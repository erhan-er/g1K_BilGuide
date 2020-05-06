package com.g1k.bilguide;

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
import com.google.firebase.auth.FirebaseAuth;

/**
 * __program description___
 * @author Erhan ER, Berk BALTACI
 * @version 1.0
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText resetEmail;
    private Button resetButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setUpUIViews();

        mAuth = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eMail = resetEmail.getText().toString().trim();

                if ( eMail.equals("") )
                {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter your Email", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.sendPasswordResetEmail( eMail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if ( task.isSuccessful())
                            {
                                Toast.makeText( ForgotPasswordActivity.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity( new Intent( ForgotPasswordActivity.this, MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText( ForgotPasswordActivity.this, "Password reset email didn't send!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity( new Intent( ForgotPasswordActivity.this, MainActivity.class));
                            }
                        }
                    });
                }

            }
        });

    }

    private void setUpUIViews()
    {
        resetEmail = (EditText)findViewById(R.id.resetEmail);
        resetButton = (Button)findViewById(R.id.Reset);
    }

}
