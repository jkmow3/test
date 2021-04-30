package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
    }

    public void onClick_easy(View v) {
        Intent intent = new Intent();
        intent.setClass(GameActivity2.this,MainActivity.class);
        intent.putExtra("v", 500);
        startActivity(intent);
    }

    public void onClick_usally(View v) {
        Intent intent = new Intent();
        intent.setClass(GameActivity2.this,MainActivity.class);
        intent.putExtra("v", 300);
        startActivity(intent);
    }

    public void onClick_diffcult(View v) {
        Intent intent = new Intent();
        intent.setClass(GameActivity2.this,MainActivity.class);
        intent.putExtra("v", 100);
        startActivity(intent);
    }
}