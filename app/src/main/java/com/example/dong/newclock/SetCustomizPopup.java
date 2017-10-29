package com.example.dong.newclock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by dong on 2017/9/27.
 */

public class SetCustomizPopup extends PopupWindow {
    private CheckBox getDay_one;
    private CheckBox getDay_two;
    private CheckBox getDay_three;
    private CheckBox getDay_four;
    private CheckBox getDay_five;
    private CheckBox getDay_six;
    private CheckBox getDay_seven;
    private Button setCustomiz_delete;
    private Button SetCustomiz_save;

    public SetCustomizPopup(Context context, View.OnClickListener itemOnClick, CompoundButton.OnCheckedChangeListener itemChangeClick){
        final View view = LayoutInflater.from(context).inflate(R.layout.set_up_customize_time_window,null);

        getDay_one = (CheckBox) view.findViewById(R.id.checkBox1);
        getDay_two = (CheckBox) view.findViewById(R.id.checkBox2);
        getDay_three = (CheckBox) view.findViewById(R.id.checkBox3);
        getDay_four = (CheckBox) view.findViewById(R.id.checkBox4);
        getDay_five = (CheckBox) view.findViewById(R.id.checkBox5);
        getDay_six = (CheckBox) view.findViewById(R.id.checkBox6);
        getDay_seven = (CheckBox) view.findViewById(R.id.checkBox7);
        setCustomiz_delete = (Button) view.findViewById(R.id.customize_delete);
        SetCustomiz_save = (Button) view.findViewById(R.id.customize_save);

        getDay_one.setOnClickListener(itemOnClick);
        getDay_two.setOnClickListener(itemOnClick);
        getDay_three.setOnClickListener(itemOnClick);
        getDay_four.setOnClickListener(itemOnClick);
        getDay_five.setOnClickListener(itemOnClick);
        getDay_six.setOnClickListener(itemOnClick);
        getDay_seven.setOnClickListener(itemOnClick);
        setCustomiz_delete.setOnClickListener(itemOnClick);
        SetCustomiz_save.setOnClickListener(itemOnClick);

        getDay_one.setTag(1);
        getDay_two.setTag(2);
        getDay_three.setTag(3);
        getDay_four.setTag(4);
        getDay_five.setTag(5);
        getDay_six.setTag(6);

        setOutsideTouchable(true);   //设置外部可点击
        setContentView(view);    //设置布局
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setFocusable(true);   //可点击
        // 实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        // setBackgroundDrawable(dw);
        view.setOnTouchListener(new View.OnTouchListener() {// 如果触摸位置在窗口外面则销毁

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int height = view.findViewById(R.id.ll_selecttimeway).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

}
