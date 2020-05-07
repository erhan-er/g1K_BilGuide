package com.g1k.bilguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Bundle;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.location.LocationManagerCompat;

import android.widget.Toast;

// classes needed to initialize map
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

// classes needed to add the location component
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;

// classes needed to add a marker
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;

// classes to calculate a route
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

// classes needed to launch navigation UI
import android.view.View;
import android.widget.Button;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;

/**
 * InfoModeMapsActivity, which draws route from location to given info, and start navigation button
 * @author Abdullah, Aslı
 * @version 1.0
 */

public class InfoModeMapsActivity extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener {

    // variables

    // adding location layer
    private MapView mapView;
    private MapboxMap mapboxMap;
    // getting location
    private PermissionsManager permissionsManager;
    private LocationComponent locationComponent;
    // routes
    private DirectionsRoute currentRoute;
    private static final String TAG = "DirectionsActivity";
    private NavigationMapRoute navigationMapRoute;
    // buttons
    private Button buttonStartNav;
    private Button buttonBackMap;
    private Button buttonMainMenuMap;
    // latitude and longitude info
    private String fromSelectLat;
    private String fromSelectLng;


    // methods

    // default method for android apps
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));     //api key for maps
        setContentView(R.layout.activity_infomodemaps);

        // defining mapview and getting map
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // defining buttons
        buttonBackMap = findViewById(R.id.buttonBackMap);
        buttonMainMenuMap = findViewById(R.id.buttonMainMenuMap);
        buttonMainMenuMap = findViewById(R.id.buttonMainMenuMap);

        // listener for main menu button
        buttonMainMenuMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), MainMenuActivity.class) );
                finish();
            }
        });

        // listener for back button
        buttonBackMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), SelectBuilding.class) );
                finish();
            }
        });

        //getting info from SelectBuilding class
        Intent intent = getIntent();
        fromSelectLat = intent.getStringExtra("lat");
        fromSelectLng = intent.getStringExtra("lng");
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

    /**
     * onMapReady method that makes map ready
     * @param mapboxMap
     */
    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {

        // if location is disabled, user is directed to location settings to open location.
        if( isLocationEnabled(this) == false ){
            Toast.makeText(InfoModeMapsActivity.this, "You need to enable GPS before continue.", Toast.LENGTH_LONG).show();
            startActivity( new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS) );
            finish();
        }
        else{
            this.mapboxMap = mapboxMap;

            //setting style for map
            mapboxMap.setStyle(getString(R.string.mapbox_style_satellite_streets), new Style.OnStyleLoaded() {      // street style
                @Override
                public void onStyleLoaded(@NonNull Style style) {

                    enableLocationComponent(style);
                    addDestinationIconSymbolLayer(style);

                    // determining start(location) point and end point
                    Point destinationPoint = Point.fromLngLat(Double.parseDouble(fromSelectLng), Double.parseDouble(fromSelectLat));
                    Point originPoint = Point.fromLngLat(locationComponent.getLastKnownLocation().getLongitude(), locationComponent.getLastKnownLocation().getLatitude());

                    GeoJsonSource source = mapboxMap.getStyle().getSourceAs("destination-source-id");
                    if (source != null) {
                        source.setGeoJson(Feature.fromGeometry(destinationPoint));
                    }

                    getRoute(originPoint, destinationPoint);      // calling getRoute method

                    // defining start navigation button and button click listener
                    buttonStartNav = findViewById(R.id.buttonStartNav);
                    buttonStartNav.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            NavigationLauncherOptions navLauncherOptions = NavigationLauncherOptions.builder().directionsRoute(currentRoute).build();
                            NavigationLauncher.startNavigation(InfoModeMapsActivity.this, navLauncherOptions);      // starting navigation activity
                        }
                    });
                }
            });
        }

    }

    /**
     * adding icon to the selected point method
     * @param loadedMapStyle
     */
    private void addDestinationIconSymbolLayer(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addImage("destination-icon-id", BitmapFactory.decodeResource(this.getResources(), R.drawable.mapbox_marker_icon_default));
        GeoJsonSource geoJsonSource = new GeoJsonSource("destination-source-id");
        loadedMapStyle.addSource(geoJsonSource);

        SymbolLayer destinationSymbolLayer = new SymbolLayer("destination-symbol-layer-id", "destination-source-id");
        destinationSymbolLayer.withProperties( iconImage("destination-icon-id"), iconAllowOverlap(true), iconIgnorePlacement(true) );
        loadedMapStyle.addLayer(destinationSymbolLayer);
    }

    /**
     * getting route from origin to destination method
     * @param origin
     * @param destination
     */
    private void getRoute(Point origin, Point destination) {
        NavigationRoute.builder(this).accessToken(Mapbox.getAccessToken()).origin(origin).destination(destination)
                .profile(DirectionsCriteria.PROFILE_WALKING).build().getRoute(new Callback<DirectionsResponse>() {      // walking routes
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {

                        // if there are no routes, it returns anything so route cannot be gotten.
                        if (response.body() == null) {
                            return;
                        } else if (response.body().routes().size() < 1) {
                            return;
                        }
                        currentRoute = response.body().routes().get(0);

                        // drawnig the route on map
                        if (navigationMapRoute != null) {
                            navigationMapRoute.removeRoute();
                        } else {
                            navigationMapRoute = new NavigationMapRoute(null, mapView, mapboxMap, R.style.NavigationMapRoute);
                        }
                        navigationMapRoute.addRoute(currentRoute);
                    }

                    // inner method for failure
                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage());
                    }
                });
    }

    /**
     * this method puts location mark if the user enabled location.
     * @param loadedMapStyle
     */
    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        // checking whether location is enabled or not
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            // activating Mapbox component
            locationComponent = mapboxMap.getLocationComponent();
            locationComponent.activateLocationComponent(this, loadedMapStyle);
            locationComponent.setLocationComponentEnabled(true);

            // setting camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);
        }
        else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // if location is disabled, this toast message will appear.
    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    // enabling location component according to enableLocationComponent method.
    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocationComponent(mapboxMap.getStyle());
        } else {
            Toast.makeText(this, "You did not grant location permissions.", Toast.LENGTH_LONG).show();
            finish();
        }
    }


    /**
     * some necessary methods for Mapbox to work.
     */

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
}

