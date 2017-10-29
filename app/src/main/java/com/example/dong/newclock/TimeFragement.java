package com.example.dong.newclock;

import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dong on 2017/10/22.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class TimeFragement extends android.support.v4.app.Fragment {
    private TextView worldTime;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.world_time, container,false);
        new TimeThread().start();
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        worldTime = (TextView) view.findViewById(R.id.world_time0);

    }

    public String getTime(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));//时
        String minute = String.valueOf(c.get(Calendar.MINUTE));//分
        String second = String.valueOf(c.get(Calendar.SECOND));//秒
        return hour + ":" + minute + ":" + second;
    }



    public class TimeThread extends Thread{

        @Override
        public void run() {
            while (true){
                try{
                    Message msg = new Message();
                    msg.what = 1;
                    hanlder.sendMessage(msg);
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        }

        private Handler hanlder = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        worldTime.setText(getTime());
                        break;
                    default:
                        break;

                }
            }
        };
    }




}
