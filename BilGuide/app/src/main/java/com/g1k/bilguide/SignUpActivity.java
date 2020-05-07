package com.g1k.bilguide;

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

import java.util.Objects;

/**
 * Sign up activity
 * @author Erhan ER, Berk BALTACI
 * @version 1.0
 */

public class SignUpActivity extends AppCompatActivity {

    // variables
    private EditText userName, userEmail, userPassword;
    private Button signUp;
    private FirebaseAuth mAuth;
    private ProgressBar dialog;
    private String name, email, password;

    // program code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setUpUIViews();

        // creates a firebase authentication object
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

                    // creates the user with the given email and password
                    mAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                finish(); // destroys the current activity
                                sendUserData(); // sends data
                                sendEmailVerification(); // sends the verification email
                            }
                            else {
                                dialog.setVisibility( View.INVISIBLE);
                                Toast.makeText(SignUpActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            }
                        }
                    });
                }
            }
        });

    }

    /**
     * Sets up the buttons, edit texts and progress bar
     */
    private void setUpUIViews()
    {
        dialog = new ProgressBar(this);

        userName = findViewById(R.id.fullName);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        signUp = findViewById(R.id.SignUpButton2);
        dialog = findViewById(R.id.progressBar);
    }

    /**
     * gets the information from edit texts and returns true
     * if at least one of the edit texts is empty, return false
     * @return boolean
     */
    private boolean validate()
    {
        boolean valid = false;
        name = ( userName.getText() ).toString();
        password = ( userPassword.getText() ).toString();
        email = ( userEmail.getText() ).toString();

        if ( name.isEmpty() || password.isEmpty() || email.isEmpty() ) {
            Toast.makeText( this, "Please enter all the details!", Toast.LENGTH_SHORT).show();
        }
        else {
            valid = true;
        }
        return valid;
    }

    /**
     * creates a firebase database object, creates the user's data with the specific information and send the information to the data base
     */
    private void sendUserData() {

        // creates a firebase database object
        FirebaseDatabase data = FirebaseDatabase.getInstance();

        // creates a reference to the database
        DatabaseReference reference = data.getReference(Objects.requireNonNull(mAuth.getUid()));

        // creates the user's data
        UserProfile profile = new UserProfile( name, email );

        // set them into the database
        reference.setValue( profile );
    }

    /**
     * sends a verification email to registration email and sends the user to the main activity
     */
    private void sendEmailVerification()
    {
        FirebaseUser user = mAuth.getCurrentUser();

        // if the user available, sends the verification email
        if ( user != null ) {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if ( task.isSuccessful() ) {
                        Toast.makeText(SignUpActivity.this, "Registration Successful. Email sent", Toast.LENGTH_SHORT).show();
                        mAuth.signOut(); // signs out the user from the application to prevent unauthorized enters
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, "Email hasn't been sent", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
