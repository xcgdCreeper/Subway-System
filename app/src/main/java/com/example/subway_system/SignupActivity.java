package com.example.subway_system;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    EditText userName;
    EditText passWord;
    int flag=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        userName=findViewById(R.id.editText3);
        passWord=findViewById(R.id.editText4);
    }
    public void load(View view){
        if(userName.getText().toString().length()==0){
            Toast.makeText(SignupActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
            return;
        }
        if(passWord.getText().toString().length()==0){
            Toast.makeText(SignupActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        String[] msg={"username"};
        userDataBase dbe=new userDataBase(SignupActivity.this);
        SQLiteDatabase db=dbe.getWritableDatabase();
        Cursor cursor=db.query("UserTable",msg,null,null,null,null,null);
        if(cursor==null){
        }
        else{
                cursor.moveToFirst();
                do{
                    String username=cursor.getString(cursor.getColumnIndex("username"));
                    if( userName.getText().toString().equals(username) ){
                        Toast.makeText(SignupActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
                        flag=1;
                        cursor.close();
                        db.close();
                        dbe.close();
                        break;
                    }
                }while (cursor.moveToNext());
            if(flag==0){
                ContentValues values=new ContentValues();
                values.put("username",userName.getText().toString());
                values.put("password",passWord.getText().toString());
                db.insert("UserTable",null,values);
                Toast.makeText(SignupActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                cursor.close();
                db.close();
                dbe.close();
            }
            Intent intent=getIntent();
            setResult(0,intent);
            finish();
        }
    }
    public void ret(View view){
        Intent intent=getIntent();
        setResult(0,intent);
        finish();
    }
}
