package com.example.myapplication.control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.myapplication.Config;
import com.example.myapplication.R;
import com.example.myapplication.model.Boxsmodel;
import com.example.myapplication.model.Mapsmodel;
import com.example.myapplication.model.Scoremodel;

public class Gamecontrol {
    public boolean isPause;
    public boolean isOver;
    Context context;
    //定义方块模块
    public Boxsmodel boxsmodel;
    //定义地图模块
    public Mapsmodel mapsmodel;
    //定义分数模块
    public Scoremodel scoremodel;
    Handler handler;
    //自动下落线程
    public Thread downThread;
    //初始化游戏控制器
    public Gamecontrol(Handler handler,Context context) {
        this.handler = handler;
        this.context = context;
        InitDate();
    }

    public void InitDate() {
        int width = getScreenwidth(context);
        //初始化游戏界面宽度
        Config.xWidth = width * 2 /3;
        //初始化游戏界面高度
        Config.yHight =  Config.xWidth*2;
        //初始化内间距
        Config.PADDing = width/Config.SP_PADDING;
        int boxSize = Config.xWidth/ Config.MAPSX;
        //初始化地图模块
        mapsmodel = new Mapsmodel(boxSize, Config.xWidth, Config.yHight);
        //初始化方块模块
        boxsmodel = new Boxsmodel(boxSize);
       //初始化分数模块
        scoremodel =new Scoremodel(context);

    }

    public void startGame(int INITIAVILOCITY) {
        //清楚地图
        mapsmodel.cleanMaps();
       //初始化当前得分
        scoremodel.scoreNow = 0;
        //下落线程
        if (downThread == null)
        {downThread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                while (true)
                {
                    try {
                        sleep(INITIAVILOCITY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //判断是否暂停或结束
                    if (isPause||isOver)
                        continue;
                    //执行下落
                    downmove();
                    //刷新
                    Message msg = new Message();
                    msg.obj = "invalidate";
                    handler.sendMessage(msg);

                }
            }
        };
            downThread.start();

        }
        //游戏结束为否
        isOver = false;
        //游戏开始为否
        isPause = false;
        boxsmodel.newBoxs();


    }

    public boolean downmove() {
        int lines;
        //移动成功
        if(boxsmodel.move(0,1,mapsmodel))
            return true;
        //移动失败 堆积处理
        for (int i = 0;i<boxsmodel.boxs.length;i++)
            mapsmodel.maps[boxsmodel.boxs[i].x][boxsmodel.boxs[i].y] = true;
        //消行
         lines = mapsmodel.cleanLine();
        scoremodel.addscore(lines);
        scoremodel.updateScoreMax(isOver);
        //生成新方块
        boxsmodel.newBoxs();
        //游戏结束
        isOver = checkOver();
        //存储每一次游戏结束的成绩
        if(isOver&&scoremodel.scoreNow!=0) scoremodel.sqlUtils.putScore(scoremodel.scoreNow);
        return false;
    }

    public  boolean checkOver(){
        for (int i =0;i<boxsmodel.boxs.length;i++)
        {
            if(mapsmodel.maps[boxsmodel.boxs[i].x][boxsmodel.boxs[i].y])
                return true;
        }
        return false;
    }
    private void setPause() {
        Message msg = new Message();
        if(isPause){
            isPause = false;
            msg.obj = "pause";

        }else{
            isPause = true;
            msg.obj = "continue";
        }
        handler.sendMessage(msg);
    }
    public static int getScreenwidth(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
    public void draw(Canvas canvas) {
        //绘制地图
        mapsmodel.drawMaps(canvas);
        //绘制方块
        boxsmodel.drawBoxs(canvas);
        //绘制线条
        mapsmodel.drawLines(canvas);
        //绘制状态
        mapsmodel.drawState(canvas,isOver,isPause);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(int id, final int INITIAVILOCITY) {
        switch (id)
        {
            case  R.id.btnleft:
                if (!isPause)
                boxsmodel.move(-1,0,mapsmodel);
                break;
            case  R.id.btnright:
                if (!isPause)
                boxsmodel.move(1,0,mapsmodel);
                break;
            case  R.id.btnup:
                if (!isPause)
                boxsmodel.rotate(mapsmodel);
                break;
            case  R.id.btndown:
                if (!isPause&&!isOver)
                while(true)
                {  if(!downmove()) break;}
                break;
            case R.id.btndown2:
                if (!isPause&&!isOver)
                downmove();
                break;
            case  R.id.btnStart:
                startGame(INITIAVILOCITY);
                break;
            case  R.id.btnPause:
                setPause();
                break;
        }
    }

    public void drawNext(Canvas canvas, int width) {
        boxsmodel.drawNext(canvas,width);
    }

}
