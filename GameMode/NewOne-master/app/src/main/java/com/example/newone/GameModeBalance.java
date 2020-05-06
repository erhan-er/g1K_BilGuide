package com.example.newone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameModeBalance extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private int balance;
    private Button buttonMainMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_balance);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        buttonMainMenu = findViewById(R.id.buttonMainMenu);
        balance = getIntent().getIntExtra("balance", 0);
        textView.setText("Congratulations! ");
        if(balance >= 20)
            textView2.setText("Bilcoin Earned: " + (balance-20));
        else
            textView2.setText("Bilcoin Lost: " + (20-balance));
        textView3.setText("New Balance: " + balance);
    }
}
