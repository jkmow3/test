package com.example.myapplication.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.myapplication.Config;

public class Mapsmodel {

    public boolean [][] maps;
    //地图画笔
    Paint mapPaint;
    //地图的长度，高度
    int xWidth,yHight;

    int boxSize;
    //辅助线画笔
    Paint LinePaint;
    //状态画笔
    Paint statePaint;
    //初始化地图
    public Mapsmodel(int boxSize,int xWidth,int yHight){
        this.boxSize = boxSize;
        this.xWidth = xWidth;
        this.yHight = yHight;
        maps = new boolean[Config.MAPSX][Config.MAPSY];
        //初始化堆积方块画笔
        mapPaint = new Paint();
        mapPaint.setColor(0x50000000);
        mapPaint.setAntiAlias(true);

        LinePaint = new Paint();
        LinePaint.setColor(0xff666666);

        statePaint =  new Paint();
        statePaint.setColor(0xffff0000);
        statePaint.setAntiAlias(true);
        statePaint.setTextSize(100);
    }
    //绘制堆积方块
    public void drawMaps(Canvas canvas){

        for (int x = 0; x < maps.length; x++)
            for (int y = 0; y < maps[x].length; y++)
                if (maps[x][y] == true)
                    canvas.drawRect(x * boxSize,
                            y * boxSize,
                            x * boxSize + boxSize,
                            y * boxSize + boxSize,
                            mapPaint);
    }

    public void drawLines(Canvas canvas) {
        for (int x = 0;x<maps.length;x++)
        {
            canvas.drawLine(x*boxSize,0,x*boxSize,yHight,LinePaint);
        }
        for(int y=0;y<maps[0].length;y++) {
            canvas.drawLine(0, y * boxSize, xWidth, y * boxSize, LinePaint);
        }
    }

    public void drawState(Canvas canvas,boolean isOver,boolean isPause) {
        if (isOver)
            canvas.drawText("游戏结束",xWidth/2-statePaint.measureText("游戏结束")/2,yHight/2,statePaint);
        if (isPause&&!isOver)
            canvas.drawText("暂停",xWidth/2-statePaint.measureText("暂停")/2,yHight/2,statePaint);

    }
    public void cleanMaps() {
        for (int x = 0;x<maps.length;x++)
            for (int y =0;y<maps[x].length;y++)
            {
                maps[x][y]=false;
            }
    }

    public int cleanLine() {
        int lines = 0;
        for (int y = maps[0].length-1;y>0;y--)
        {    if(checkLine(y))
        {
            deleteLine(y);
            lines++;
            y++;
        }

        }
        return lines;

    }
    public boolean checkLine(int y){
        for(int x=0; x < maps.length; x++){
            //如果有一个不为true,则该行不能消除
            if(!maps[x][y]){
                return false;
            }
        }
        return true;
    }
    public void deleteLine(int dy){
        for(int y=dy; y>0;y--) {
            for (int x = 0; x < maps.length; x++) {
                maps[x][y] = maps[x][y - 1];
            }
        }
    }
}
