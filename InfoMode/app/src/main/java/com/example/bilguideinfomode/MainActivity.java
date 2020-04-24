package com.example.bilguideinfomode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Spinner spinnerBuilding;
    private Button buttonGo;
    private Button buttonMainMenu;
    private ArrayAdapter<String> buildingAdapter;
    private String[] buildings = {"A Building", "B Building",
            "EA Building", "EE Building",
            "FF Building", "G Building",
            "MA Building", "SA Building",
            "SB Building", "Sport Center",
            "Speed/Kirac Cafe", "Cafeteria",
            "Coffee Break", "Library"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonGo = findViewById(R.id.buttonGo);
        buttonMainMenu = findViewById(R.id.buttonMainMenu);
        spinnerBuilding = findViewById(R.id.spinnerBuilding);
        buildingAdapter = new ArrayAdapter<String>(getApplicationContext()
                , android.R.layout.simple_list_item_1
                , android.R.id.text1
                , buildings);
        spinnerBuilding.setAdapter(buildingAdapter);

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You will be redirecting...", Toast.LENGTH_LONG).show();
            }
        });

    }
}
