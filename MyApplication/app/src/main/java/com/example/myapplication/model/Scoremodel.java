package com.example.myapplication.model;

import android.content.Context;
import android.widget.TextView;

import com.example.myapplication.utils.SQLUtils;

public class Scoremodel {
  public int scoreNow;
  public int scoreMax;
  public SQLUtils sqlUtils;
    public Scoremodel(Context context){
        sqlUtils = new SQLUtils(context,"score.db",null,1);
    }
    public void addscore(int lines) {
        if(lines ==0){
            return;
        }
        scoreNow += lines;
    }
    public void updateScoreMax(boolean isOver)
    {
        if(scoreMax==0)
        {
            scoreMax = sqlUtils.getMaxScore();
        }
        if(scoreNow>scoreMax)
        {
            scoreMax = scoreNow;
            if (isOver)
                sqlUtils.putScore(scoreMax);
        }

    }
    public void showNowScore(TextView textNowScore){
        if(textNowScore != null){
            textNowScore.setText(""+ scoreNow);
        }
    }

    //显示最高分
    public void showMaxScore(boolean isover, TextView textMaxScore){
        if(textMaxScore != null){
            updateScoreMax(isover);
            textMaxScore.setText(""+scoreMax);
        }
    }
}
