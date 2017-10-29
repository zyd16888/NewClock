package com.example.dong.newclock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dong on 2017/10/15.
 */

class ClockDataBaseHelper extends SQLiteOpenHelper{

    public static final String CREAT_CLOCK = "create table Clock("
            + "id integer primary key autoincrement, "
            + "clockname text, "
            + "isclock integer, "
            + "position integer, "
            + "hour integer, "
            + "min integer, "
            + "date text,"
            + "time integer, "
            + "broadcastid integer)";



    public ClockDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_CLOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
