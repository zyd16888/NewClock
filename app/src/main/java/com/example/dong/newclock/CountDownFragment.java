package com.example.dong.newclock;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dong on 2017/10/22.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class CountDownFragment extends android.support.v4.app.Fragment implements View.OnClickListener{


    private View view;
    private myThread cd;
    private TextView tvShow;
    private Context context;
    private EditText minute;
    private EditText second;
    private ImageView ivStart;
    private ImageView ivRefresh;
    private boolean flag = true, flag2 = true, flag3 = true;
    private int min;
    private int sec;




    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.count_down,container,false);
        context = getActivity();

        tvShow = (TextView) view.findViewById(R.id.cd_tv_show);
        minute = (EditText) view.findViewById(R.id.edit_min);
        second = (EditText) view.findViewById(R.id.edit_sec);
        ivStart = (ImageView) view.findViewById(R.id.cd_iv_start);
        ivRefresh = (ImageView) view.findViewById(R.id.cd_iv_refresh);
        ivStart.setOnClickListener(this);
        ivRefresh.setOnClickListener(this);
        cd = new myThread();
        return view;


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cd_iv_start:
                if(!ivStart.isSelected()){
                    if (flag2){
                        if (TextUtils.isEmpty(minute.getText()) || TextUtils.isEmpty(second.getText())){
                            Toast.makeText(context,"请在两个框中输入时间",Toast.LENGTH_SHORT).show();
                            flag3 = false;
                        }
                        else if (!theNum(minute.getText()) || !theNum(second.getText())){
                            Toast.makeText(this.getActivity(), "请输入数字，不支持字符", Toast.LENGTH_SHORT).show();
                            flag3 = false;
                        }
                        else if (Integer.parseInt(String.valueOf(minute.getText())) > 59 ||
                                Integer.parseInt(String.valueOf(second.getText())) > 59 ||
                                Integer.parseInt(String.valueOf(minute.getText())) < 0 ||
                                Integer.parseInt(String.valueOf(second.getText())) < 0 ){
                            Toast.makeText(this.getActivity(), "请输入60以内的数字", Toast.LENGTH_SHORT).show();
                            flag3 = false;
                        }else {
                            flag3 = true;
                            min = Integer.parseInt(String.valueOf(minute.getText()));
                            sec = Integer.parseInt(String.valueOf(second.getText()));
                        }
                    }
                    if (flag3) {
                        flag2 = false;
                        ivStart.setSelected(true);
                        ivRefresh.setSelected(true);
                        if (flag) {
                            flag = false;
                            cd.start();
                        } else {cd.myRun();}
                    }
                }
                    else {
                        ivStart.setSelected(false);
                        ivRefresh.setSelected(false);
                        cd.mySuspend();
                    }break;
            case R.id.cd_iv_refresh:
                if (ivRefresh.isSelected()){
                    cd.mySuspend();
                    flag2 = true;
                    min = 0;
                    sec = 0;
                    ivStart.setSelected(false);
                    ivStart.setSelected(false);
                    tvShow.setText("0:0");
                }break;
            default:
                break;
        }

    }


    private class myThread extends Thread{
        private final int RUNNING = 1;
        private final int SUSPEND = 0;
        private final int STOP = -1;
        private int status =1;

        @Override
        public void run(){
            while (status != STOP){
                if(status ==  SUSPEND){
                    synchronized (this){
                        try {
                            wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }else {
                    try {
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                        sleep(999);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }

        public void myRun(){
            synchronized (this){
                notify();
            }
            status = RUNNING;
        }

        public void mySuspend(){
            status = SUSPEND;
        }




    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if (sec != 0){
                        sec--;
                    }else {
                        sec = 59;
                        min--;
                    }
                    if (min == -1){
                        cd.mySuspend();
                        ivStart.setSelected(false);
                        ivRefresh.setSelected(false);
                        flag2 = true;
                        Toast.makeText(context,"倒计时结束",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    tvShow.setText(min+":"+sec);
                    break;
            }
        }
    };



    private boolean theNum(Editable edt) {

        if(edt.length()>0){
            Log.e("Num", "233");
            for (int i = edt.length(); i >0; i--) {
                int j=i-1;
                int num = edt.charAt(j);
                if (num < 48 || num > 57) {
                    return false;
                }
            }
        }
        return true;

    }

}
