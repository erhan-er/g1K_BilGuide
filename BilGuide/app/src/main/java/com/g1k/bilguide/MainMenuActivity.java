package com.g1k.bilguide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * Main menu activity
 * @author Erhan ER, Berk BALTACI
 * @version 1.0
 */

public class MainMenuActivity extends AppCompatActivity {

    // variables
    private TextView hello, coin;
    private Button game, info, logout, money;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mData;

    // program code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setUpUIViews();

        // creates a firebase authentication object
        mAuth = FirebaseAuth.getInstance();

        // creates a firebase database object
        mData = FirebaseDatabase.getInstance();

        // creates a reference to the database
        DatabaseReference ref = mData.getReference(Objects.requireNonNull(mAuth.getUid()));

        // gets the data from the database
        ref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserProfile profile = dataSnapshot.getValue( UserProfile.class );
                assert profile != null;
                coin.setText( "" + profile.getBilcoin() );
                hello.setText( "Hello " + profile.getUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( MainMenuActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity( new Intent( MainMenuActivity.this, MainActivity.class ));

                finish();
            }
        });

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( MainMenuActivity.this, BilcoinActivity.class));
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ReadyActivity.class));
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SelectBuilding.class));
            }
        });
    }

    /**
     * sets the buttons and text views
     */
    private void setUpUIViews()
    {
        money = (Button)findViewById(R.id.money);
        hello = (TextView)findViewById(R.id.hello);
        coin = (TextView)findViewById(R.id.bilcoin);
        game = (Button)findViewById(R.id.gameModeButton);
        info = (Button)findViewById(R.id.informationModeButton);
        logout = (Button)findViewById(R.id.logoutButton);
    }

}
