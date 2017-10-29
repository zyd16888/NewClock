package com.example.dong.newclock;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by dong on 2017/9/27.
 */

public class SetRepetitionsPopup extends PopupWindow {
    private TextView workday;
    private TextView once;
    private TextView everyday;
    private TextView customize;


    public SetRepetitionsPopup(Context context , View.OnClickListener itemsOnClick){
       final View view = LayoutInflater.from(context).inflate(R.layout.set_up_time_window, null);
        workday = (TextView) view.findViewById(R.id.workday);
        once = (TextView) view.findViewById(R.id.once);
        everyday = (TextView) view.findViewById(R.id.everyday);
        customize = (TextView) view.findViewById(R.id.customize);

        workday.setOnClickListener(itemsOnClick);
        once.setOnClickListener(itemsOnClick);
        everyday.setOnClickListener(itemsOnClick);
        customize.setOnClickListener(itemsOnClick);

        setOutsideTouchable(true);   //设置外部可点击
        setContentView(view);    //设置布局
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setFocusable(true);   //可点击
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        setBackgroundDrawable(dw);
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
