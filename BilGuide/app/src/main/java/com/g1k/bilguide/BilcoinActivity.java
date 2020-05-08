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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Bilcoin activity
 * @author Erhan ER, Berk BALTACI
 * @version 1.0
 */
public class BilcoinActivity extends AppCompatActivity {

    private Button plus, minus, back;
    private TextView total;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilcoin);
        setUpUIViews();

        // creates a firebase authentication object
        mAuth = FirebaseAuth.getInstance();

        // creates a firebase database object
        mData = FirebaseDatabase.getInstance();

        // creates a reference to the database
        DatabaseReference mRef = mData.getReference(Objects.requireNonNull(mAuth.getUid()));

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                UserProfile user = dataSnapshot.getValue( UserProfile.class );
                plus.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        int foo;
                        foo = Integer.parseInt(total.getText().toString()) + 50;
                        total.setText( "" + foo );
                        ArrayList<String> array = new ArrayList<String>( Arrays.asList ( "A", "B", "EA", "EE", "FF", "G", "SA" ));

                        // updates the specific data
                        assert user != null;
                        dataSnapshot.getRef().child("bilcoin").setValue( user.getBilcoin() + 50 );
                        dataSnapshot.getRef().child( "buildings").setValue( array );
                    }
                });
                minus.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        int foo;
                        foo = Integer.parseInt(total.getText().toString()) - 50;
                        total.setText( "" + foo );

                        // updates the specific data
                        assert user != null;
                        dataSnapshot.getRef().child("bilcoin").setValue( user.getBilcoin() - 50 );
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( BilcoinActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity( new Intent( BilcoinActivity.this, MainMenuActivity.class));
            }
        });
    }

    /**
     * sets the buttons and text view
     */
    private void setUpUIViews()
    {
        plus = (Button)findViewById(R.id.adder);
        minus = (Button)findViewById(R.id.minus);
        total = (TextView) findViewById(R.id.total);
        back = (Button)findViewById(R.id.backButton);
    }
}
