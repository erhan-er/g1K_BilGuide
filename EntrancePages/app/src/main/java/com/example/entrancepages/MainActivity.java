package com.example.entrancepages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Main activity
 * @author Erhan ER, Berk BALTACI
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {

    // variables
    private Button signIn, signUp;
    private TextView needHelp;
    private FirebaseAuth mAuth;

    // program code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUIViews();

        // creates a firebase authentication object
        mAuth = FirebaseAuth.getInstance();

        // creates a firebase authentication user object
        FirebaseUser user = mAuth.getCurrentUser();

        // if there is an user which signed in before and didn't log out, the application will send the user directly to the main menu
        if ( user != null )
            startActivity( new Intent( MainActivity.this, MainMenuActivity.class ));

        // When user presses the sign up button, it will send the user to the sign up activity
        signUp.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view)
            {
                startActivity( new Intent(MainActivity.this, SignUpActivity.class ));
            }
        });

        // When the user presses sign in button, it will send the user to the log in activity
        signIn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                startActivity( new Intent(MainActivity.this, LoginActivity.class ));
            }
        });

        // When the user presses need help, it will send the user to the need help activity
        needHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( MainActivity.this, NeedHelpActivity.class ));
            }
        });
    }

    /**
     * Sets up the buttons and text view
     */
    private void setUpUIViews()
    {
        signIn = findViewById(R.id.signInButton);
        signUp = findViewById(R.id.signUpButton);
        needHelp = findViewById(R.id.needHelp);
    }
}
