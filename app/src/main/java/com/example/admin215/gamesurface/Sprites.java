package com.example.admin215.gamesurface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Admin215 on 26.01.2016.
 */
public class Sprites {
    MySurfaceView mySurfaceView;
    Bitmap image;
    int x, y;
    float dx, dy;
    int height, width;
    Paint paint = new Paint();
    final int IMAGE_ROWS = 4;
    final int IMAGE_COLUMNS = 3;
    int currentFrame = 0;
    int direction = 1;

    public Sprites(Bitmap image, MySurfaceView surfaceView, int x, int y){
        mySurfaceView = surfaceView;
        this.image = image;
        width = this.image.getWidth() / IMAGE_COLUMNS;
        height = this.image.getHeight() / IMAGE_ROWS;
        this.x = x;
        this.y = y;
    }

    public void controlRoute(){
        if(x < 0 || x > mySurfaceView.getWidth() - image.getWidth())
            dx = -dx;
        if(y < 0 || y > mySurfaceView.getHeight() - image.getHeight())
            dy = -dy;
        x += dx;
        y += dy;
        currentFrame = ++currentFrame % IMAGE_COLUMNS;
    }

    public void setSpeed(float sX, float sY){
        dx = sX;
        dy = sY;
    }

    public void draw(Canvas canvas){
        controlRoute();
        Rect src = new Rect(currentFrame*width, direction*height, currentFrame*width+width, direction*height+height);
        Rect dst = new Rect(x, y, x+width, y+height);
        canvas.drawBitmap(image, src, dst, paint);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
