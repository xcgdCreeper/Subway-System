package com.example.subway_system;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class stationDataBase extends SQLiteOpenHelper {
    public stationDataBase(Context context){
        super(context, "Sdb", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql="create table if not exists StationTable (stationname text not null primary key,position text not null)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
