package com.example.entrancepages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {
    private EditText eMail, password, forgotPassword;
    private Button logIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpUIViews();

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if ( user != null )
        {
            finish();
            startActivity( new Intent(Login.this, MainMenu.class));
        }

        logIn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                //validate( Name.getText().toString(), Password.getText().toString() );
            }
        });
    }

    private void setUpUIViews()
    {
        eMail = (EditText)findViewById(R.id.Email1);
        password = (EditText)findViewById(R.id.password);
        logIn = (Button)findViewById(R.id.logInButton);
        forgotPassword = (EditText)findViewById(R.id.ForgotPassword);
    }

    private void validate( String userName, String userPassword )
    {
        mAuth.createUserWithEmailAndPassword( userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful())
                {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity( new Intent(Login.this, MainMenu.class));
                }
                else
                {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
