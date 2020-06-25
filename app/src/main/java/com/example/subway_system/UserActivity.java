package com.example.subway_system;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HorizontalScrollView scrollView=new HorizontalScrollView(this);
        LinearLayout ll=new LinearLayout(this);
        Button button=new Button(this);
        button.setText("ImHere!");
        button.setX(100);
        button.setY(50);
        scrollView.addView(button);
        button.getLayoutParams().width=200;
        button.getLayoutParams().height=100;
        setContentView(scrollView);
    }
}
