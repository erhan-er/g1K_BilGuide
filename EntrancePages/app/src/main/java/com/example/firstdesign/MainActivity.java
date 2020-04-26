package com.example.firstdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView needHelp;
    private Button signUpButton, signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUIViews();

        signUpButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity( new Intent( MainActivity.this, SignUp.class));
            }
        });

        signInButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity( new Intent( MainActivity.this, Login.class));
            }
        });

        needHelp.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }

    private void setUIViews()
    {
        signUpButton = (Button)findViewById(R.id.signupbtn);
        signInButton = (Button)findViewById(R.id.signInBtn);
        needHelp = (TextView)findViewById(R.id.needHelpTxt);
    }
}
