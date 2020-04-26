package com.example.firstdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText userName, userPassword, userEmail;
    private Button signUpButton, logInButton;
    private FirebaseAuth fireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setUIViews();

        fireBaseAuth = FirebaseAuth.getInstance();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( validate() )
                {
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    fireBaseAuth.createUserWithEmailAndPassword( user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if ( task.isSuccessful())
                            {
                                Toast.makeText( SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity( new Intent( SignUp.this, Login.class));
                            }
                            else
                            {
                                Toast.makeText( SignUp.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        logInButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity( new Intent( SignUp.this, Login.class));
            }
        });
    }

    private void setUIViews()
    {
        userName = ( EditText )findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        signUpButton = (Button)findViewById(R.id.register);
        logInButton = (Button)findViewById(R.id.btnLogInButton);
    }

    private Boolean validate()
    {
        Boolean result;
        result = false;

        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String eMail = userEmail.getText().toString();

        if ( name.isEmpty() && password.isEmpty() && eMail.isEmpty() )
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
