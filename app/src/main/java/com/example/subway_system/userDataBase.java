package com.example.subway_system;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class userDataBase extends SQLiteOpenHelper {
    public userDataBase(Context context){
        super(context, "Udb", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql="create table if not exists UserTable (username text not null primary key,password text)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
