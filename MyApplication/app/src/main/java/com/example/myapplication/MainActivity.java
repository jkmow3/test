package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.control.Gamecontrol;
import com.example.myapplication.model.Boxsmodel;
import com.example.myapplication.model.Mapsmodel;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int initiaVilocity;
    public View view;
    public View NextPanel;
    public Gamecontrol gamecontrol;
    public TextView Score;

    public TextView textMax;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            String type = (String) msg.obj;
            //刷新重绘view
            if (type.equals("invalidate")) {
                view.invalidate();
                NextPanel.invalidate();
                gamecontrol.scoremodel.showNowScore(Score);
                gamecontrol.scoremodel.showMaxScore(gamecontrol.isOver,textMax);
            }
            else if (type.equals("pause")) {
                TextView textView = findViewById(R.id.btnPause);
                textView.setText("pause");
            } else if (type.equals("continue")) {
                TextView textView = findViewById(R.id.btnPause);
                textView.setText("continue");
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=this.getIntent();
        initiaVilocity = intent.getIntExtra("v", 500);
        gamecontrol = new Gamecontrol(handler,this);
        InitView();
        initListener();
        gamecontrol.scoremodel.showMaxScore(gamecontrol.isOver,textMax);
    }

    private void initListener() {
        findViewById(R.id.btnleft).setOnClickListener(this);
        findViewById(R.id.btnright).setOnClickListener(this);
        findViewById(R.id.btnup).setOnClickListener(this);
        findViewById(R.id.btndown).setOnClickListener(this);
        findViewById(R.id.btnStart).setOnClickListener(this);
        findViewById(R.id.btnPause).setOnClickListener(this);
        findViewById(R.id.btndown2).setOnClickListener(this);
    }




    private void InitView() {

       //游戏区域绘制
        view = new View(this){
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                gamecontrol.draw(canvas);
            }
        };
        view.setLayoutParams(new ViewGroup.LayoutParams(Config.xWidth,Config.yHight));
        view.setBackgroundColor(0x10000000);
        FrameLayout layoutgame = (FrameLayout) findViewById(R.id.layoutGame);
        layoutgame.setPadding(Config.PADDing,Config.PADDing,Config.PADDing,Config.PADDing);
        layoutgame.addView(view);

        LinearLayout layoutinfo = (LinearLayout) findViewById((R.id.layoutInfo));
        layoutinfo.setPadding(Config.PADDing,Config.PADDing,Config.PADDing,Config.PADDing);
        //预览界面的绘制
        NextPanel = new View(this){
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                gamecontrol.drawNext(canvas,NextPanel.getWidth());
            }
        };
        NextPanel.setLayoutParams(new ViewGroup.LayoutParams(-1,200));
        NextPanel.setBackgroundColor(0x20000000);
        FrameLayout layoutNext = (FrameLayout)findViewById(R.id.layoutNext);
        layoutNext.addView(NextPanel);

        Score = findViewById(R.id.textNowScore);
        textMax = findViewById(R.id.textMaxScore);
    }

    @Override
    public void onClick(View v) {

        gamecontrol.onClick(v.getId(),initiaVilocity);

        view.invalidate();
        NextPanel.invalidate();
    }
}