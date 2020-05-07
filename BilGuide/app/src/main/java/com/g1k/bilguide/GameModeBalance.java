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

public class GameModeBalance extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private int balance;
    private Button buttonMainMenu;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_balance);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        buttonMainMenu = findViewById(R.id.buttonMainMenu);

        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance();
        DatabaseReference mRef = mData.getReference(Objects.requireNonNull(mAuth.getUid()));

        balance = getIntent().getIntExtra("balance", 0);
        if(balance >= 0) {
            textView.setText("Congratulations! ");
            textView2.setText("Bilcoin Earned: " + (balance));
        }
        else {
            textView.setText("Nice Try! ");
            textView2.setText("Bilcoin Lost: " + (-balance));
        }

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile user = dataSnapshot.getValue( UserProfile.class );
                assert user != null;
                int foo = user.getBilcoin() + balance;
                textView3.setText( "New Balance: " + foo );

                buttonMainMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataSnapshot.getRef().child("bilcoin").setValue( foo );
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( GameModeBalance.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
