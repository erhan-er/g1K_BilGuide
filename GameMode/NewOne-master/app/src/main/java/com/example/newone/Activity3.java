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
import java.util.List;

public class Activity3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private Button buttonGo;
    private String answerSelected;
    private ArrayList<String> questionList;
    private ArrayList<String> answerList;
    private TextView questionText;
    private Intent intent;
    private String buildingName;
    private int index;
    private int bilCoin;
    private int wrongTry;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        wrongTry = 0;
        bilCoin = 20;
        buttonGo = findViewById(R.id.buttonGo2);
        spinner = findViewById(R.id.spinner);
        questionText = findViewById(R.id.questionText);
        questionList = new ArrayList<String>();
        answerList = new ArrayList<String>();
        answerList.add("-");
        answerList.add("-1");
        answerList.add("0");
        answerList.add("1");
        answerList.add("2");
        answerList.add("3");
        buildingName = getIntent().getStringExtra("answer");
        if(buildingName.equals("B")) {
            questionList.add("Which floor is Mozart Cafe located on?");
            questionList.add("How many lab classes are there on the second floor?");
            questionList.add("How many printer(s) does each lab class on the third floor have ?");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        else if(buildingName.equals("A")) {
            questionList.add("Which floor is TradeMaster located on?");
            questionList.add("Which amphi is there in A building?");
            questionList.add("Which department is not located in A building");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        else if(buildingName.equals("EA")) {
            questionList.add("Which floor is Yapı Kredi bank located on?");
            questionList.add("Which cafe are there in EA building entrance? ");
            questionList.add("Which amphi is there in this building?");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        else if(buildingName.equals("SA")) {
            questionList.add("Which floor is Foucault pendulum located on? ");
            questionList.add("Which structure do the stairs around the pendulum resemble?");
            questionList.add("Which department is not located in SA building?");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        else if(buildingName.equals("FF")) {
            questionList.add("How many amphi(s) are there at the floor of -1 in FF");
            questionList.add("Which amphi do you see after you go down the stairs which is the closest stairs to the door of FFB? ");
            questionList.add("Which cafe is there in FF-C?");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        index = 0;
        questionText.setText(questionList.get(index));
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Activity3.this, Activity4.class);
                intent.putExtra("balance", bilCoin);
                startActivity(intent);
            }
        });

        // adapter = ArrayAdapter.createFromResource(this, R.array.floors, android.R.layout.simple_spinner_item);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter = new ArrayAdapter<String>(getApplicationContext()
                , android.R.layout.simple_list_item_1
                , android.R.id.text1
                , answerList);
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
            else if (index == 3){
                Toast.makeText(parent.getContext(), "Go", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(parent.getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public boolean checkAnswer() {
        buttonGo.setEnabled(false);
        if (buildingName.equals("B")) {
            if ((index == 0) && answerSelected.equals("-1")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                for (int i = 0; i < 6; i++)
                    answerList.remove(0);
                answerList.add("-");
                answerList.add("1");
                answerList.add("2");
                answerList.add("3");
                answerList.add("4");
                return true;
            }
            else if ((index == 1) && answerSelected.equals("2")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                for (int i = 0; i < 5; i++)
                    answerList.remove(0);
                answerList.add("-");
                answerList.add("1");
                answerList.add("2");
                answerList.add("3");
                answerList.add("4");
                return true;
            }
            else if ((index == 2) && answerSelected.equals("1")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 5; i++)
                    answerList.remove(0);
                buttonGo.setEnabled(true);
                wrongTry = 0;
                bilCoin += 10;
                return true;
            }
            else if (answerSelected.equals("-"))
                return true;
            else if (index == 3) {
                return true;
            }
            else {
                wrongTry++;
                if (wrongTry == 3 && index == 0) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 6; i++)
                        answerList.remove(0);
                    answerList.add("-");
                    answerList.add("1");
                    answerList.add("2");
                    answerList.add("3");
                    answerList.add("4");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 5; i++)
                        answerList.remove(0);
                    answerList.add("-");
                    answerList.add("0");
                    answerList.add("1");
                    answerList.add("2");
                    answerList.add("3");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 5; i++)
                        answerList.remove(0);
                    buttonGo.setEnabled(true);
                    bilCoin -= 5;
                }
                return false;
            }
        }
        if (buildingName.equals("A")) {
            if ((index == 0) && answerSelected.equals("0")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                for (int i = 0; i < 6; i++)
                    answerList.remove(0);
                answerList.add("-");
                answerList.add("Mithat Çoruh");
                answerList.add("EE01");
                answerList.add("C Blok Amphi");
                answerList.add("FFB22");
                return true;
            }
            else if (index == 1 && answerSelected.equals("C Blok Amphi")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                for (int i = 0; i < 5; i++)
                    answerList.remove(0);
                answerList.add("-");
                answerList.add("International Relations");
                answerList.add("Philosophy");
                answerList.add("Archeology");
                answerList.add("Management");
                return true;
            }
            else if ((index == 2) && answerSelected.equals("Management")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 5; i++)
                    answerList.remove(0);
                buttonGo.setEnabled(true);
                wrongTry = 0;
                bilCoin += 10;
                return true;
            }
            else if (answerSelected.equals("-"))
                return true;
            else if (index == 3) {
                return true;
            }
            else {
                wrongTry++;
                if (wrongTry == 3 && index == 0) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 6; i++)
                        answerList.remove(0);
                    answerList.add("-");
                    answerList.add("Mithat Çoruh");
                    answerList.add("C Blok Amphi");
                    answerList.add("EE01");
                    answerList.add("FFB22");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 5; i++)
                        answerList.remove(0);
                    answerList.add("-");
                    answerList.add("International Relations");
                    answerList.add("Philosophy");
                    answerList.add("Archeology");
                    answerList.add("Management");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 5; i++)
                        answerList.remove(0);
                    buttonGo.setEnabled(true);
                    bilCoin -= 5;
                }
                return false;
            }
        }
        if (buildingName.equals("EA")) {
            if ((index == 0) && answerSelected.equals("0")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                for (int i = 0; i < 6; i++)
                    answerList.remove(0);
                answerList.add("-");
                answerList.add("Fameo");
                answerList.add("CoffeeBreak");
                answerList.add("Starbucks");
                answerList.add("Speed");
                return true;
            }
            else if (index == 1 && answerSelected.equals("Fameo")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                for (int i = 0; i < 5; i++)
                    answerList.remove(0);
                answerList.add("-");
                answerList.add("C Blok Amphi");
                answerList.add("Mithat Çoruh");
                answerList.add("EE01");
                answerList.add("FFB22");
                return true;
            }
            else if ((index == 2) && answerSelected.equals("Mithat Çoruh")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 5; i++)
                    answerList.remove(0);
                buttonGo.setEnabled(true);
                wrongTry = 0;
                bilCoin += 10;
                return true;
            }
            else if (answerSelected.equals("-"))
                return true;
            else if (index == 3) {
                return true;
            }
            else {
                wrongTry++;
                if (wrongTry == 3 && index == 0) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 6; i++)
                        answerList.remove(0);
                    answerList.add("-");
                    answerList.add("Fameo");
                    answerList.add("CoffeeBreak");
                    answerList.add("Starbucks");
                    answerList.add("Speed");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 5; i++)
                        answerList.remove(0);
                    answerList.add("-");
                    answerList.add("C Blok Amphi");
                    answerList.add("Mithat Çoruh");
                    answerList.add("EE01");
                    answerList.add("FFB22");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 5; i++)
                        answerList.remove(0);
                    buttonGo.setEnabled(true);
                    bilCoin -= 5;
                }
                return false;
            }
        }
        if (buildingName.equals("SA")) {
            if ((index == 0) && answerSelected.equals("0")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                for (int i = 0; i < 6; i++)
                    answerList.remove(0);
                answerList.add("-");
                answerList.add("DNA Helix");
                answerList.add("Home");
                answerList.add("Circle");
                answerList.add("Mountain");
                return true;
            }
            else if ((index == 1) && answerSelected.equals("DNA Helix")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                for (int i = 0; i < 5; i++)
                    answerList.remove(0);
                answerList.add("-");
                answerList.add("Mathematics");
                answerList.add("Physics");
                answerList.add("Molecular Biology and Genetic");
                answerList.add("Chemistry");
                return true;
            }
            else if ((index == 2) && answerSelected.equals("Chemistry")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 5; i++)
                    answerList.remove(0);
                buttonGo.setEnabled(true);
                wrongTry = 0;
                bilCoin += 10;
                return true;
            }
            else if (answerSelected.equals("-"))
                return true;
            else if (index == 3) {
                return true;
            }
            else {
                wrongTry++;
                if (wrongTry == 3 && index == 0) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 6; i++)
                        answerList.remove(0);
                    answerList.add("-");
                    answerList.add("DNA Helix");
                    answerList.add("Home");
                    answerList.add("Circle");
                    answerList.add("Mountain");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 5; i++)
                        answerList.remove(0);
                    answerList.add("-");
                    answerList.add("Mathematics");
                    answerList.add("Physics");
                    answerList.add("Molecular Biology and Genetic");
                    answerList.add("Chemistry");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 5; i++)
                        answerList.remove(0);
                    buttonGo.setEnabled(true);
                    bilCoin -= 5;
                }
                return false;
            }
        }
        if (buildingName.equals("FF")) {
            if ((index == 0) && answerSelected.equals("3")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                for (int i = 0; i < 6; i++)
                    answerList.remove(0);
                answerList.add("-");
                answerList.add("FFB05");
                answerList.add("FFB22");
                answerList.add("FFB01");
                answerList.add("FFB10");
                return true;
            }
            else if ((index == 1) && answerSelected.equals("FFB05")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                for (int i = 0; i < 5; i++)
                    answerList.remove(0);
                answerList.add("-");
                answerList.add("Fameo");
                answerList.add("CoffeeBreak");
                answerList.add("Starbucks");
                answerList.add("Speed");
                return true;
            }
            else if ((index == 2) && answerSelected.equals("Starbucks")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 5; i++)
                    answerList.remove(0);
                buttonGo.setEnabled(true);
                wrongTry = 0;
                bilCoin += 10;
                return true;
            }
            else if (answerSelected.equals("-"))
                return true;
            else if (index == 3) {
                return true;
            }
            else {
                wrongTry++;
                if (wrongTry == 3 && index == 0) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 6; i++)
                        answerList.remove(0);
                    answerList.add("-");
                    answerList.add("FFB05");
                    answerList.add("FFB22");
                    answerList.add("FFB01");
                    answerList.add("FFB10");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 5; i++)
                        answerList.remove(0);
                    answerList.add("-");
                    answerList.add("Fameo");
                    answerList.add("CoffeeBreak");
                    answerList.add("Starbucks");
                    answerList.add("Speed");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 5; i++)
                        answerList.remove(0);
                    buttonGo.setEnabled(true);
                    bilCoin -= 5;
                }
                return false;
            }
        }
        return false;
    }
}
