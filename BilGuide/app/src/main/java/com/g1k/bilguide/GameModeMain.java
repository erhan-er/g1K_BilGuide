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

    // variables
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

    // methods
    /*
     * This methods works at which an item is selected in spinner
     * @param parent, a View object, position of selected item, id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Getting the selected answer
        answerSelected = parent.getItemAtPosition(position).toString();
        // If the answer is wrong
        if (!checkAnswer()) {
            // Setting the button go unavailable
            buttonGo.setEnabled(false);
            // Setting toast message "wrong answer" on screen
            Toast.makeText(parent.getContext(), "Wrong answer", Toast.LENGTH_SHORT).show();
        }
        else {
            if (answerSelected.equals("-")) {
                // Setting the button go unavailable
                buttonGo.setEnabled(false);
            } else {
                // Setting toast message "Go" on screen
                Toast.makeText(parent.getContext(), "Go", Toast.LENGTH_SHORT).show();
                // Setting the button go available
                buttonGo.setEnabled(true);
            }
        }
    }

    /*
     *This methods is the default method of android studio, which applies main process for widgets.
     * @param parent, a View object, position of selected item, id
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_main); // displays particular layout

        // Linking the variables to widgets in layouts
        questionText = findViewById(R.id.questionText);
        spinner = findViewById(R.id.spinner);
        buttonGo = findViewById(R.id.buttonGo2);

        answerList = new ArrayList<String>( Arrays.asList( "-", "A", "B", "EA", "EE", "FF", "G", "SA", "V"));
        // Database things
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance();

        DatabaseReference mRef = mData.getReference(Objects.requireNonNull(mAuth.getUid()));

        // This controls the app when  data changes
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue( UserProfile.class );

                assert user != null;
                ArrayList questionList = user.getBuildings(); // this gets the list of buildings from database
                index = ( int )( ( user.getSizeOfBuildings() - 1 ) * Math.random() ); // this is for showing the building questions randomly
                questionText.setText( user.getQuestion( index) ); // sets the question text

                // When the button go was pressed, this method works
                buttonGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // if the user gives correct answer, then he/she can pass second page
                        if(checkAnswer()) {
                            intent = new Intent(GameModeMain.this, GameModeMap.class);
                            intent.putExtra("answer",answerSelected);
                            // when a question was answered, it does not seem anymore in other tries
                            // to protect the user from solving the same question again.
                            questionList.remove( index );
                            dataSnapshot.getRef().child("buildings").setValue( questionList );
                            dataSnapshot.getRef().child( "sizeOfBuildings" ).setValue( user.getSizeOfBuildings() );
                            startActivity(intent);
                        }
                    }
                });
            }
            /*
             * This method is related to database process about cancellation
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( GameModeMain.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
        // setting the adapter for spinner
        adapter = new ArrayAdapter<String>(getApplicationContext()
                , R.layout.forspinner
                , android.R.id.text1
                , answerList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    // This method makes nothing when no item was selected in spinner
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*
     * This method checks the answer by comparing the question with the selected answer.
     * @return if the answer is correct, this method returns true, otherwise returns false
     */
    public boolean checkAnswer(){
        // For building A
        if ( user.getQuestion( index ).equals("Where is Faculty of Economics, Administrative, and Social Sciences?") && answerSelected.equals("A")){
            return true;
        }
        // For building B
        else if ( user.getQuestion( index ).equals("Where is Faculty of Law?") && answerSelected.equals("B")) {
            return true;
        }
        // For building EA
        else if (user.getQuestion(index ).equals("Where is Faculty of   Engineering?") && answerSelected.equals("EA")) {
            return true;
        }
        // For building EE
        else if (user.getQuestion(index).equals("Where is Department of Electrical and Electronic Engineering?") && answerSelected.equals("EE")) {
            return true;
        }
        // For building FF
        else if (user.getQuestion(index).equals("Where is Faculty of Art, Design and Architecture?") && answerSelected.equals("FF")) {
            return true;
        }
        // For building G
        else if ( user.getQuestion(index).equals("Where is Faculty of Education?") && answerSelected.equals("G")) {
            return true;
        }
        // For building SA
        else if (user.getQuestion(index).equals("Where is Faculty of Science?") && answerSelected.equals("SA")) {
            return true;
        }
        // For building V
        else if (user.getQuestion(index).equals("Which building has the largest lecture halls on the campus?") && answerSelected.equals("V")) {
            return true;
        }
        // This returns true not to view the message of wrong answer on the screen when this item selected
        else if(answerSelected.equals("-"))
            return true;
        // This means the answer is wrong
        else
            return false;
    }
}
