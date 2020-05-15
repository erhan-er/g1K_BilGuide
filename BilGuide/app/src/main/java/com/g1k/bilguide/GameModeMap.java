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

/**
 * GameModeMap class, directs users to some buildings in map
 * @author Furkan Turunç, Murat Furkan Uğurlu, Recep Uysal
 * @version 1.0
 */
public class GameModeMap extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener {

    // proporties
    private MapView            mapView;
    private MapboxMap          mapboxMap;
    private LocationComponent  locationComponent;
    private PermissionsManager permissionsManager;
    private Intent             intent2;
    private Button             buttonGo2;
    private String             buildingName;
    private NavigationView     navigationView;
    private Button checkArrival;

    // methods
    /**
     * This method is the default method of android studio, which applies main process for widgets.
     * @param savedInstanceState , a Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_NoActionBar);

        // initialising map objects
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_game_mode_map);


        // linking mapView
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // linking buttons
        buttonGo2 = findViewById(R.id.buttonGo2);
        checkArrival = findViewById(R.id.checkArrival);

        // linking navigationview
        navigationView = findViewById(R.id.navigationView);

        // get the building name from GameModeMain class
        buildingName = getIntent().getStringExtra("answer");

        // When the button go was pressed, this method works
        buttonGo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creating new intent to go to the GameModeDiscovery activity
                intent2 = new Intent(GameModeMap.this, GameModeDiscovery.class);

                // sending the building name to the GameModeDiscovery Class
                intent2.putExtra("answer",buildingName);

                // start intent
                startActivity(intent2);
            }
        });

        // When the button checkArrival was pressed, this method works
        checkArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get users last location
                Point newLocation = Point.fromLngLat(locationComponent.getLastKnownLocation().getLongitude(),locationComponent.getLastKnownLocation().getLatitude());

                // For building A
                // checking the user arrived the building or not
                if( buildingName.equals("A")) {
                    if (newLocation.latitude() > 39.8675 && newLocation.latitude() < 39.8682 && newLocation.longitude() > 32.7492 && newLocation.longitude() < 32.7502) {
                        // setting Go button enabled
                        buttonGo2.setEnabled(true);
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        // setting Go button disabled
                        buttonGo2.setEnabled(false);
                    }
                }

                // For building B
                // checking the user arrived the building or not
                if( buildingName.equals("B")) {
                    if(newLocation.latitude() > 39.8685 && newLocation.latitude() < 39.8690 && newLocation.longitude() > 32.7475 && newLocation.longitude() < 32.7485) {
                        // setting Go button enabled
                        buttonGo2.setEnabled(true);
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        // setting Go button disabled
                        buttonGo2.setEnabled(false);
                    }
                }

                // For building EA
                // checking the user arrived the building or not
                if( buildingName.equals("EA")) {
                    if (newLocation.latitude() > 39.8708 && newLocation.latitude() < 39.8715 && newLocation.longitude() > 32.7497 && newLocation.longitude() < 32.7504) {
                        // setting Go button enabled
                        buttonGo2.setEnabled(true);
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        // setting Go button disabled
                        buttonGo2.setEnabled(false);
                    }
                }

                // For building EE
                // checking the user arrived the building or not
                if( buildingName.equals("EE")) {
                    if(newLocation.latitude() > 39.8719 && newLocation.latitude() < 39.8722 && newLocation.longitude() > 32.7504 && newLocation.longitude() < 32.7510) {
                        // setting Go button enabled
                        buttonGo2.setEnabled(true);
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        // setting Go button disabled
                        buttonGo2.setEnabled(false);
                    }
                }

                // For building FF
                // checking the user arrived the building or not
                if( buildingName.equals("FF")) {
                    if(newLocation.latitude() > 39.8655 && newLocation.latitude() < 39.8665 && newLocation.longitude() > 32.7485 && newLocation.longitude() < 32.7495) {
                        // setting Go button enabled
                        buttonGo2.setEnabled(true);
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        // setting Go button disabled
                        buttonGo2.setEnabled(false);
                    }
                }

                // For building G
                // checking the user arrived the building or not
                if( buildingName.equals("G")) {
                    if(newLocation.latitude() > 39.8682 && newLocation.latitude() < 39.8689 && newLocation.longitude() > 32.7494 && newLocation.longitude() < 32.7504) {
                        // setting Go button enabled
                        buttonGo2.setEnabled(true);
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        // setting Go button disabled
                        buttonGo2.setEnabled(false);
                    }
                }

                // For building SA
                // checking the user arrived the building or not
                if( buildingName.equals("SA")) {
                    if(newLocation.latitude() > 39.8675 && newLocation.latitude() < 39.8680 && newLocation.longitude() > 32.7480 && newLocation.longitude() < 32.7485) {
                        // setting Go button enabled
                        buttonGo2.setEnabled(true);
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        // setting Go button disabled
                        buttonGo2.setEnabled(false);
                    }
                }

                // For building V
                // checking the user arrived the building or not
                if( buildingName.equals("V")) {
                    if(newLocation.latitude() > 39.8668 && newLocation.latitude() < 39.8673 && newLocation.longitude() > 32.7496 && newLocation.longitude() < 32.7505) {
                        // setting Go button enabled
                        buttonGo2.setEnabled(true);
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You have arrived! Discover the building!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // showing toast message
                        Toast.makeText(GameModeMap.this, "You haven't arrived yet", Toast.LENGTH_SHORT).show();
                        // setting Go button disabled
                        buttonGo2.setEnabled(false);
                    }
                }
            }
        });
        // set Go button disabled
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

    /**
     * onPermissionResult method, checks the permission is granted or not
     * @param granted
     */
    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocationComponent(mapboxMap.getStyle());
        } else {
            // showing the toast message
            Toast.makeText(getApplicationContext(), "Permission not granted", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    /**
     * onMapReady method, checks the map is ready to be used
     * @param mapboxMap
     */
    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        // checking the GPS is enabled
        if( isLocationEnabled(this) == false){
            Toast.makeText(getApplicationContext(),  "You need to enable GPS before continue.", Toast.LENGTH_LONG).show();
            startActivity( new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS) );
            finish();
        }
        else {
            this.mapboxMap = mapboxMap;

            // setting the map zoom
            this.mapboxMap.setMinZoomPreference(0.1);

            // setting the map style
            mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(Style style) {
                    enableLocationComponent(style);
                    addDestinationIconLayer(style);
                }
            });
        }
    }

    /**
     * enableLocationComponent method, checks the map is ready to be used
     * @param loadedMapStyle
     */
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

    /**
     * addDestinationIconLayer method, adds destination icon layer to the choosen building
     * @param style
     */
    private void addDestinationIconLayer(Style style) {
        // setting style
        style.addImage("destination-icon-id", BitmapFactory.decodeResource(this.getResources(),
                R.drawable.mapbox_marker_icon_default));
        GeoJsonSource source = new GeoJsonSource("destination-source-id");
        style.addSource(source);
        SymbolLayer destinationSymbolLayer = new SymbolLayer("destination-symbol-layer-id","destination-source-id");
        destinationSymbolLayer.withProperties(iconImage("destination-icon-id"), iconAllowOverlap(true),
                iconIgnorePlacement(true));
        style.addLayer(destinationSymbolLayer);

        // adding icon layers for choosen building
        if ( source != null ) {
            Toast.makeText(this, "Please click check arrival button when you arrive the building.", Toast.LENGTH_LONG).show();
            // For building A
            if (buildingName.equals("A")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.749340, 39.868065)));
            }

            // For building B
            if (buildingName.equals("B")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.7482, 39.8686)));
            }

            // For building EA
            if (buildingName.equals("EA")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.75010, 39.871125)));
            }

            // For building EE
            if (buildingName.equals("EE")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.750600, 39.872060)));
            }

            // For building FF
            if (buildingName.equals("FF")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.748890, 39.8658865)));
            }

            // For building G
            if (buildingName.equals("G")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.749600, 39.868700)));
            }

            // For building SA
            if (buildingName.equals("SA")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.748395, 39.867700)));
            }

            // For building V
            if (buildingName.equals("V")) {
                source.setGeoJson(Feature.fromGeometry(Point.fromLngLat(32.750000, 39.867100)));
            }
        }
    }

    /**
     * onRequestPermissionsResult method, checks the permissions are granted or not
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    /**
     * onStart method, starts the activity and mapView
     */
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    /**
     * onResume method, resumes the activity and mapView
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();

    }

    /**
     * onPause method, pauses the activity and mapView
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * onStop method, stops the activity and mapView
     */
    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    /**
     * onBackPressed method, sets the function of back button
     */
    public void onBackPressed(){
        // creating and initialising new intent
        Intent intent = new Intent(GameModeMap.this, MainMenuActivity.class);
        // back to the main menu
        startActivity(intent);
    }

    /**
     * onSaveInstanceState method, saves the state of the activity and mapView
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * onDestroy method, finishes the activity and mapView
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * onLowMemory method, kills the background process of the activity and mapView when the phone memory is low
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    /**
     * onRestoreInstanceState method, restores the state of the activity and navigationView
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        navigationView.onRestoreInstanceState(savedInstanceState);
    }
}
