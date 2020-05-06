package com.g1k.bilguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * __program description___
 * @author Erhan ER, Berk BALTACI
 * @version 1.0
 */

public class NeedHelpActivity extends AppCompatActivity {

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_help);

        buttonBack = (Button)findViewById(R.id.buttonNeedHelpBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NeedHelpActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
