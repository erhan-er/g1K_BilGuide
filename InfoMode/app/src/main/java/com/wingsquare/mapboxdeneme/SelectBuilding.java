package com.wingsquare.mapboxdeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.mapboxdeneme.R;

import java.util.ArrayList;
import java.util.List;

public class SelectBuilding extends AppCompatActivity{

    private ImageView imageLocation;
    private Spinner spinnerBuilding;
    private Button buttonGo;
    private Button buttonMainMenu;
    private ArrayAdapter<Building> buildingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_building);

        imageLocation = findViewById(R.id.imageLocation);
        Animation shakeAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shakeanimation);
        imageLocation.setAnimation(shakeAnim);

        buttonGo = findViewById(R.id.buttonGo);
        buttonMainMenu = findViewById(R.id.buttonMainMenu);
        spinnerBuilding = findViewById(R.id.spinnerBuilding);

        final List<Building> buildings = new ArrayList<>();
        Building A = new Building( "A Building", 39.867970, 32.749709);
        buildings.add(A);
        Building B = new Building("B Building",39.868619, 32.748242);
        buildings.add(B);
        Building EA = new Building("EA Building",39.871079, 32.750061);
        buildings.add(EA);
        Building EE = new Building("EE Building", 39.872024, 32.750444);
        buildings.add(EE);
        Building FF = new Building("FF Building",39.865946, 32.748831);
        buildings.add(FF);
        Building G = new Building("G Building",39.868696, 32.749775);
        buildings.add(G);
        Building MA = new Building("MA Building",39.867485, 32.750434);
        buildings.add(MA);
        Building SA = new Building("SA Building",39.867876, 32.748466);
        buildings.add(SA);
        Building SB = new Building("SB Building",39.868267, 32.748433);
        buildings.add(SB);
        Building SportCenter = new Building("Sport Center",39.866703, 32.748777);
        buildings.add(SportCenter);
        Building SpeedKirac = new Building("Speed/Kirac Cafe",39.866210, 32.748600);
        buildings.add(SpeedKirac);
        Building Cafeteria = new Building("Cafeteria",39.870443, 32.750381);
        buildings.add(Cafeteria);
        Building CoffeeBreak = new Building("Coffee Break",39.868199, 32.748901);
        buildings.add(CoffeeBreak);
        Building Library = new Building("Library",39.870280, 32.749838);
        buildings.add(Library);

        buildingAdapter = new ArrayAdapter<Building>(getApplicationContext()
                , R.layout.spinneritem
                , android.R.id.text1
                , buildings);
        buildingAdapter.setDropDownViewResource(R.layout.forspinner);
        spinnerBuilding.setAdapter(buildingAdapter);

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMap = new Intent(getApplicationContext(), InfoModeMapsActivity.class);
                for( int i = 0; i <=13; i++){
                    if(spinnerBuilding.getSelectedItemPosition()== i){
                        goToMap.putExtra("lat", ((Building) spinnerBuilding.getItemAtPosition(i)).getLatitude() + "");
                        goToMap.putExtra("lng", ((Building) spinnerBuilding.getItemAtPosition(i)).getLongitude() + "");
                        startActivity(goToMap);
                    }
                }
            }
        });

    }
}
