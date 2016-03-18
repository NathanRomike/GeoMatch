package comnathanromike.github.geomatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewCluesActivity extends AppCompatActivity {
    public static final String TAG = ViewCluesActivity.class.getSimpleName();
    private TextView mPhotoHints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clues);

        mPhotoHints = (TextView) findViewById(R.id.hintTextView);

        Intent intent = getIntent();
        String photoHints = intent.getStringExtra("photoHints");

        Log.d(TAG, "Intent" + photoHints);

        if (photoHints != null) {
            mPhotoHints.setText(photoHints);
        } else {
            mPhotoHints.setText("Clues:");
        }

    }
}
