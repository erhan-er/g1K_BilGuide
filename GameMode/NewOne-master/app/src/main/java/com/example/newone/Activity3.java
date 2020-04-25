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

public class Activity3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private Button buttonGo;
    private String answerSelected;
    private ArrayList<String> questionList;
    private TextView questionText;
    private Intent intent;
    private String buildingName;
    private int index;
    //private int bilCoin;
    private int wrongTry;
    private ArrayAdapter<CharSequence> adapter;

    public boolean checkAnswer() {
        buttonGo.setEnabled(false);
        if (buildingName.equals("B")) {
            if ((index == 0) && answerSelected.equals("-1")) {
                index++;
                questionText.setText(questionList.get(index));
                return true;
            }
            else if ((index == 1) && answerSelected.equals("2")) {
                index++;
                questionText.setText(questionList.get(index));
                return true;
            }
            else if ((index == 2) && answerSelected.equals("1")) {
                buttonGo.setEnabled(true);
                wrongTry = 0;
                return true;
            }
            else if (answerSelected.equals("-"))
                return true;
            else {
                wrongTry++;
                if (wrongTry == 3 && index != 2) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                }
                else if (wrongTry == 3 && index == 2) {
                    buttonGo.setEnabled(true);
                }
                return false;
            }
        }
        if (buildingName.equals("A")) {
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
            if ((index == 0) && answerSelected.equals("0")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                return true;
            }
            else if (index == 1 && answerSelected.equals("C Blok Amphi")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                return true;
            }
            else if ((index == 2) && answerSelected.equals("Management")) {
                buttonGo.setEnabled(true);
                wrongTry = 0;
                return true;
            }
            else if (answerSelected.equals("-"))
                return true;
            else {
                wrongTry++;
                if (wrongTry == 3 && index != 2) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                }
                else if (wrongTry == 3 && index == 2) {
                    buttonGo.setEnabled(true);
                }
                return false;
            }
        }
        if (buildingName.equals("E")) {
            if ((index == 0) && answerSelected.equals("Fameo")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                return true;
            }
            else if (index == 1 && answerSelected.equals("0")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                return true;
            }
            else if ((index == 2) && answerSelected.equals("Mithat Çoruh")) {
                buttonGo.setEnabled(true);
                wrongTry = 0;
                return true;
            }
            else if (answerSelected.equals("-"))
                return true;
            else {
                wrongTry++;
                if (wrongTry == 3 && index != 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                }
                else if (wrongTry == 3 && index == 2) {
                    buttonGo.setEnabled(true);
                }
                return false;
            }
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        wrongTry = 0;
        buttonGo = findViewById(R.id.buttonGo);
        spinner = findViewById(R.id.spinner);
        questionText = findViewById(R.id.questionText);
        questionList = new ArrayList<String>();
        buildingName = getIntent().getStringExtra("answer");
        if(buildingName.equals("B")) {
            questionList.add("Which floor is Mozart Cafe located on?");
            questionList.add("How many lab classes are there on the second floor?");
            questionList.add("How many printer(s) does each lab class on the third floor have ?");
        }
        else if(buildingName.equals("A")) {
            questionList.add("Which floor is TradeMaster located on?");
            questionList.add("Which amphi is there in A building?");
            questionList.add("Which department is not located in A building");
        }
        else if(buildingName.equals("E")) {
            questionList.add("Which cafe are there in E building entrance? ");
            questionList.add("Which floor is Yapı Kredi bank located on?");
            questionList.add("Which amphi is there in E building?");
        }
        index = 0;
        questionText.setText(questionList.get(index));
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Activity3.this, Activity4.class);
                //intent.putExtra("balance", bilCoin);
                startActivity(intent);
            }
        });
        adapter = ArrayAdapter.createFromResource(this,R.array.floors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter1 = ArrayAdapter.createFromResource(this,R.array.cafe, android.R.layout.simple_spinner_item);
        //adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter2 = ArrayAdapter.createFromResource(this,R.array.amphi, android.R.layout.simple_spinner_item);
        //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        answerSelected = parent.getItemAtPosition(position).toString();
        if (!checkAnswer()) {
            Toast.makeText(parent.getContext(), "Wrong answer", Toast.LENGTH_SHORT).show();
        }
        else {
            if (answerSelected.equals("-")) {
            }
            else {
                Toast.makeText(parent.getContext(), "Go", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
