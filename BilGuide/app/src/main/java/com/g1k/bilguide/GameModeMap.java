package com.g1k.bilguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.location.LocationManagerCompat;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.services.android.navigation.ui.v5.NavigationView;

import java.util.List;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Feature;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;

public class GameModeMap extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener {

    private MapView            mapView;
    private MapboxMap          mapboxMap;
    private LocationComponent  locationComponent;
    private PermissionsManager permissionsManager;
    private Intent             intent2;
    private Button             buttonGo2;
    private String             buildingName;
    private NavigationView     navigationView;
    private Button checkArrival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_NoActionBar);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_game_mode_map);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        buttonGo2 = findViewById(R.id.buttonGo2);
        checkArrival = findViewById(R.id.checkArrival);
        navigationView = findViewById(R.id.navigationView);
        buildingName = getIntent().getStringExtra("answer");
        buttonGo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2 = new Intent(GameModeMap.this, GameModeDiscovery.class);
                intent2.putExtra("answer",buildingName);
                startActivity(intent2);
            }
        });
        checkArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Point newLocation = Point.fromLngLat(locationComponent.getLastKnownLocation().getLongitude(),locationComponent.getLastKnownLocation().getLatitude());
                if( buildingName.equals("A")) {
                    if (newLocation.latitude() > 39.8675 && newLocation.latitude() < 39.8682 && newLocation.longitude() > 32.7492 && newLocation.longitude() < 32.7502) {
                        buttonGo2.setEnabled(true);
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        buttonGo2.setEnabled(false);
                    }
                }
                if( buildingName.equals("B")) {
                    if(newLocation.latitude() > 39.8685 && newLocation.latitude() < 39.8690 && newLocation.longitude() > 32.7475 && newLocation.longitude() < 32.7485) {
                        buttonGo2.setEnabled(true);
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        buttonGo2.setEnabled(false);
                    }
                }
                if( buildingName.equals("EA")) {
                    if (newLocation.latitude() > 39.8708 && newLocation.latitude() < 39.8715 && newLocation.longitude() > 32.7497 && newLocation.longitude() < 32.7504) {
                        buttonGo2.setEnabled(true);
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        buttonGo2.setEnabled(false);
                    }
                }
                if( buildingName.equals("EE")) {
                    if(newLocation.latitude() > 39.8719 && newLocation.latitude() < 39.8722 && newLocation.longitude() > 32.7504 && newLocation.longitude() < 32.7510) {
                        buttonGo2.setEnabled(true);
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        buttonGo2.setEnabled(false);
                    }
                }
                if( buildingName.equals("FF")) {
                    if(newLocation.latitude() > 39.8655 && newLocation.latitude() < 39.8665 && newLocation.longitude() > 32.7485 && newLocation.longitude() < 32.7495) {
                        buttonGo2.setEnabled(true);
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        buttonGo2.setEnabled(false);
                    }
                }
                if( buildingName.equals("G")) {
                    if(newLocation.latitude() > 39.8682 && newLocation.latitude() < 39.8689 && newLocation.longitude() > 32.7494 && newLocation.longitude() < 32.7504) {
                        buttonGo2.setEnabled(true);
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        buttonGo2.setEnabled(false);
                    }
                }
                if( buildingName.equals("SA")) {
                    if(newLocation.latitude() > 39.8675 && newLocation.latitude() < 39.8680 && newLocation.longitude() > 32.7480 && newLocation.longitude() < 32.7485) {
                        buttonGo2.setEnabled(true);
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        buttonGo2.setEnabled(false);
                    }
                }
                if( buildingName.equals("V")) {
                    if(newLocation.latitude() > 39.8668 && newLocation.latitude() < 39.8673 && newLocation.longitude() > 32.7496 && newLocation.longitude() < 32.7505) {
                        buttonGo2.setEnabled(true);
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        buttonGo2.setEnabled(false);
                    }
                }
            }
        });
        buttonGo2.setEnabled(false);
    }

    /**
     * isLocationEnabled method, returns true if location is enabled
     * @param context
     * @return  boolean
     */
    private boolean isLocationEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return LocationManagerCompat.isLocationEnabled(locationManager);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) { }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocationComponent(mapboxMap.getStyle());
        } else {
            Toast.makeText(getApplicationContext(), "Permission not granted", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        if( isLocationEnabled(this) == false){
            Toast.makeText(getApplicationContext(),  "You need to enable GPS before continue.", Toast.LENGTH_LONG).show();
            startActivity( new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS) );
            finish();
        }
        else {
            this.mapboxMap = mapboxMap;
            this.mapboxMap.setMinZoomPreference(0.1);
            mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(Style style) {
                    enableLocationComponent(style);
                    addDestinationIconLayer(style);
                }
            });
        }
    }
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            locationComponent = mapboxMap.getLocationComponent();
            locationComponent.activateLocationComponent(this, loadedMapStyle);
            locationComponent.setLocationComponentEnabled(true);
            locationComponent.setCameraMode(CameraMode.TRACKING);
        }
            else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }

    }

    private void addDestinationIconLayer(Style style) {
        style.addImage("destination-icon-id", BitmapFactory.decodeResource(this.getResources(),
                R.drawable.mapbox_marker_icon_default));
        GeoJsonSource source = new GeoJsonSource("destination-source-id");
        style.addSource(source);
        SymbolLayer destinationSymbolLayer = new SymbolLayer("destination-symbol-layer-id","destination-source-id");
        destinationSymbolLayer.withProperties(iconImage("destination-icon-id"), iconAllowOverlap(true),
                iconIgnorePlacement(true));
        style.addLayer(destinationSymbolLayer);

        if ( source != null ) {
            Toast.makeText(this, "Please click check arrival button when you arrive the building.", Toast.LENGTH_LONG).show();
            if (buildingName.equals("A")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.749340, 39.868065)));
            }
            if (buildingName.equals("B")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.7482, 39.8686)));
            }
            if (buildingName.equals("EA")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.75010, 39.871125)));
            }
            if (buildingName.equals("EE")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.750600, 39.872060)));
            }
            if (buildingName.equals("FF")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.748890, 39.8658865)));
            }
            if (buildingName.equals("G")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.749600, 39.868700)));
            }
            if (buildingName.equals("SA")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.748395, 39.867700)));
            }
            if (buildingName.equals("V")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.750000, 39.867100)));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    public void onBackPressed(){

        Intent intent = new Intent(GameModeMap.this, MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        navigationView.onRestoreInstanceState(savedInstanceState);
    }
}