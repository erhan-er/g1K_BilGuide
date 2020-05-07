package com.g1k.bilguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ReadyActivity extends AppCompatActivity {

    private Timer timer;
    private TimerTask timerTask;
    private TextView textViewCount;
    private double doubleCount;
    private static final int DELAY = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);

        textViewCount = findViewById(R.id.textViewCount);
        doubleCount = 3;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(doubleCount != 0){
                    textViewCount.setText( (int)doubleCount + "");
                    doubleCount = doubleCount - 0.003;
                }
                else{
                    return;
                }
            }
        };
        timer.schedule(timerTask, 1000,3);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToGame = new Intent( getApplicationContext(), GameModeMain.class);
                startActivity(goToGame);
                finish();
            }
        }, DELAY);
    }
}
