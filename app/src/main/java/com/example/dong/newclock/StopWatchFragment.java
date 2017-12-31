package com.example.dong.newclock;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dong on 2017/10/11.
 */

public class StopWatchFragment extends Fragment implements View.OnClickListener {
    private TextView tvShow;
    private ImageView ivStart;
    private ImageView ivRefresh;
    private ImageView ivRecord;
    private RecyclerView recyclerView;
    private List<String> timeRecord;
    private stopWatchThred swt;
    private boolean flag2 = true;
    private LinerRecyclerAdapter adapter;
    private View view;
    private int min , second , msec  ,second1, stopWatch=0;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stopwatch, container, false);
        tvShow = (TextView) view.findViewById(R.id.Stopwatch_show);
        ivStart = (ImageView) view.findViewById(R.id.iV_sW_Start);
        ivRefresh = (ImageView) view.findViewById(R.id.iv_refresh_sw);
        ivRecord = (ImageView) view.findViewById(R.id.iv_sw_Record);
        ivStart.setOnClickListener(this);
        ivRecord.setOnClickListener(this);
        ivRefresh.setOnClickListener(this);
        swt = new stopWatchThred();
        timeRecord = new ArrayList<String>();
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iV_sW_Start:
                if (!ivStart.isSelected()){
                    ivStart.setSelected(true);
                    ivRefresh.setSelected(true);
                    ivRecord.setSelected(true);
                    if (flag2){
                        Log.e("dong","start");
                        swt.start();
                    }else {
                        Log.e("dong","run");
                        swt.myRun();
                    }
                }else if (ivStart.isSelected()){
                    Log.e("dong",String.valueOf(ivStart.isSelected()));
                    ivStart.setSelected(false);
                    ivRefresh.setSelected(false);
                    ivRecord.setSelected(false);
                    swt.mySuspend();
                }else {
                    ivStart.setSelected(true);
                    ivRefresh.setSelected(true);
                    ivRecord.setSelected(true);
                }
                break;
            case R.id.cd_iv_refresh:
                if(ivRefresh.isSelected()){
                    timeRecord.clear();
                    recyclerView.removeAllViews();
                    adapter.clean();
                    adapter.notifyDataSetChanged();
                    swt.mySuspend();
                    ivRefresh.setSelected(false);
                    stopWatch=0;
                    ivStart.setSelected(false);
                    ivRecord.setSelected(false);
                    tvShow.setText("00:00.00");
                }
                break;
            case R.id.iv_sw_Record:
                if (ivRecord.isSelected()){
                    timeRecord.add(0,min + ":" + second + ":" + msec);
                    //adapter.notifyItemInserted(1);
                    //recyclerView.scrollToPosition(1);
                }
                break;
            default:
                break;
        }

    }



    public class stopWatchThred extends Thread{
        private final int RUNNING = 1;
        private final int SUSPEND = 0;
        private final int STOP = -1;
        private int status = 1;

        public void run() {
            flag2 = false;
            while (status != STOP) {
                if (status == SUSPEND) {
                    synchronized (this) {
                        try {
                            Log.e("dong", String.valueOf(status));
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        Message msg = new Message();
                        msg.what = 1;
                        hanlder.sendMessage(msg);
                        stopWatch++;
                        Log.e("dong", String.valueOf(status));
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }

        public void myRun() {
            status = RUNNING;
            synchronized (this) {
                notify();
            }
        }

        public void mySuspend() {
            status = SUSPEND;
        }

        public void myStop() {
            status = STOP;
        }



    }


    private Handler hanlder = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    msec = stopWatch%100;
                    second1=stopWatch/100;
                    second = stopWatch/100%60;
                    min=second1/60;
                    tvShow.setText(min+":"+second+":"+msec);
                    break;
                default:
                    break;

            }
        }
    };


}
