package com.example.admin215.gamesurface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlaceActivity extends AppCompatActivity {
    EditText text;
    Button setButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        setButton = (Button)findViewById(R.id.setX);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = (EditText) findViewById(R.id.koordinata);
                Intent intent = new Intent(PlaceActivity.this, MainActivity.class);
                int sendX = Integer.parseInt(text.getText().toString());
                intent.putExtra(MainActivity.TAG, sendX);
                startActivity(intent);
            }
        });
    }
}
