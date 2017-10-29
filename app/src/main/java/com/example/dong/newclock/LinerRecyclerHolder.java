package com.example.dong.newclock;

import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;


/**
 * Created by hhx on 17-2-11.
 */

public class LinerRecyclerHolder extends RecyclerView.ViewHolder {

    public TextView tv;

    public LinerRecyclerHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.sw_tv_item);
    }
}
