package com.g1k.bilguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.Arrays;
import java.util.Objects;

public class GameModeMain extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Button buttonGo;
    private String answerSelected;
    private TextView questionText;
    private Intent intent;
    private int index;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> answerList;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mData;
    private UserProfile user;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        answerSelected = parent.getItemAtPosition(position).toString();
        if (!checkAnswer()) {
            buttonGo.setEnabled(false);
            Toast.makeText(parent.getContext(), "Wrong answer", Toast.LENGTH_SHORT).show();
        }
        else {
            if (answerSelected.equals("-")) {
                buttonGo.setEnabled(false);
            } else {
                Toast.makeText(parent.getContext(), "Go", Toast.LENGTH_SHORT).show();
                buttonGo.setEnabled(true);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_main);

        questionText = findViewById(R.id.questionText);
        spinner = findViewById(R.id.spinner);
        buttonGo = findViewById(R.id.buttonGo2);

        answerList = new ArrayList<String>( Arrays.asList( "-", "A", "B", "EA", "EE", "FF", "G", "SA", "V"));

        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance();

        DatabaseReference mRef = mData.getReference(Objects.requireNonNull(mAuth.getUid()));



        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue( UserProfile.class );

                assert user != null;
                ArrayList questionList = user.getBuildings();
                index = ( int )( ( user.getSizeOfBuildings() - 1 ) * Math.random() );
                questionText.setText( user.getQuestion( index) );



                buttonGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkAnswer()) {
                            intent = new Intent(GameModeMain.this, GameModeMap.class);
                            intent.putExtra("answer",answerSelected);
                            questionList.remove( index );
                            dataSnapshot.getRef().child("buildings").setValue( questionList );
                            dataSnapshot.getRef().child( "sizeOfBuildings" ).setValue( user.getSizeOfBuildings() );
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( GameModeMain.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new ArrayAdapter<String>(getApplicationContext()
                , R.layout.forspinner
                , android.R.id.text1
                , answerList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean checkAnswer(){
        if ( user.getQuestion( index ).equals("Where is Faculty of Economics, Administrative, and Social Sciences?") && answerSelected.equals("A")){
            return true;
        }
        else if ( user.getQuestion( index ).equals("Where is Faculty of Law?") && answerSelected.equals("B")) {
            return true;
        }
        else if (user.getQuestion(index ).equals("Where is Faculty of   Engineering?") && answerSelected.equals("EA")) {
            return true;
        }
        else if (user.getQuestion(index).equals("Where is Department of Electrical and Electronic Engineering?") && answerSelected.equals("EE")) {
            return true;
        }
        else if (user.getQuestion(index).equals("Where is Faculty of Art, Design and Architecture?") && answerSelected.equals("FF")) {
            return true;
        }
        else if ( user.getQuestion(index).equals("Where is Faculty of Education?") && answerSelected.equals("G")) {
            return true;
        }
        else if (user.getQuestion(index).equals("Where is Faculty of Science?") && answerSelected.equals("SA")) {
            return true;
        }
        else if (user.getQuestion(index).equals("Which building has the largest lecture halls on the campus?") && answerSelected.equals("V")) {
            return true;
        }
        else if(answerSelected.equals("-"))
            return true;
        else
            return false;
    }
}
