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
    private Timer timer2;
    private TimerTask timerTask;
    private TimerTask timerTask2;
    private TextView textViewCount;
    private double doubleCount;
    private static final int DELAY = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);
        textViewCount = findViewById(R.id.textViewCount);
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                textViewCount.setText( (int)doubleCount + "");
            }
        };
        timer.schedule(timerTask, 1000,3);

        doubleCount = 3;
        timer2 = new Timer();
        timerTask2 = new TimerTask() {
            @Override
            public void run() {
                if(doubleCount != 0){
                    doubleCount = doubleCount - 0.0035;

                }
                else{
                    return;
                }
            }
        };

        timer2.schedule(timerTask2, 1000, 3);

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
