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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private Button buttonGo;
    private String answerSelected;
    private ArrayList<String> questionList;
    private TextView questionText;
    private Intent intent;
    private int index;
    private ArrayAdapter<CharSequence> adapter;
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
        setContentView(R.layout.activity_main);
        questionList = new ArrayList<String>();
        questionList.add("Where is Law Faculty / BCC?");
        questionList.add("Where is Faculty of Engineering");
        questionList.add("Where is Economics Department");
        questionList.add("Where is Faculty of Science");
        //arrayList.add("Where is Trade Master?");
        questionText = findViewById(R.id.questionText);
        index = ((int)(4*Math.random()));
        questionText.setText(questionList.get(index));
        spinner = findViewById(R.id.spinner);
        buttonGo = findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAnswer()) {
                    intent = new Intent(MainActivity.this,Activity2.class);
                    intent.putExtra("answer",answerSelected);
                    startActivity(intent);
                }
            }
        });
        adapter = ArrayAdapter.createFromResource(this,R.array.answers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public boolean checkAnswer(){
        if (index == 0 && answerSelected.equals("B")){
            return true;
        }
        else if (index == 1 && answerSelected.equals("E")) {
            return true;
        }
        else if (index == 2 && answerSelected.equals("A")) {
            return true;
        }
        else if (index == 3 && answerSelected.equals("SA")) {
            return true;
        }
        //else if (index == 4 && text.equals("A")) {
            //return true;
        //}
        else if(answerSelected.equals("-"))
            return true;
        else
            return false;
    }
}
