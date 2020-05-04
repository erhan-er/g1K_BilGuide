package com.example.newone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity4 extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private int balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        balance = getIntent().getIntExtra("balance", 0);
        textView.setText("Congratulations! ");
        textView2.setText("Bilcoin Earned: " + (balance-20));
        textView3.setText("New Balance: " + balance);
    }
}
