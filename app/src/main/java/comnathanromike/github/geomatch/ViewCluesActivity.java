package comnathanromike.github.geomatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewCluesActivity extends AppCompatActivity {
    public static final String TAG = ViewCluesActivity.class.getSimpleName();
    private String[] clues = new String[] {
            "Rain is frequent at this location",
            "This city was almost called 'Boston'",
            "This location would prefer to be weird",
            "LOOK AT THE SIGN!"
    };

    @Bind(R.id.hintTextView) TextView mPhotoHints;
//    @Bind(R.id.cluesGridView) GridView mGridView;
    @Bind(R.id.cluesListView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clues);

        ButterKnife.bind(this);

//        mGridView.setAdapter(new ImageAdapter(this));

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, clues);
        mListView.setAdapter(adapter);

        Intent intent = getIntent();
        String photoHints = intent.getStringExtra("photoHints");

        if (photoHints != null) {

            mPhotoHints.setText(photoHints);
        } else {
            mPhotoHints.setText("Clues:");
        }

    }
}
