package comnathanromike.github.geomatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class ViewCluesActivity extends AppCompatActivity {
    public static final String TAG = ViewCluesActivity.class.getSimpleName();
    private TextView mPhotoHints;
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clues);

        mPhotoHints = (TextView) findViewById(R.id.hintTextView);
        mGridView = (GridView) findViewById(R.id.cluesGridView);

        mGridView.setAdapter(new ImageAdapter(this));

        Intent intent = getIntent();
        String photoHints = intent.getStringExtra("photoHints");

        if (photoHints != null) {
            mPhotoHints.setText(photoHints);
        } else {
            mPhotoHints.setText("Clues:");
        }

    }
}
