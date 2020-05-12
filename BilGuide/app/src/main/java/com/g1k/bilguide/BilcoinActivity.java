package com.g1k.bilguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * BilcoinActivity class, gives users opportunities to buy several items from
 * the cafeterias in Bilkent University
 * @author Aslı Dinç, Berk Baltacı, Erhan Er, Furkan Turunç,
 *         Muhammet Abdullah Koç, Murat Furkan Uğurlu, Recep Uysal
 */
public class BilcoinActivity extends AppCompatActivity {

    // properties
    private Button               buttonSpend;
    private TextView             textViewBilcoinBalance;
    private TextView             textViewCode;
    private Spinner              spinnerCafes;
    private ArrayAdapter<String> adapterCafes;
    private ArrayList<String>    cafes;
    private int                  purchaseCode;
    private FirebaseAuth         mAuth;
    private FirebaseDatabase     mData;
    private UserProfile          user;

    // methods
    /**
     * OnCreate method, default method of android apps
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilcoin);

        // initializing firebase properties
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance();

        // initializing (ArrayList) cafes and adding cafes and items with their prices
        cafes = new ArrayList<String>();
        cafes.add( "Coffee Break - 1 Coffee(Optional) - 100 BilCoin");
        cafes.add( "Cafe In - 1 Pasta - 200 BilCoin");
        cafes.add( "Kıraç - 1 Soup - 150 BilCoin");
        cafes.add( "Speed - 1 Orange Juice - 100 BilCoin");
        cafes.add( "Mozart - 1 Toast with Cheese - 150 BilCoin");
        cafes.add( "Fameo - 1 Water Bottle - 80 BilCoin");
        cafes.add( "Bilka - 1 Cup of Tea - 50 BilCoin");

        // linking spend button and textViews that will show code and balance info
        buttonSpend = findViewById(R.id.buttonSpend);
        textViewBilcoinBalance = findViewById(R.id.textViewBilcoinBalance);
        textViewCode = findViewById(R.id.textViewCode);
        spinnerCafes = findViewById(R.id.spinnerCafes);

        // initializing adapterCafes
        adapterCafes = new ArrayAdapter<String>(getApplicationContext()
                , R.layout.spinneritem
                , android.R.id.text1
                , cafes);
        adapterCafes.setDropDownViewResource(R.layout.forspinner);
        spinnerCafes.setAdapter(adapterCafes);

        DatabaseReference mRef = mData.getReference(Objects.requireNonNull(mAuth.getUid()));

        // adding valueListener to have control over changes
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue( UserProfile.class );

                //show balance
                assert user != null;
                textViewBilcoinBalance.setText( "Your balance: " + user.getBilcoin() );

                if( user.getBilcoin() < 50) {
                    buttonSpend.setEnabled(false);
                }

                // setting clickListener to let the user spend
                buttonSpend.setOnClickListener(new View.OnClickListener() {
                    /**
                     * onClick method, lets users to spend Bilcoin and updates balance info
                     * according to the item that users select
                     * @param v, is a View object
                     */
                    @Override
                    public void onClick(View v) {
                        if(spinnerCafes.getSelectedItemPosition() == 0) {
                            //100 bilcoin decrease
                            if ( user.getBilcoin() >= 100 ) {
                                mRef.child("bilcoin").setValue( user.getBilcoin() - 100 );
                                purchaseCode = (int) (Math.random() * 89999) + 10001;
                                textViewCode.setText( "Your code is : " + purchaseCode);
                                Toast.makeText( BilcoinActivity.this, "Purchase is successful", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText( BilcoinActivity.this, "Your BilCoin is not enough", Toast.LENGTH_SHORT).show();
                        }
                        if(spinnerCafes.getSelectedItemPosition() == 1) {
                            //200 bilcoin decrease
                            if ( user.getBilcoin() >= 200 ) {
                                mRef.child("bilcoin").setValue( user.getBilcoin() - 200 );
                                purchaseCode = (int) (Math.random() * 89999) + 10001;
                                textViewCode.setText( "Your code is : " + purchaseCode);
                                Toast.makeText( BilcoinActivity.this, "Purchase is successful", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText( BilcoinActivity.this, "Your BilCoin is not enough", Toast.LENGTH_SHORT).show();
                        }
                        if(spinnerCafes.getSelectedItemPosition() == 2) {
                            //150 bilcoin decrease
                            if ( user.getBilcoin() >= 150 ) {
                                mRef.child("bilcoin").setValue( user.getBilcoin() - 150 );
                                purchaseCode = (int) (Math.random() * 89999) + 10001;
                                textViewCode.setText( "Your code is : " + purchaseCode);
                                Toast.makeText( BilcoinActivity.this, "Purchase is successful", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText( BilcoinActivity.this, "Your BilCoin is not enough", Toast.LENGTH_SHORT).show();
                        }
                        if(spinnerCafes.getSelectedItemPosition() == 3) {
                            //100 bilcoin decrease
                            if ( user.getBilcoin() >= 100 ) {
                                mRef.child("bilcoin").setValue( user.getBilcoin() - 100 );
                                purchaseCode = (int) (Math.random() * 89999) + 10001;
                                textViewCode.setText( "Your code is : " + purchaseCode);
                                Toast.makeText( BilcoinActivity.this, "Purchase is successful", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText( BilcoinActivity.this, "Your BilCoin is not enough", Toast.LENGTH_SHORT).show();
                        }
                        if(spinnerCafes.getSelectedItemPosition() == 4) {
                            //150 bilcoin decrease
                            if ( user.getBilcoin() >= 150 ) {
                                mRef.child("bilcoin").setValue( user.getBilcoin() - 150 );
                                purchaseCode = (int) (Math.random() * 89999) + 10001;
                                textViewCode.setText( "Your code is : " + purchaseCode);
                                Toast.makeText( BilcoinActivity.this, "Purchase is successful", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText( BilcoinActivity.this, "Your BilCoin is not enough", Toast.LENGTH_SHORT).show();
                        }
                        if(spinnerCafes.getSelectedItemPosition() == 5) {
                            //80 bilcoin decrease
                            if ( user.getBilcoin() >= 80 ) {
                                mRef.child("bilcoin").setValue( user.getBilcoin() - 80 );
                                purchaseCode = (int) (Math.random() * 89999) + 10001;
                                textViewCode.setText( "Your code is : " + purchaseCode);
                                Toast.makeText( BilcoinActivity.this, "Purchase is successful", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText( BilcoinActivity.this, "Your BilCoin is not enough", Toast.LENGTH_SHORT).show();
                        }
                        if(spinnerCafes.getSelectedItemPosition() == 6) {
                            //50 bilcoin decrease
                            if ( user.getBilcoin() >= 50 ) {
                                mRef.child("bilcoin").setValue( user.getBilcoin() - 50 );
                                purchaseCode = (int) (Math.random() * 89999) + 10001;
                                textViewCode.setText( "Your code is : " + purchaseCode);
                                Toast.makeText( BilcoinActivity.this, "Purchase is successful", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText( BilcoinActivity.this, "Your BilCoin is not enough", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            /**
             * onCancelled method is related to database process about cancellation
             * @param databaseError
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( BilcoinActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
