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
* This class shows three questions for each building
* @author Furkan, Murat Furkan, Recep
* @version 1.0
*/

public class GameModeDiscovery extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // variables
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

    // methods
    /*
     *This methods is the default method of android studio, which applies main process for widgets.
     * @param parent, a View object, position of selected item, id
     */
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
        // setting three questions for each building by adding them to the question list
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
            questionList.add("Which cafe is there in EA building entrance? ");
            questionList.add("Which amphitheater is there in this building?");
            questionList.add("You completed the questions. Please press the button to view your balance.");
        }
        else if(buildingName.equals("SA")) {
            questionList.add("Which floor is Foucault pendulum located on? ");
            questionList.add("Which structure do the stairs around the pendulum resemble?");
            questionList.add("Which department is located in SA building?");
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
        index = 0; // This means question are viewed in turn by starting index at zero
        questionText.setText(questionList.get(index)); // setting the question

        // When the button go was pressed, this method works
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Passing from this activity to the next activity which is balance page
                intent = new Intent(GameModeDiscovery.this, GameModeBalance.class);
                // Putting balance earned to the next page to view it
                intent.putExtra("balance", bilCoin);
                startActivity(intent);
            }
        });
        adapter = new ArrayAdapter<String>(getApplicationContext()
                , android.R.layout.simple_list_item_1
                , android.R.id.text1
                , answerList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /*
     *This methods works at which an item is selected in spinner
     * @param parent, a View object, position of selected item, id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Getting selected answer
        answerSelected = parent.getItemAtPosition(position).toString();
        // Shows a message as to accuracy of answer by using  the method checkAnswer
        if (!checkAnswer()) {
            Toast.makeText(parent.getContext(), "Wrong answer", Toast.LENGTH_SHORT).show();
        }
        else {
            if (answerSelected.equals("-")) {
            }
            else if (index == 3){
                // When the last question is answered this message is viewed
                Toast.makeText(parent.getContext(), "Go", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(parent.getContext(), "Correct answer", Toast.LENGTH_SHORT).show();
            }
        }
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
        // setting the button unavailable
        buttonGo.setEnabled(false);
        // For building A
        if (buildingName.equals("A")) {
            // if the first question answered correctly
            if ((index == 0) && answerSelected.equals("0")) {
                index++; // to view the next question
                questionText.setText(questionList.get(index));
                wrongTry = 0; // any previous wrong attempts are not cared for balance
                bilCoin += 10; // for each correct answer, balance will increase as 10
                // setting the options of the next question
                answerList.set(1,"Mithat Çoruh");
                answerList.set(2,"A Blok Auditorium");
                answerList.set(3,"D Blok Auditorium");
                answerList.set(4,"Adnan Saygun");
                answerList.set(5,"C Blok Auditorium");
                answerList.set(6,"B Blok Auditorium");
                return true;
            }
            // if the second question answered correctly
            else if (index == 1 && answerSelected.equals("C Blok Auditorium")) {
                index++; // to view the next question
                questionText.setText(questionList.get(index));
                wrongTry = 0; // any previous wrong attempts are not cared for balance
                bilCoin += 10; // for each correct answer, balance will increase as 10
                // setting the options of the next question
                answerList.set(1,"International Relations");
                answerList.set(2,"Philosophy");
                answerList.set(3,"Economics");
                answerList.set(4,"Business Administration");
                answerList.set(5,"English Language and Literature");
                answerList.set(6,"American Culture and Literature");
                return true;
            }
            // If the third question answered correctly
            else if ((index == 2) && answerSelected.equals("Management")) {
                index++; // to view the next question
                questionText.setText(questionList.get(index));
                // Removing the options of the last question so that the user is unlikely to select an option anymore
                for (int i = 0; i < 7; i++)
                    answerList.remove(0);
                buttonGo.setEnabled(true);
                wrongTry = 0; // any previous wrong attempts are not cared for balance
                bilCoin += 10; // for each correct answer, balance will increase as 10
                return true;
            }
            else if (answerSelected.equals("-"))
                return true;
            else if (index == 3) {
                return true;
            }
            else {
                wrongTry++; // For each wrong answer, this is increased
                // If the first question is answered wrongly three times
                if (wrongTry == 3 && index == 0) {
                    index++; // to view the next question
                    wrongTry = 0; // setting wrong try to zero for other questions
                    bilCoin -= 5;; // for each wrong answer, balance will decrease as 5
                    questionText.setText(questionList.get(index));
                    answerList.set(1,"Mithat Çoruh");
                    answerList.set(2,"A Blok Auditorium");
                    answerList.set(3,"D Blok Auditorium");
                    answerList.set(4,"Adnan Saygun");
                    answerList.set(5,"C Blok Auditorium");
                    answerList.set(6,"B Blok Auditorium");
                }
                // If the second question is answered wrongly three times
                else if( wrongTry == 3 && index == 1) {
                    index++;
                    wrongTry = 0;
                    questionText.setText(questionList.get(index));
                    answerList.set(1,"International Relations");
                    answerList.set(2,"Philosophy");
                    answerList.set(3,"Economics");
                    answerList.set(4,"Business Administration");
                    answerList.set(5,"English Language and Literature");
                    answerList.set(6,"American Culture and Literature");
                    bilCoin -= 5;
                }
                // If the third(last) question is answered wrongly three times
                else if (wrongTry == 3 && index == 2) {
                    index++;
                    questionText.setText(questionList.get(index));
                    // removing options
                    for (int i = 0; i < 7; i++)
                        answerList.remove(0);
                    buttonGo.setEnabled(true);
                    bilCoin -= 5;
                }
                return false;
            }
        }

        // For building B
        // For each wrong or correct answer, the state of index, wrongTry, and balance is the same as other buildings
        // Options are changed question to question
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

        // For building EA
        // For each wrong or correct answer, the state of index, wrongTry, and balance is the same as other buildings
        // Options are changed question to question
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

        // For building EE
        // For each wrong or correct answer, the state of index, wrongTry, and balance is the same as other buildings
        // Options are changed question to question
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

        // For building FF
        // For each wrong or correct answer, the state of index, wrongTry, and balance is the same as other buildings
        // Options are changed question to question
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

        // For building G
        // For each wrong or correct answer, the state of index, wrongTry, and balance is the same as other buildings
        // Options are changed question to question
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

        // For building SA
        // For each wrong or correct answer, the state of index, wrongTry, and balance is the same as other buildings
        // Options are changed question to question
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

        // For building V
        // For each wrong or correct answer, the state of index, wrongTry, and balance is the same as other buildings
        // Options are changed question to question
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
