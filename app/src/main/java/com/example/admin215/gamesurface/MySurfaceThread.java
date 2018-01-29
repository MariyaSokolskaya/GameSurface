package com.example.admin215.gamesurface;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Admin215 on 18.01.2016.
 */
public class MySurfaceThread extends Thread {

    SurfaceHolder mHolder;
    MySurfaceView mySurfaceView;
    long prevTime;
    boolean threadRunning;
    int idThread;

    public MySurfaceThread (SurfaceHolder holder, MySurfaceView surfaceView, int id){
        mHolder = holder;
        mySurfaceView = surfaceView;
        prevTime = System.currentTimeMillis();
        idThread = id;
    }

    public void setRunning(boolean running){
        threadRunning = running;
    }

    @Override
    public void run(){
        while (threadRunning) {
            Canvas canvas = null;
            long nowTime = System.currentTimeMillis();
            long ellapsedTime = nowTime - prevTime;
            if(ellapsedTime > 50){
                prevTime = nowTime;
                canvas = mHolder.lockCanvas();
                synchronized (mHolder){
                    if (idThread == 0)
                        mySurfaceView.draw(canvas);
                    if (idThread == 1)
                        mySurfaceView.labirinth(canvas);
                }
                if(canvas != null)
                    mHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
