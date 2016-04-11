package comnathanromike.github.geomatch.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.SnapMapApplication;
import comnathanromike.github.geomatch.adapters.PhotoListAdapter;
import comnathanromike.github.geomatch.models.PuzzlePhoto;
import comnathanromike.github.geomatch.services.TaggedPhotosService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PuzzleListActivity extends AppCompatActivity {
    public static final String TAG = PuzzleListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private Firebase mFirebaseRef;
    private PhotoListAdapter mAdapter;
    public ArrayList<String> mPhotoIdsList = new ArrayList<>();
    public ArrayList<String> mPhotoMUrls = new ArrayList<>();


    public ArrayList<PuzzlePhoto> mPhotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clues);

        getPhotosByTag();

        ButterKnife.bind(this);

        mFirebaseRef = SnapMapApplication.getAppInstance().getFirebaseRef();
        mAdapter = new PhotoListAdapter(getApplicationContext(), mPhotos);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PuzzleListActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                this.logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        mFirebaseRef.unauth();
        Intent intent = new Intent(PuzzleListActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void getPhotosByTag() {
        final TaggedPhotosService taggedPhotosService = new TaggedPhotosService(this);

        taggedPhotosService.findPhotosInGroup(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mPhotos = taggedPhotosService.processResults(response);

                PuzzleListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new PhotoListAdapter(getApplicationContext(), mPhotos);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PuzzleListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        String[] photoIds = new String[mPhotos.size()];
                        String[] photoMUrl = new String[mPhotos.size()];
                        for (int i = 0; i < photoIds.length; i++) {
                            photoIds[i] = mPhotos.get(i).getPhotoId();
                            photoMUrl[i] = mPhotos.get(i).getMediumPhotoUrl();
                            mPhotoIdsList.add(photoIds.toString());
                            mPhotoMUrls.add(photoMUrl.toString());
                        }
//                        for (PuzzlePhoto snapMapGroup : mSnapPhotos) {
//                            Log.d("RESULTS", "Ids " + snapMapGroup.getTitle());
//                        }
                    }
                });
            }
        });
    }
}
