package com.example.dong.newclock;


import java.util.Calendar;

/**
 * Created by dong on 2017/10/11.
 */

public class ClockItem {
    private String clockItemDate;
    private Calendar clockItemTime;
    private boolean clockItemSwitch;
    public java.util.Calendar getClockItemTime() {
        return clockItemTime;
    }

    public void setClockItemTime(java.util.Calendar clockItemTime) {
        this.clockItemTime = clockItemTime;
    }

    public String getClockItemDate() {
        return clockItemDate;
    }

    public void setClockItemDate(String clockItemDate) {
        this.clockItemDate = clockItemDate;
    }

    public boolean getClockItemSwitch() {
        return clockItemSwitch;
    }

    public void setClockItemSwitch(boolean clockItemSwitch) {
        this.clockItemSwitch = clockItemSwitch;
    }


}
