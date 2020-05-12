package com.example.newone;

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

import java.util.ArrayList;
/*
 * This class shows a sample question about a building
 * @author Furkan, Murat Furkan, Recep
 * @version 1.0
 */

public class GameModeMain extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // variables
    private Spinner spinner;
    private Button buttonGo;
    private String answerSelected;
    private ArrayList<String> questionList;
    private TextView questionText;
    private Intent intent;
    private int index;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> answerList;

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
        setContentView(R.layout.activity_game_mode_main);
        answerList = new ArrayList<String>();
        // Adding the options
        answerList.add("-");
        answerList.add("A");
        answerList.add("B");
        answerList.add("EA");
        answerList.add("EE");
        answerList.add("FF");
        answerList.add("G");
        answerList.add("SA");
        answerList.add("V");
        questionList = new ArrayList<String>();
        // Adding the questions
        questionList.add("Where is Faculty of Economics, Administrative, and Social Sciences?");
        questionList.add("Where is Faculty of Law?");
        questionList.add("Where is Faculty of   Engineering?");
        questionList.add("Where is Department of Electrical and Electronic Engineering?");
        questionList.add("Where is Faculty of Art, Design and Architecture?");
        questionList.add("Where is Faculty of Education?");
        questionList.add("Where is Faculty of Science?");
        questionList.add("Which building has the largest lecture halls on the campus?");
        questionText = findViewById(R.id.questionText); // sets the question text
        // This is for showing the building questions randomly
        index = ((int)(questionList.size()*Math.random()));
        questionText.setText(questionList.get(index));
        // Linking the variables to widgets in layouts
        spinner = findViewById(R.id.spinner);
        buttonGo = findViewById(R.id.buttonGo2);
        // When the button go was pressed, this method works
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if the user gives correct answer, then he/she can pass second page
                if(checkAnswer()) {
                    intent = new Intent(GameModeMain.this, GameModeMap.class);
                    // Putting the selected answer to the next page
                    intent.putExtra("answer",answerSelected);
                    startActivity(intent);
                }
            }
        });
        // Setting the adapter for spinner
        adapter = new ArrayAdapter<String>(getApplicationContext()
                , android.R.layout.simple_list_item_1
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
    public boolean checkAnswer() {
        // For building A
        if (index == 0 && answerSelected.equals("A")) {
            return true;
        }
        // For building B
        else if (index == 1 && answerSelected.equals("B")) {
            return true;
        }
        // For building EA
        else if (index == 2 && answerSelected.equals("EA")) {
            return true;
        }
        // For building EE
        else if (index == 3 && answerSelected.equals("EE")) {
            return true;
        }
        // For building FF
        else if (index == 4 && answerSelected.equals("FF")) {
            return true;
        }
        // For building G
        else if (index == 5 && answerSelected.equals("G")) {
            return true;
        }
        // For building SA
        else if (index == 6 && answerSelected.equals("SA")) {
            return true;
        }
        // For building V
        else if (index == 7 && answerSelected.equals("V")) {
            return true;
        }
        // This returns true not to view the message of wrong answer on the screen when this item selected
        else if (answerSelected.equals("-"))
            return true;
        // This means the answer is wrong
        else
            return false;
    }
}
