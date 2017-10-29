package com.example.dong.newclock;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by dong on 2017/10/11.
 */

public class ClockFragement extends Fragment implements View.OnClickListener{


    private View view;
    private ImageView clockIncrease;
    private RecyclerView recyclerViewClcok;
    private ClockRecyclerAdapter clockRecyclerAdapter;
    private List<ClockItem> mData;
    private ClockDataBaseHelper mdbHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.clock, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void onActivityCreated(Bundle saveInstancState){
        super.onActivityCreated(saveInstancState);
        intitView();
        recyclerViewClcok.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerViewClcok.setAdapter(clockRecyclerAdapter = new ClockRecyclerAdapter(getContext(),mData,ClockFragement.this));
        clockRecyclerAdapter.setOnItemClickListener(new ClockRecyclerAdapter.OnItemOnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemOnClick(View view, int position) {
                Intent intent = new Intent(getActivity(),SetClockActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("hour",clockRecyclerAdapter.getItem(position).getClockItemTime().get(Calendar.HOUR_OF_DAY));
                bundle.putInt("min",clockRecyclerAdapter.getItem(position).getClockItemTime().get(Calendar.MINUTE));
                bundle.putString("date",clockRecyclerAdapter.getItem(position).getClockItemDate());
                intent.putExtras(bundle);
                deleteClock(position);
                startActivity(intent);
            }

            @Override
            public void onItemLongOnClick(View view, int position) {
                showPopMenu(view, position);
            }
        });
        readClockRecycler();
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    int hour = data.getIntExtra("Hour", 1);
                    int min = data.getIntExtra("Min", 1);
                    String date = data.getStringExtra("date");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, min);
                    ClockItem clockItem = new ClockItem();
                    clockItem.setClockItemDate(date);
                    clockItem.setClockItemTime(calendar);
                    clockItem.setClockItemSwitch(true);
                    clockRecyclerAdapter.addData(clockItem);
                    recyclerViewClcok.scrollToPosition(0);
                   saveClockRecycler(this.getActivity());
                    startAlarm(this.getActivity(), calendar);  //启动闹钟服务
                }
                break;
        }
    }

    public void saveClockRecycler(Context context) {
        SQLiteDatabase fc = mdbHelper.getWritableDatabase();
        fc.execSQL("DELETE FROM Clock");
        ContentValues values = new ContentValues();
        for (int i = 0; i < clockRecyclerAdapter.getItemCount(); i++) {
            values.put("clockname", "clock" + i);
            values.put("hour", clockRecyclerAdapter.getItem(i).getClockItemTime().get(Calendar.HOUR_OF_DAY));
            values.put("min", clockRecyclerAdapter.getItem(i).getClockItemTime().get(Calendar.MINUTE));
            values.put("position", i);
            values.put("date", clockRecyclerAdapter.getItem(i).getClockItemDate());
            int isswitch;
            if (clockRecyclerAdapter.getItem(i).getClockItemSwitch()) {
                isswitch = 1;
            } else {
                isswitch = 0;
            }
            values.put("isclock", isswitch);
            values.put("time", clockRecyclerAdapter.getItem(i).getClockItemTime().getTimeInMillis());
            fc.insert("Clock", null, values);
            values.clear();
        }

    }


    private void readClockRecycler() {
        SQLiteDatabase fc = mdbHelper.getWritableDatabase();
        Cursor cursor = fc.query("Clock", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            ClockItem clockItem = new ClockItem();
            Calendar calendarex = Calendar.getInstance();
            calendarex.setTimeInMillis(cursor.getLong(cursor.getColumnIndex("time")));
            clockItem.setClockItemTime(calendarex);
            boolean isclock;
            if (cursor.getInt(cursor.getColumnIndex("isclock")) == 0) {
                isclock = false;
            } else {
                isclock = true;
            }
            clockItem.setClockItemSwitch(isclock);
            clockItem.setClockItemDate(cursor.getString(cursor.getColumnIndex("date")));
            clockRecyclerAdapter.addData(clockItem);
        }
    }

    public void showPopMenu(View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_delet, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override

            public boolean onMenuItemClick(MenuItem item) {
                deleteClock(position);
                saveClockRecycler(getContext());
                return false;
            }
        });


        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {

            }
        });
        popupMenu.show();
    }

    public int setTime(long time) {
        return (int) (time / 60000);
    }
    //删除闹钟
    private void deleteClock(int position) {
        Intent intent = new Intent(getActivity(),AlarmBroadcastReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        PendingIntent delete = PendingIntent.getBroadcast(getActivity(), setTime(clockRecyclerAdapter.getItem(position).getClockItemTime().getTimeInMillis()),intent,0);
        alarmManager.cancel(delete);
        clockRecyclerAdapter.deleteItem(position);
    }

    private void intitView() {
        clockIncrease = (ImageView) view.findViewById(R.id.clock_increase);
        recyclerViewClcok = (RecyclerView) view.findViewById(R.id.recyclerViewClock);
        clockIncrease.setOnClickListener(this);
        mData = new ArrayList<ClockItem>();
        mdbHelper = new ClockDataBaseHelper(getContext(), "MyClock.db" , null , 1 );
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startAlarm(Context context, Calendar calendar) {
        Intent intent = new Intent(this.getActivity(), AlarmBroadcastReceiver.class);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        PendingIntent sender = PendingIntent.getBroadcast(this.getActivity(), setTime(calendar.getTimeInMillis()), intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }

    public void setSwitch(int position, boolean isSwitch) {
        clockRecyclerAdapter.getItem(position).setClockItemSwitch(isSwitch);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clock_increase:
                Intent intent = new Intent(this.getActivity(),SetClockActivity.class);
                startActivityForResult(intent, 1);
                break;
            default:
                break;
        }

    }



}
