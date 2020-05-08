package com.g1k.bilguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BilcoinActivity<Firebase> extends AppCompatActivity {

    private Button buttonSpend;
    private TextView textViewBilcoinBalance;
    private TextView textViewCode;
    private Spinner spinnerCafes;
    private ArrayAdapter<String> adapterCafes;
    private ArrayList<String> cafes;
    private int purchaseCode;
    private Firebase mAuth;
    private FirebaseDatabase mData;
    private UserProfile user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilcoin);

        cafes = new ArrayList<String>();
        cafes.add( "Coffee Break - 1 Coffee(Optional) - 100 BilCoin");
        cafes.add( "Cafe In - 1 Pasta - 200 BilCoin");
        cafes.add( "Kıraç - 1 Soup - 150 BilCoin");
        cafes.add( "Speed - 1 Orange Juice - 100 BilCoin");
        cafes.add( "Mozart - 1 Toast with Cheese - 150 BilCoin");
        cafes.add( "Fameo - 1 Water Bottle - 80 BilCoin");
        cafes.add( "Bilka - 1 Cup of Tea - 50 BilCoin");

        buttonSpend = findViewById(R.id.buttonSpend);
        textViewBilcoinBalance = findViewById(R.id.textViewBilcoinBalance);
        textViewCode = findViewById(R.id.textViewCode);
        spinnerCafes = findViewById(R.id.spinnerCafes);


        adapterCafes = new ArrayAdapter<String>(getApplicationContext()
                , R.layout.spinneritem
                , android.R.id.text1
                , cafes);
        adapterCafes.setDropDownViewResource(R.layout.forspinner);
        spinnerCafes.setAdapter(adapterCafes);

        //show balance
        textViewBilcoinBalance.setText( "Your balance: ");

        if(balance < 50){
            buttonSpend.setEnabled(false);
        }

        DatabaseReference mRef = mData.getReference( mAuth.getUid());
        buttonSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinnerCafes.getSelectedItemPosition() == 0){
                    //100 bilcoin decrease
                    purchaseCode = (int) (Math.random() * 89999) + 10001;
                    textViewCode.setText( "Your code is : " + purchaseCode);
                }
                if(spinnerCafes.getSelectedItemPosition() == 1){
                    //200 bilcoin decrease
                    purchaseCode = (int) (Math.random() * 89999) + 10001;
                    textViewCode.setText( "Your code is : " + purchaseCode);
                }
                if(spinnerCafes.getSelectedItemPosition() == 2){
                    //150 bilcoin decrease
                    purchaseCode = (int) (Math.random() * 89999) + 10001;
                    textViewCode.setText( "Your code is : " + purchaseCode);
                }
                if(spinnerCafes.getSelectedItemPosition() == 3){
                    //100 bilcoin decrease
                    purchaseCode = (int) (Math.random() * 89999) + 10001;
                    textViewCode.setText( "Your code is : " + purchaseCode);
                }
                if(spinnerCafes.getSelectedItemPosition() == 4){
                    //150 bilcoin decrease
                    purchaseCode = (int) (Math.random() * 89999) + 10001;
                    textViewCode.setText( "Your code is : " + purchaseCode);
                }
                if(spinnerCafes.getSelectedItemPosition() == 5){
                    //80 bilcoin decrease
                    purchaseCode = (int) (Math.random() * 89999) + 10001;
                    textViewCode.setText( "Your code is : " + purchaseCode);
                }
                if(spinnerCafes.getSelectedItemPosition() == 6){
                    //50 bilcoin decrease
                    purchaseCode = (int) (Math.random() * 89999) + 10001;
                    textViewCode.setText( "Your code is : " + purchaseCode);
                }
            }
        });

    }
}
