package com.example.firstdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText userPassword, userEmail;
    private Button btn2LogInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUIViews();

        btn2LogInButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if ( !validate() ) {
                    
                }
                else
                {
                    startActivity(new Intent(Login.this, MainMenu.class));
                }
            }
        });
    }

    private void setUIViews()
    {
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        btn2LogInButton = (Button)findViewById(R.id.btn2LogInButton);
    }

    private Boolean validate()
    {
        Boolean result;
        result = false;

        String eMail = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if ( password.isEmpty() && eMail.isEmpty() )
        {
            Toast.makeText( this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        return result;
    }
}
