package comnathanromike.github.geomatch.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.adapters.PhotoListAdapter;
import comnathanromike.github.geomatch.models.PuzzlePhoto;

public class PuzzleListActivity extends AppCompatActivity {
    public static final String TAG = PuzzleListActivity.class.getSimpleName();

    @Bind(R.id.hintTextView) TextView mPhotoHints;
    @Bind(R.id.cluesListView) ListView mListView;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private PhotoListAdapter mAdapter;

    public ArrayList<PuzzlePhoto> mPhotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clues);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        ArrayList<String> photoIdList = intent.getStringArrayListExtra("photosIdList");
        ArrayList<String> photoMUrls = intent.getStringArrayListExtra("photoMUrls");

        mAdapter = new PhotoListAdapter(getApplicationContext(), mPhotos);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PuzzleListActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

    }
}
