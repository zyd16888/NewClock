package com.example.dong.newclock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by dong on 2017/10/11.
 */

public class ClockRecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView clockItemTime;
    public TextView clockItemRepeat;
    public Switch clockItemSwitch;
    private ClockFragement clockFragement;
    private Context mcontext;


    public ClockRecyclerHolder(ClockFragement clockFragement, View itemView) {
        super(itemView);
        clockItemTime = (TextView) itemView.findViewById(R.id.clock_it_time);
        clockItemRepeat = (TextView) itemView.findViewById(R.id.clock_it_repeat_time);
        clockItemSwitch = (Switch) itemView.findViewById(R.id.clock_switch);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clock_switch:
                if (clockItemSwitch.isChecked()) {
                    clockFragement.setSwitch(getAdapterPosition(),true);
                    clockFragement.saveClockRecycler(mcontext);

                }
                else{
                    clockFragement.setSwitch(getAdapterPosition(),false);
                    clockFragement.saveClockRecycler(mcontext);
                }
                default:
        }

    }
}
