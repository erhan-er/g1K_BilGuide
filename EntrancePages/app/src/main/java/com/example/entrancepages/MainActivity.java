package com.example.entrancepages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button signIn, signUp;
    private TextView needHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUIViews();

        signUp.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view)
            {
                startActivity( new Intent(MainActivity.this, Signup.class ));
            }
        });

        signIn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                startActivity( new Intent(MainActivity.this, Login.class ));
            }
        });
    }

    private void setUpUIViews()
    {
        signIn = (Button)findViewById(R.id.signInButton);
        signUp = (Button)findViewById(R.id.signUpButton);
        needHelp = (TextView)findViewById(R.id.needHelp);
    }
}
