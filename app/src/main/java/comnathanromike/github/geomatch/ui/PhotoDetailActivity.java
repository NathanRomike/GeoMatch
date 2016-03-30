package comnathanromike.github.geomatch.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.adapters.PhotoPagerAdapter;
import comnathanromike.github.geomatch.models.PuzzlePhoto;

public class PhotoDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private PhotoPagerAdapter adapterViewPager;
    ArrayList<PuzzlePhoto> mPhotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        ButterKnife.bind(this);
        mPhotos = Parcels.unwrap(getIntent().getParcelableExtra("photos"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        adapterViewPager = new PhotoPagerAdapter(getSupportFragmentManager(), mPhotos);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
