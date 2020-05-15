package com.g1k.bilguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * GameModeBalance class, shows updated Bilcoin information
 * @author Furkan Turunç, Murat Furkan Uğurlu, Recep Uysal
 * @version 1.0
 */
public class GameModeBalance extends AppCompatActivity {

    // properties
    private TextView         textView;
    private TextView         textView2;
    private int              balance;
    private Button           buttonMainMenu;
    private FirebaseAuth     mAuth;
    private FirebaseDatabase mData;

    // methods
    /**
     * This methods is the default method of android studio, which applies main process for widgets.
     * @param savedInstanceState , a Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_balance);

        // Linking textViews that will show Bilcoin updates and congratulations message
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        // Linking main menu button
        buttonMainMenu = findViewById(R.id.buttonMainMenu);

        // Initializing firebase objects
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance();
        DatabaseReference mRef = mData.getReference(Objects.requireNonNull(mAuth.getUid()));

        // Initializing balance
        balance = getIntent().getIntExtra("balance", 0);

        // if balance is greater than 0, print congratulations message
        // and updated Bilcoin information
        if(balance >= 0) {
            textView.setText("Congratulations! ");
            textView2.setText("Bilcoin Earned: " + (balance));
        }

        // Otherwise, if balance is less than 0, then print message
        // and updated Bilcoin information
        else {
            textView.setText("Nice Try! ");
            textView2.setText("Bilcoin Lost: " + (-balance));
        }

        // Adding valueEventListener to have control over changes
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile user = dataSnapshot.getValue( UserProfile.class );
                assert user != null;

                // initializing foo that represents new Bilcoin info
                int foo = user.getBilcoin() + balance;

                // adding clickListener for main menu button
                buttonMainMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        // directing the user to the main menu
                        startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                        dataSnapshot.getRef().child("bilcoin").setValue( foo );
                    }
                });
            }

            /**
             * onCancelled method is related to database process about cancellation
             * @param databaseError is DatabaseError object and symbolizes data base errors
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( GameModeBalance.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}