package com.example.admin215.gamesurface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "tag";
    MySurfaceView mySurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySurfaceView = new MySurfaceView(this);
        setContentView(mySurfaceView);
        int recieveX = getIntent().getIntExtra(TAG, 20);
        mySurfaceView.spriteArray.get(0).setX(recieveX);
        mySurfaceView.spriteArray.get(0).setY(recieveX);

    }
}
