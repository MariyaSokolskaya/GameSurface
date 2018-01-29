package com.example.admin215.gamesurface;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Admin215 on 18.01.2016.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    MySurfaceThread thread, thread1;
    Bitmap image,wall;
    Resources resources;
    int x = 0,y = 0;
    int wallX = 500, wallY = 500;
    int dx, dy, widthScreen;
    int targetX, targetY;
    float speedX = 3, speedY = 5;
    Paint paint = new Paint();
    int widthView;
    boolean drawing = false;
    Rect fRect, wRect;
    //Sprites sprite;
    ArrayList<Sprites> spriteArray = new ArrayList<Sprites>();

    public MySurfaceView(Context context) {
        super(context);
        info();
    }

    private void info(){
        getHolder().addCallback(this);
        setFocusable(true);
        resources = getResources();
        image = BitmapFactory.decodeResource(resources, R.drawable.sprites);
        wall = BitmapFactory.decodeResource(resources, R.drawable.wall);
        paint.setColor(Color.GREEN);
        drawing = true;
        wRect = new Rect(wallX, wallY, wallX+wall.getWidth(), wallY+wall.getHeight());
        fRect = new Rect(x,y, x+image.getWidth(), y+image.getHeight());
        spriteArray.add(new Sprites(image, this, 10, 10));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MySurfaceThread(getHolder(), this, 0);
        thread.setRunning(true);
        thread.start();
        thread1 = new MySurfaceThread(getHolder(), this, 1);
        thread1.setRunning(true);
        thread1.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry){
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void labirinth(Canvas canvas){
        canvas.drawBitmap(wall, wallX, wallY, paint);
        //wallX = 1000; wallY = 1000;
        //canvas.drawBitmap(wall, wallX, wallY, paint);
        thread1.setRunning(false);

    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        labirinth(canvas);
        //canvas.drawBitmap(image, x, y, paint);
        //x += speedX;
        //y += speedY;
        //fRect.set(x,y, x+image.getWidth(), y+image.getHeight());
        //if(checkCrash(fRect, wRect)){
        //    speedX = 0;
        //    speedY = 0;
        //}
        for (int i = 0; i<spriteArray.size(); i++)
            spriteArray.get(i).draw(canvas);
        widthScreen = canvas.getWidth();

    }



    public boolean checkCrash(Rect r1, Rect r2){
        if(Rect.intersects(r1, r2)){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent (MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            targetX = (int)event.getX();
            targetY = (int)event.getY();
            spriteArray.add(new Sprites(image, this, targetX, targetY));
            Iterator<Sprites> iterator = spriteArray.iterator();
            while (iterator.hasNext()){
                Sprites sprite;
                sprite = iterator.next();
                speedX = (targetX - sprite.x)/(float)widthScreen*40;
                speedY = (targetY - sprite.y)/(float)widthScreen*40;
                sprite.setSpeed(speedX,speedY);
            }
        }
        return true;
    }
}
