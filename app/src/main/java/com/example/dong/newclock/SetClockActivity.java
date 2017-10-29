package com.example.dong.newclock;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by dong on 2017/9/26.
 */

public class SetClockActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {

    private LinearLayout linearlayout;
    private TextView delete;
    private boolean isSave = false;
    private TextView save;
    private TextView repeat_time;
    private TimePicker timePicker;
    private boolean isTime[];

    private SetRepetitionsPopup setRepetitionsPopup;
    private SetCustomizPopup setCustomizPopup;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_clock);


        linearlayout = (LinearLayout) findViewById(R.id.set_up);
        delete = (TextView) findViewById(R.id.delete);
        save = (TextView) findViewById(R.id.save);
        repeat_time = (TextView) findViewById(R.id.repeat_time);
        timePicker = (TimePicker) findViewById(R.id.TimePicker);
        delete.setOnClickListener(this);
        save.setOnClickListener(this);
        linearlayout.setOnClickListener(this);
        isTime = new boolean[7];
        setTimeFalse();
        Bundle bundle = this.getIntent().getExtras();
        if (bundle!=null&&bundle.containsKey("date")){
            repeat_time.setText(bundle.getString("date"));
            timePicker.setHour(bundle.getInt("hour"));
            timePicker.setMinute(bundle.getInt("min"));
        }

    }


    private void setTimeFalse() {
        for(int i=0;i<7;i++){
            isTime[i]=false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete:
                finish();
                break;
            case R.id.save:
                Intent intent = new Intent();
                intent.putExtra("Hour",timePicker.getHour());
                intent.putExtra("Min",timePicker.getMinute());
                intent.putExtra("date",repeat_time.getText());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.set_up:
                showSetUpWindows();

                default:
                    break;
        }

    }

    private void showSetUpWindows() {
        //showAtLocation(View parent, int gravity, int x, int y)：相对于父控件的位置（例如正中央Gravity.CENTER，
        // 下方Gravity.BOTTOM等），可以设置偏移或无偏
        setRepetitionsPopup = new SetRepetitionsPopup(this,itemsOnclick);
        setRepetitionsPopup.showAtLocation(SetClockActivity.this.findViewById(R.id.set_up),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    private View.OnClickListener itemsOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setRepetitionsPopup.dismiss();
            switch (v.getId()){
                case R.id.everyday:
                    repeat_time.setText("每天");
                    break;
                case R.id.once:
                    repeat_time.setText("一次");
                    break;
                case R.id.workday:
                    repeat_time.setText("工作日");
                    break;
                case R.id.customize:
                    showSetCustomTimeWindows();

            }
        }
    };

    private void showSetCustomTimeWindows() {
        setCustomizPopup = new SetCustomizPopup(this, itemsOnClick2, itemChangeClick);
        setCustomizPopup.showAtLocation(SetClockActivity.this.findViewById(R.id.set_up),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }


    private View.OnClickListener itemsOnClick2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setRepetitionsPopup.dismiss();
            switch (v.getId()){
                case R.id.customize_delete:
                    showSetUpWindows();
                    break;
                case R.id.customize_save:
                    StringBuffer show=new StringBuffer();
                    for (int i=0; i<7;i++){
                        if(isTime[i]){
                            show.append("星期"+(i+1));
                            show.append(",");
                        }
                    }
                    if(show.length()>0){
                        show.delete(show.length()-1,show.length());
                        repeat_time.setText(show);
                    }else{
                        repeat_time.setText("一次");
                    }
                    setTimeFalse();
                    break;

            }
        }
    };

    private CompoundButton.OnCheckedChangeListener itemChangeClick = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked){
                isTime[(int) buttonView.getTag()-1]=true;
            }else {
                isTime[(int) buttonView.getTag()-1]=false;
            }
        }
    };

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
