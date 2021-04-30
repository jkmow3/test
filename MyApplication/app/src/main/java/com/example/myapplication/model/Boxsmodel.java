package com.example.myapplication.model;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;
//方块模型
public class Boxsmodel {
    //方块画笔
    public Paint boxPaint;
    //方块的点
    public Point[] boxs;
    //方块的类型
    public int boxType;
    //方块大小
    int boxSize;
    //预览方块大小
    public int boxsnextSize;

    final int TUPE = 7;

    public Point[] boxsNext;

    public int boxNextType;

    //初始化方块
    public Boxsmodel(int boxSize) {
        this.boxSize = boxSize;
        boxPaint = new Paint();
        boxPaint.setColor(0xff000000);
        boxPaint.setAntiAlias(true);
    }
    //绘制预览方块
    public void drawNext(Canvas canvas, int width) {
        if (boxsNext != null) {
            if (boxsnextSize == 0)
                boxsnextSize = width / 5;
            for (int i = 0; i < boxsNext.length; i++)
                canvas.drawRect((boxsNext[i].x - 2) * boxsnextSize
                        , (boxsNext[i].y + 1) * boxsnextSize
                        , (boxsNext[i].x - 2) * boxsnextSize + boxsnextSize
                        , (boxsNext[i].y + 1) * boxsnextSize + boxsnextSize,
                        boxPaint);
        }
    }

    //生成新的方块
    public void newBoxs() {
        if (boxsNext == null)
            nexBoxsNext();

        boxs = boxsNext;
        boxType = boxNextType;

        nexBoxsNext();
    }
    private void nexBoxsNext() {
        Random random = new Random();
        boxNextType = random.nextInt(TUPE);
        switch (boxNextType) {
            case 0:
                boxsNext = new Point[]{new Point(4, 0), new Point(5, 0), new Point(4, 1), new Point(5, 1)};
                break;
            //L
            case 1:
                boxsNext = new Point[]{new Point(4, 1), new Point(5, 0), new Point(3, 1), new Point(5, 1)};
                break;
            //J
            case 2:
                boxsNext = new Point[]{new Point(4, 1), new Point(3, 0), new Point(3, 1), new Point(5, 1)};
                break;
            //I
            case 3:
                boxsNext = new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(6, 0)};
                break;
            //S
            case 4:
                boxsNext = new Point[]{new Point(4, 1), new Point(4, 0), new Point(3, 1), new Point(5, 0)};
                break;
            //Z
            case 5:
                boxsNext = new Point[]{new Point(4, 1), new Point(3, 0), new Point(4, 0), new Point(5, 1)};
                break;
            //T
            case 6:
                boxsNext = new Point[]{new Point(4, 1), new Point(4, 0), new Point(3, 1), new Point(5, 1)};
                break;
        }
    }
    //绘制方块
    public void drawBoxs(Canvas canvas) {
        if (boxs != null)
            for (int i = 0; i < boxs.length; i++) {
                canvas.drawRect(boxs[i].x * boxSize,
                        boxs[i].y * boxSize,
                        boxs[i].x * boxSize + boxSize,
                        boxs[i].y * boxSize + boxSize,
                        boxPaint);
            }
    }

    public boolean move(int x, int y, Mapsmodel mapsmodel) {
        for (int i = 0; i < boxs.length; i++) {
            if (checkBoundary(boxs[i].x + x, boxs[i].y + y, mapsmodel)) return false;
        }
        for (int i = 0; i < boxs.length; i++) {
            boxs[i].x += x;
            boxs[i].y += y;
        }
        return true;
    }

    public boolean rotate(Mapsmodel mapsmodel) {
        if (boxType == 0) return false;
        /*else if (boxType == 3) {
            for (int i = 0; i < boxs.length; i++) {
                int checkX = boxs[i].x+i;
                int checkY = boxs[i].x;
                if (checkBoundary(checkX, checkY, mapsmodel)) return false;
            }
            for (int i = 0; i < boxs.length; i++) {
                int checkX = boxs[i].y;
                int checkY = boxs[i].x;
                boxs[i].x = checkX;
                boxs[i].y = checkY;
                return true;
            }
        }*/
        //笛卡尔公式
        else {
            for (int i = 0; i < boxs.length; i++) {
                int checkX = -boxs[i].y + boxs[0].y + boxs[0].x;
                int checkY = boxs[i].x - boxs[0].x + boxs[0].y;
                if (checkBoundary(checkX, checkY, mapsmodel))
                    return true;
            }
            for (int i = 0; i < boxs.length; i++) {
                int checkX = -boxs[i].y + boxs[0].y + boxs[0].x;
                int checkY = boxs[i].x - boxs[0].x + boxs[0].y;
                boxs[i].x = checkX;
                boxs[i].y = checkY;
            }
            return true;
        }
    }


    //边界判断
    public boolean checkBoundary(int x,int y,Mapsmodel mapsmodel) {
        return (x < 0 || y < 0 || x >=mapsmodel.maps.length || y>=mapsmodel.maps[0].length||mapsmodel.maps[x][y]==true);
    }
}
