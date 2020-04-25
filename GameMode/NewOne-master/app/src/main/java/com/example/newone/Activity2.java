package com.example.newone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    private Intent intent2;
    private Button buttonGo;
    private String buildingName;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        buttonGo = findViewById(R.id.buttonGo);
        textView = findViewById(R.id.textView);

        buildingName = getIntent().getStringExtra("answer");
        textView.setText(buildingName);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2 = new Intent(Activity2.this,Activity3.class);
                intent2.putExtra("answer",buildingName);
                startActivity(intent2);
            }
        });


    }
}
