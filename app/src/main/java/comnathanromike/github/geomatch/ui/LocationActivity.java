package comnathanromike.github.geomatch.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.SnapMapApplication;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, View.OnClickListener {
    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Bind(R.id.confirmButton) Button mConfirmButton;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private LocationRequest mLocationRequest;
    private LatLng mCurrentLocation;
    private Firebase mFirebaseRef;
    private String mPhotoId;
    private String mCurrentUserUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        mPhotoId = intent.getStringExtra("photoId");
        mSharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mFirebaseRef = SnapMapApplication.getAppInstance().getFirebaseRef();
        mCurrentUserUid = mFirebaseRef.getAuth().getUid();

        mConfirmButton.setOnClickListener(this);

        int permissionCheck = ContextCompat.checkSelfPermission(LocationActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        Log.d("permission check", Integer.toString(permissionCheck));

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(10 * 1000)
                .setFastestInterval(1 * 1000);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng defaultLocation = new LatLng(-45.5231, 122.6765);
        mMap.addMarker(new MarkerOptions().position(defaultLocation).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation));
    }

    @Override
    public void onConnected(Bundle bundle) {
        try {
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (location == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                handleNewLocation(location);
            } else {
                mCurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                handleNewLocation(location);
            }
        } catch (SecurityException e) {
            Log.d("security exception", e.toString());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("LAST KNOWN LOCATION", "Location services suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("Location failed", connectionResult.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
            mGoogleApiClient.disconnect();
        }
    }

    private void handleNewLocation(Location location) {
        Log.d("Location", location.toString());
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("You are here!");
        mMap.addMarker(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
    }

    @Override
    public void onLocationChanged(Location location) {
        addToSharedPreferences(location.toString());
        handleNewLocation(location);
    }

    @Override
    public void onClick(View view) {
        addToSharedPreferences(mCurrentLocation.toString());
        mFirebaseRef.child("photos/" + mPhotoId + "/").child(mCurrentUserUid + "/")
                .setValue(mCurrentLocation);
        Toast.makeText(LocationActivity.this, "Your guess has been saved.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LocationActivity.this, PuzzleListActivity.class);
        intent.putExtra("confirmStatus", "Confirmed");
        startActivity(intent);
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString("location", location).commit();
    }
}
