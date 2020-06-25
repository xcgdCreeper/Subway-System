package com.example.subway_system;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    Button Ret;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);
        Ret=findViewById(R.id.button9);
        Ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();
                setResult(0,intent);
                finish();
            }
        });
    }

    public void add(View view){
        int flag=0;
        EditText stationName=findViewById(R.id.editText5);
        EditText position=findViewById(R.id.editText6);
        String[] msg={"stationname","position"};
        stationDataBase dbe=new stationDataBase(AdminActivity.this);
        SQLiteDatabase db=dbe.getWritableDatabase();
        Cursor cursor=db.query("StationTable",msg,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String sname=cursor.getString(cursor.getColumnIndex("stationname"));
                String p=cursor.getString(cursor.getColumnIndex("position"));
                if(stationName.getText().toString().equals(sname)){
                    flag=1;
                    Toast.makeText(AdminActivity.this,"车站已存在",Toast.LENGTH_SHORT).show();
                    db.close();
                    dbe.close();
                    cursor.close();
                    break;
                }
                if(position.getText().toString().equals(p)){
                    flag=1;
                    Toast.makeText(AdminActivity.this,"位置已被占用",Toast.LENGTH_SHORT).show();
                    db.close();
                    dbe.close();
                    cursor.close();
                    break;
                }
            }while(cursor.moveToNext());
        }
        if(flag==0 && stationName.getText().toString().length()!=0 && position.getText().toString().length()!=0){
            ContentValues values=new ContentValues();
            values.put("stationname",stationName.getText().toString());
            values.put("position",position.getText().toString());
            db.insert("StationTable",null,values);
            db.close();
            dbe.close();
            cursor.close();
            Toast.makeText(AdminActivity.this,"成功创建车站",Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View view){
        int flag=0;
        EditText stationName=findViewById(R.id.editText5);
        EditText position=findViewById(R.id.editText6);
        String[] msg={"stationname"};
        stationDataBase dbe=new stationDataBase(AdminActivity.this);
        SQLiteDatabase db=dbe.getWritableDatabase();
        Cursor cursor=db.query("StationTable",msg,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String sname=cursor.getString(cursor.getColumnIndex("stationname"));
                if(stationName.getText().toString().equals(sname)){
                    flag=1;
                    db.execSQL("delete from StationTable where stationname='"+stationName.getText().toString()+"';");
                    Toast.makeText(AdminActivity.this,"车站已删除",Toast.LENGTH_SHORT).show();
                    db.close();
                    dbe.close();
                    cursor.close();
                    break;
                }
            }while(cursor.moveToNext());
        }
        if(flag==0){
            Toast.makeText(AdminActivity.this,"未找到车站",Toast.LENGTH_SHORT).show();
        }
    }
}
