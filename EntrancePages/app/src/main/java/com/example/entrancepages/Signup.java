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


public class Signup extends AppCompatActivity {

    private EditText userName, userEmail, userPassword;
    private Button signUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setUpUIViews();

        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                if ( validate() );
                    // Upload data to database
                String user_email = userEmail.getText().toString().trim();
                String user_password = userPassword.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword( user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Signup.this, MainActivity.class));
                        }
                        else
                            Toast.makeText(Signup.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

//        signUp.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Signup.this, MainActivity.class));
//            }
//        });
    }

    private void setUpUIViews()
    {
        userName = (EditText)findViewById(R.id.fullName);
        userEmail = (EditText)findViewById(R.id.userEmail);
        userPassword = (EditText)findViewById(R.id.userPassword);
        signUp = (Button)findViewById(R.id.SignUpButton2);
    }

    private boolean validate()
    {
        Boolean valid = false;

        String name = ( userName.getText() ).toString();
        String password = ( userPassword.getText() ).toString();
        String email = ( userEmail.getText() ).toString();

        if ( name.isEmpty() || password.isEmpty() || email.isEmpty() )
        {
            Toast.makeText( this, "Please enter all the details!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            valid = true;
        }
        return valid;
    }
}
