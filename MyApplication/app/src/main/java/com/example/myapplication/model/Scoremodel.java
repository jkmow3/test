package com.example.myapplication.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.utils.SQLUtils;
//计算当前分数
public class Scoremodel {
  public int scoreNow;
  public int scoreMax;
    public SQLUtils sqlUtils;
    public Scoremodel(Context context){
        Log.d("TAG", "sql: ");
        sqlUtils = new SQLUtils(context,"score_db",null,1);
    }
    public void addscore(int lines) {
        if(lines ==0){
            return;
        }
        scoreNow += lines;
    }
    //计算最高分
    public void updateScoreMax(boolean isOver)
    {
        if(scoreMax==0)
            scoreMax = sqlUtils.getMaxScore();
        if(scoreNow>scoreMax)
        {Log.d("TAG", "scoreMAx");
            scoreMax = scoreNow;
        }

    }
    @SuppressLint("SetTextI18n")
    public void showNowScore(TextView textNowScore){
        if(textNowScore != null) {
            textNowScore.setText("" + scoreNow);
        }
    }

    //显示最高分
    @SuppressLint("SetTextI18n")
    public void showMaxScore(boolean isover, TextView textMaxScore){
        if(textMaxScore != null){
            updateScoreMax(isover);
            textMaxScore.setText(""+scoreMax);
        }
    }
}
