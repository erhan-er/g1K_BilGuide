package com.g1k.bilguide;

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

public class GameModeDiscovery extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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
        setContentView(R.layout.activity_game_mode_discovery);
        wrongTry = 0;
        bilCoin = 0;
        buttonGo = findViewById(R.id.buttonGo2);
        spinner = findViewById(R.id.spinner);
        questionText = findViewById(R.id.questionText);
        questionList = new ArrayList<String>();
        answerList = new ArrayList<String>();
        answerList.add("-");
        answerList.add("-2");
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
            questionList.add("Which amphitheater is there in A building?");
            questionList.add("Which department is not located in A building");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        else if(buildingName.equals("EA")) {
            questionList.add("Which floor is Yapı Kredi bank located on?");
            questionList.add("Which cafe are there in EA building entrance? ");
            questionList.add("Which amphitheater is there in this building?");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        else if(buildingName.equals("SA")) {
            questionList.add("Which floor is Foucault pendulum located on? ");
            questionList.add("Which structure do the stairs around the pendulum resemble?");
            questionList.add("Which department is not located in SA building?");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        else if(buildingName.equals("FF")) {
            questionList.add("On which floor is there a bookstore in FF building?");
            questionList.add("Which amphitheater do you see after you go down the stairs which is the closest stairs to the door of FFB? ");
            questionList.add("Which cafe is there in FF-C?");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        else if(buildingName.equals("EE")) {
            questionList.add("Which floor does the entrance from the side of Rector's Office of this building seem as in the elevator?");
            questionList.add("Which cafe is there in this building? ");
            questionList.add("Which floor does have a gate that you can go to ODEON and Meteksan Store?");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        else if(buildingName.equals("G")) {
            questionList.add("Which floor is vending machine located on?");
            questionList.add("Which one is the column shape on the ceiling of G building?");
            questionList.add("In which floor you can shift to A building directly?");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        else if(buildingName.equals("V")) {
            questionList.add("Which floor is Coffee Break located on?");
            questionList.add("How many entrance does each lecture hall have?");
            questionList.add("In which floor you can shift to A building directly?");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        index = 0;
        questionText.setText(questionList.get(index));
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GameModeDiscovery.this, GameModeBalance.class);
                intent.putExtra("balance", bilCoin);
                startActivity(intent);
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
        if (buildingName.equals("A")) {
            if ((index == 0) && answerSelected.equals("0")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"Mithat Çoruh");
                answerList.set(2,"A Blok Auditorium");
                answerList.set(3,"D Blok Auditorium");
                answerList.set(4,"Adnan Saygun");
                answerList.set(5,"C Blok Auditorium");
                answerList.set(6,"B Blok Auditorium");
                return true;
            }
            else if (index == 1 && answerSelected.equals("C Blok Auditorium")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"International Relations");
                answerList.set(2,"Philosophy");
                answerList.set(3,"Economics");
                answerList.set(4,"Management");
                answerList.set(5,"English Language and Literature");
                answerList.set(6,"American Culture and Literature");
                return true;
            }
            else if ((index == 2) && answerSelected.equals("Management")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 7; i++)
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
                    answerList.set(1,"Mithat Çoruh");
                    answerList.set(2,"A Blok Auditorium");
                    answerList.set(3,"D Blok Auditorium");
                    answerList.set(4,"Adnan Saygun");
                    answerList.set(5,"C Blok Auditorium");
                    answerList.set(6,"B Blok Auditorium");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    answerList.set(1,"International Relations");
                    answerList.set(2,"Philosophy");
                    answerList.set(3,"Economics");
                    answerList.set(4,"Management");
                    answerList.set(5,"English Language and Literature");
                    answerList.set(6,"American Culture and Literature");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 7; i++)
                        answerList.remove(0);
                    buttonGo.setEnabled(true);
                    bilCoin -= 5;
                }
                return false;
            }
        }
        if (buildingName.equals("B")) {
            if ((index == 0) && answerSelected.equals("-1")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"0");
                answerList.set(2,"1");
                answerList.set(3,"2");
                answerList.set(4,"3");
                answerList.set(5,"4");
                answerList.set(6,"5");
                return true;
            }
            else if ((index == 1) && answerSelected.equals("2")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"0");
                answerList.set(2,"1");
                answerList.set(3,"2");
                answerList.set(4,"3");
                answerList.set(5,"4");
                answerList.set(6,"5");
                return true;
            }
            else if ((index == 2) && answerSelected.equals("1")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 7; i++)
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
                    answerList.set(1,"0");
                    answerList.set(2,"1");
                    answerList.set(3,"2");
                    answerList.set(4,"3");
                    answerList.set(5,"4");
                    answerList.set(6,"5");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    answerList.set(1,"0");
                    answerList.set(2,"1");
                    answerList.set(3,"2");
                    answerList.set(4,"3");
                    answerList.set(5,"4");
                    answerList.set(6,"5");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 7; i++)
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
                answerList.set(1,"Fameo");
                answerList.set(2,"Coffee Break");
                answerList.set(3,"Starbucks");
                answerList.set(4,"Speed");
                answerList.set(5,"Mozart");
                answerList.set(6,"Cafe In");
                return true;
            }
            else if (index == 1 && answerSelected.equals("Fameo")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"B Blok Auditorium");
                answerList.set(2,"A Blok Auditorium");
                answerList.set(3,"D Blok Auditorium");
                answerList.set(4,"Adnan Saygun");
                answerList.set(5,"C Blok Auditorium");
                answerList.set(6,"Mithat Çoruh");
                bilCoin -= 5;
                return true;
            }
            else if ((index == 2) && answerSelected.equals("Mithat Çoruh")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 7; i++)
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
                    answerList.set(1,"Fameo");
                    answerList.set(2,"Coffee Break");
                    answerList.set(3,"Starbucks");
                    answerList.set(4,"Speed");
                    answerList.set(5,"Mozart");
                    answerList.set(6,"Cafe In");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    answerList.set(1,"B Blok Auditorium");
                    answerList.set(2,"A Blok Auditorium");
                    answerList.set(3,"D Blok Auditorium");
                    answerList.set(4,"Adnan Saygun");
                    answerList.set(5,"C Blok Auditorium");
                    answerList.set(6,"Mithat Çoruh");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 7; i++)
                        answerList.remove(0);
                    buttonGo.setEnabled(true);
                    bilCoin -= 5;
                }
                return false;
            }
        }
        if (buildingName.equals("EE")) {
            if ((index == 0) && answerSelected.equals("3")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"Cafe In");
                answerList.set(2,"Coffee Break");
                answerList.set(3,"Starbucks");
                answerList.set(4,"Speed");
                answerList.set(5,"Mozart");
                answerList.set(6,"Fameo");
                return true;
            }
            else if ((index == 1) && answerSelected.equals("Mozart")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"-2");
                answerList.set(2,"-1");
                answerList.set(3,"0");
                answerList.set(4,"1");
                answerList.set(5,"2");
                answerList.set(6,"3");
                return true;
            }
            else if ((index == 2) && answerSelected.equals("0")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 7; i++)
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
                    answerList.set(1,"Fameo");
                    answerList.set(2,"Coffee Break");
                    answerList.set(3,"Starbucks");
                    answerList.set(4,"Speed");
                    answerList.set(5,"Mozart");
                    answerList.set(6,"Cafe In");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    answerList.set(1,"-2");
                    answerList.set(2,"-1");
                    answerList.set(3,"0");
                    answerList.set(4,"1");
                    answerList.set(5,"2");
                    answerList.set(6,"3");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 7; i++)
                        answerList.remove(0);
                    buttonGo.setEnabled(true);
                    bilCoin -= 5;
                }
                return false;
            }
        }
        if (buildingName.equals("FF")) {
            if ((index == 0) && answerSelected.equals("1")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"FFB05");
                answerList.set(2,"FFB22");
                answerList.set(3,"FFB01");
                answerList.set(4,"FFB10");
                answerList.set(5,"Mithat Çoruh");
                answerList.set(6,"A Blok Auditorium");
                return true;
            }
            else if ((index == 1) && answerSelected.equals("FFB05")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"Fameo");
                answerList.set(2,"Coffee Break");
                answerList.set(3,"Starbucks");
                answerList.set(4,"Speed");
                answerList.set(5,"Mozart");
                answerList.set(6,"Cafe In");
                return true;
            }
            else if ((index == 2) && answerSelected.equals("Starbucks")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 7; i++)
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
                    answerList.set(1,"FFB05");
                    answerList.set(2,"FFB22");
                    answerList.set(3,"FFB01");
                    answerList.set(4,"FFB10");
                    answerList.set(5,"Mithat Çoruh");
                    answerList.set(6,"A Blok Auditorium");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    answerList.set(1,"Fameo");
                    answerList.set(2,"Coffee Break");
                    answerList.set(3,"Starbucks");
                    answerList.set(4,"Speed");
                    answerList.set(5,"Mozart");
                    answerList.set(6,"Cafe In");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 7; i++)
                        answerList.remove(0);
                    buttonGo.setEnabled(true);
                    bilCoin -= 5;
                }
                return false;
            }
        }
        if (buildingName.equals("G")) {
            if ((index == 0) && answerSelected.equals("0")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"DNA Helix");
                answerList.set(2,"Triangle");
                answerList.set(3,"Circle");
                answerList.set(4,"Mountain");
                answerList.set(5,"Star");
                answerList.set(6,"Moon");
                return true;
            }
            else if ((index == 1) && answerSelected.equals("Triangle")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"-2");
                answerList.set(2,"-1");
                answerList.set(3,"0");
                answerList.set(4,"1");
                answerList.set(5,"2");
                answerList.set(6,"3");
                return true;
            }
            else if ((index == 2) && answerSelected.equals("2")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 7; i++)
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
                    answerList.set(1,"DNA Helix");
                    answerList.set(2,"Triangle");
                    answerList.set(3,"Circle");
                    answerList.set(4,"Mountain");
                    answerList.set(5,"Star");
                    answerList.set(6,"Moon");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    answerList.set(1,"-2");
                    answerList.set(2,"-1");
                    answerList.set(3,"0");
                    answerList.set(4,"1");
                    answerList.set(5,"2");
                    answerList.set(6,"3");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 7; i++)
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
                answerList.set(1,"DNA Helix");
                answerList.set(2,"Rose");
                answerList.set(3,"Circle");
                answerList.set(4,"Mountain");
                answerList.set(5,"Star");
                answerList.set(6,"Moon");
                return true;
            }
            else if ((index == 1) && answerSelected.equals("DNA Helix")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"Translation and Interpretation");
                answerList.set(2,"Archeology");
                answerList.set(3,"Mathematics");
                answerList.set(4,"Chemistry");
                answerList.set(5,"Economics");
                answerList.set(6,"Biology");
                return true;
            }
            else if ((index == 2) && answerSelected.equals("Mathematics")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 7; i++)
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
                    answerList.set(1,"DNA Helix");
                    answerList.set(2,"Rose");
                    answerList.set(3,"Circle");
                    answerList.set(4,"Mountain");
                    answerList.set(5,"Star");
                    answerList.set(6,"Moon");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    answerList.set(1,"Translation and Interpretation");
                    answerList.set(2,"Archeology");
                    answerList.set(3,"Mathematics");
                    answerList.set(4,"Chemistry");
                    answerList.set(5,"Economics");
                    answerList.set(6,"Biology");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 7; i++)
                        answerList.remove(0);
                    buttonGo.setEnabled(true);
                    bilCoin -= 5;
                }
                return false;
            }
        }
        if (buildingName.equals("V")) {
            if ((index == 0) && answerSelected.equals("0")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"1");
                answerList.set(2,"2");
                answerList.set(3,"3");
                answerList.set(4,"4");
                answerList.set(5,"5");
                answerList.set(6,"6");
                return true;
            }
            else if ((index == 1) && answerSelected.equals("2")) {
                index++;
                questionText.setText(questionList.get(index));
                wrongTry = 0;
                bilCoin += 10;
                answerList.set(1,"-2");
                answerList.set(2,"-1");
                answerList.set(3,"0");
                answerList.set(4,"1");
                answerList.set(5,"2");
                answerList.set(6,"3");
                return true;
            }
            else if ((index == 2) && answerSelected.equals("1")) {
                index++;
                questionText.setText(questionList.get(index));
                for (int i = 0; i < 7; i++)
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
                    answerList.set(1,"1");
                    answerList.set(2,"2");
                    answerList.set(3,"3");
                    answerList.set(4,"4");
                    answerList.set(5,"5");
                    answerList.set(6,"6");
                    bilCoin -= 5;
                }
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    answerList.set(1,"-2");
                    answerList.set(2,"-1");
                    answerList.set(3,"0");
                    answerList.set(4,"1");
                    answerList.set(5,"2");
                    answerList.set(6,"3");
                    bilCoin -= 5;
                }
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    for (int i = 0; i < 7; i++)
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
