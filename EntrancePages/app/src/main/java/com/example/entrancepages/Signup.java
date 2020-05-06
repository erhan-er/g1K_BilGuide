package com.example.entrancepages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends AppCompatActivity {

    private EditText userName, userEmail, userPassword;
    private Button signUp;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private ProgressBar dialog;
    String name, email, password;

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
                if ( validate() ) {
                    dialog.setVisibility( View.VISIBLE);
                    String user_email = userEmail.getText().toString();
                    String user_password = userPassword.getText().toString();
                    mAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendUserData();
                                mAuth.signOut();
                                sendEmailVerification();
                            } else {
                                dialog.setVisibility( View.INVISIBLE);
                                Toast.makeText(Signup.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Signup.this, MainActivity.class));
                            }
                        }
                    });
                }
            }
        });

    }

    private void setUpUIViews()
    {
        dialog = new ProgressBar(this);

        userName = (EditText)findViewById(R.id.fullName);
        userEmail = (EditText)findViewById(R.id.userEmail);
        userPassword = (EditText)findViewById(R.id.userPassword);
        signUp = (Button)findViewById(R.id.SignUpButton2);
        dialog = (ProgressBar)findViewById(R.id.progressBar);
    }

    private boolean validate()
    {
        Boolean valid = false;
        name = ( userName.getText() ).toString();
        password = ( userPassword.getText() ).toString();
        email = ( userEmail.getText() ).toString();

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

    private void sendUserData() {
        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference reference = data.getReference( mAuth.getUid());
        UserProfile profile = new UserProfile( name, email );
        reference.setValue( profile );
    }

    private void sendEmailVerification()
    {
        FirebaseUser user = mAuth.getCurrentUser();
        if ( user != null )
        {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if ( task.isSuccessful() )
                    {
                        Toast.makeText(Signup.this, "Registration Successful. Email sent", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        startActivity(new Intent(Signup.this, MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(Signup.this, "Email hasn't been sent", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
