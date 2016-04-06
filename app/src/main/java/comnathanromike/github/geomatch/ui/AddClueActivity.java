package comnathanromike.github.geomatch.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;

public class AddClueActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Bind(R.id.addPhotoButton) Button mAddPhotoButton;
    @Bind(R.id.takePhotoButton) Button mTakePhotoButton;
    @Bind(R.id.photoHintEditText) EditText mAddHint;
    @Bind(R.id.addImageView) ImageView mAddImageView;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Double mLatitude;
    private Double mLongitude;
    private String mConfirmStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clue);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        mConfirmStatus = intent.getStringExtra("confirmStatus");

        if (mConfirmStatus == null) {
            mAddPhotoButton.setText("Confirm Location");
        }

        mAddPhotoButton.setOnClickListener(this);
        mTakePhotoButton.setOnClickListener(this);

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

    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mAddImageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mAddPhotoButton) {
            if (mConfirmStatus == null) {
                Intent intent = new Intent(AddClueActivity.this, LocationActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(AddClueActivity.this, PuzzleListActivity.class);
                startActivity(intent);
            }
        } else {
            capturePhoto();
        }
    }
}
