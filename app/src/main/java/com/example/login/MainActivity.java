package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.login.activity.MainActivityScore;

import com.example.login.Manager.PrefManager;

public class MainActivity extends AppCompatActivity {
Button btnLogout,btnScore;
Context context;
/*
    btnLogout = findViewbyId("btnLogout");*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        btnLogout = findViewById(R.id.btnLogout);
        btnScore = findViewById(R.id.btnScore);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefManager prefManager=new PrefManager(context);
                prefManager.remove("TOKEN");
                Intent intent = new Intent(context, SplashscreenActivity.class);
                startActivity(intent);
            }
        });

        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentScore = new Intent(context, MainActivityScore.class);
                startActivity(intentScore);
            }
        });

//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context,WelcomeActivity.class);
//                startActivity(intent);
//            }
//        });

//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            //    setLogout();
//            }
//        });
//    }

//    public void setLogout(){
//        PrefManager prefManager = new PrefManager(context);
//        prefManager.remove("TOKEN");
//        Intent intent = new Intent(context,LoginActivity.class);
//        startActivity(intent);
//    }
}}
