package comnathanromike.github.geomatch.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;

public class PuzzleListActivity extends AppCompatActivity {
    public static final String TAG = PuzzleListActivity.class.getSimpleName();

    @Bind(R.id.hintTextView) TextView mPhotoHints;
    @Bind(R.id.cluesListView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clues);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        ArrayList<String> photoIdList = intent.getStringArrayListExtra("photosIdList");


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, photoIdList);
        mListView.setAdapter(adapter);

    }
}
