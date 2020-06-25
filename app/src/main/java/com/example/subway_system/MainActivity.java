package com.example.subway_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText userName;
    EditText passWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName=findViewById(R.id.editText2);
        passWord=findViewById(R.id.editText);
    }
    public void logIn(View view){
        //用户名 test
        //密码 123
        //用户名 xcgdCreeper
        //密码 123
        int flag=0;
        String[] msg={"username","password"};
        userDataBase dbe=new userDataBase(MainActivity.this);
        SQLiteDatabase db=dbe.getWritableDatabase();
        Cursor cursor=db.query("UserTable",msg,null,null,null,null,null);
        if(cursor==null){
        }
        else{
            cursor.moveToFirst();
            do{
                String username=cursor.getString(cursor.getColumnIndex("username"));
                String password=cursor.getString(cursor.getColumnIndex("password"));
                if(userName.getText().toString().equals(username)){
                    if(passWord.getText().toString().equals(password)){
                        flag=1;
                        AlertDialog.Builder builder=new AlertDialog.Builder(this);
                        builder.setMessage("欢迎回来,"+userName.getText().toString()).setPositiveButton("嗯", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(MainActivity.this,UserActivity.class);
                                startActivity(intent);
                            }
                        } ).show();
                        break;
                    }
                    else{
                        Toast.makeText(MainActivity.this,"密码错了！",Toast.LENGTH_SHORT).show();
                    }
                }
            }while(cursor.moveToNext());
            if(flag==0)
                Toast.makeText(MainActivity.this,"用户名错了",Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        db.close();
        dbe.close();
    }
    public void quit(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("真的要退出吗orz").setPositiveButton("告辞", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        } ).setNegativeButton("我再想想", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"mua",Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
    public void signUp(View view){
        Intent intent=new Intent(MainActivity.this,SignupActivity.class);
        startActivity(intent);
    }
    public void cheat(View view){
        Intent intent=new Intent(MainActivity.this,AdminActivity.class);
        startActivity(intent);
    }
}
