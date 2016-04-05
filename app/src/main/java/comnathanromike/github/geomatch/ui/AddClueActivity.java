package comnathanromike.github.geomatch.ui;

import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;

public class AddClueActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    @Bind(R.id.addPhotoButton) Button mAddPhotoButton;
    @Bind(R.id.photoHintEditText) EditText mAddHint;
    @Bind(R.id.locationTextView) TextView mLocationTextView;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Double mLatitude;
    private Double mLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clue);

        ButterKnife.bind(this);

        mAddPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddClueActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitude = mLastLocation.getLatitude();
            mLongitude = mLastLocation.getLongitude();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
