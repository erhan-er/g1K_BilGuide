package com.g1k.bilguide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Log in activity
 * @author Erhan ER, Berk BALTACI
 * @version 1.0
 */

public class LoginActivity extends AppCompatActivity {

    // variables
    private EditText eMail, password;
    private TextView forgotPassword;
    private Button logIn;
    private FirebaseAuth mAuth;
    private ProgressDialog dialog;
    private int enters;

    // program code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpUIViews();
        enters = 5;

        // creates a firebase authentication object
        mAuth = FirebaseAuth.getInstance();

        // creates a firebase authentication user
        FirebaseUser user = mAuth.getCurrentUser();

        // creates a progress dialog
        dialog = new ProgressDialog( LoginActivity.this );

        logIn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                validate( eMail.getText().toString(), password.getText().toString());
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
    }

    /**
     * sets the button, edit texts and text view
     */
    private void setUpUIViews()
    {
        eMail = (EditText)findViewById(R.id.Email1);
        password = (EditText)findViewById(R.id.password);
        logIn = (Button)findViewById(R.id.logInButton);
        forgotPassword = (TextView)findViewById(R.id.ForgotPassword);
    }

    /**
     * It sign in the account if there is an account with the email and if the password is correct
     * If the user enters 5 times wrong password, the log in button will be locked
     * @param userName
     * @param userPassword
     */
    private void validate( String userName, String userPassword )
    {
        dialog.setMessage( "Please wait.");
        dialog.show();

        mAuth.signInWithEmailAndPassword( userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful()) {
                    dialog.dismiss();
                    checkEmailVerification();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    enters--;
                    dialog.dismiss();
                    if ( enters == 0 )
                        logIn.setEnabled(false);
                }
            }
        });

    }

    /**
     * checks if the email verified
     */
    private void checkEmailVerification() {
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        assert user != null;
        boolean situation = user.isEmailVerified();

        if ( situation ) {
            finish();
            startActivity( new Intent( LoginActivity.this, MainMenuActivity.class ));
        }
        else {
            Toast.makeText( this, "Verify your email", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }
    }
}
