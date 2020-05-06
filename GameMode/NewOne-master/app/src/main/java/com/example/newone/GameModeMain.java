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

public class GameModeMain extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private Button buttonGo;
    private String answerSelected;
    private ArrayList<String> questionList;
    private TextView questionText;
    private Intent intent;
    private int index;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> answerList;
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
        answerList = new ArrayList<String>();
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
        questionList.add("Where is Faculty of Economics, Administrative, and Social Sciences?");
        questionList.add("Where is Faculty of Law?");
        questionList.add("Where is Faculty of   Engineering?");
        questionList.add("Where is Department of Electrical and Electronic Engineering?");
        questionList.add("Where is Faculty of Art, Design and Architecture?");
        questionList.add("Where is Faculty of Education?");
        questionList.add("Where is Faculty of Science?");
        questionList.add("Which building has the largest lecture halls on the campus?");
        questionText = findViewById(R.id.questionText);
        index = ((int)(questionList.size()*Math.random()));
        questionText.setText(questionList.get(index));
        spinner = findViewById(R.id.spinner);
        buttonGo = findViewById(R.id.buttonGo2);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAnswer()) {
                    intent = new Intent(GameModeMain.this, GameModeMap.class);
                    intent.putExtra("answer",answerSelected);
                    startActivity(intent);
                }
            }
        });
        adapter = new ArrayAdapter<String>(getApplicationContext()
                , android.R.layout.simple_list_item_1
                , android.R.id.text1
                , answerList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public boolean checkAnswer(){
        if (index == 0 && answerSelected.equals("A")){
            return true;
        }
        else if (index == 1 && answerSelected.equals("B")) {
            return true;
        }
        else if (index == 2 && answerSelected.equals("EA")) {
            return true;
        }
        else if (index == 3 && answerSelected.equals("EE")) {
            return true;
        }
        else if (index == 4 && answerSelected.equals("FF")) {
            return true;
        }
        else if (index == 5 && answerSelected.equals("G")) {
            return true;
        }
        else if (index == 6 && answerSelected.equals("SA")) {
            return true;
        }
        else if (index == 7 && answerSelected.equals("V")) {
            return true;
        }
        else if(answerSelected.equals("-"))
            return true;
        else
            return false;
    }
}
