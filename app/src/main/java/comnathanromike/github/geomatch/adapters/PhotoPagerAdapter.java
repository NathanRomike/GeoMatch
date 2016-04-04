package comnathanromike.github.geomatch.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import comnathanromike.github.geomatch.models.PuzzlePhoto;
import comnathanromike.github.geomatch.fragments.PhotoDetailFragment;

/**
 * Created by Guest on 3/29/16.
 */
public class PhotoPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<PuzzlePhoto> mPhotos;

    public PhotoPagerAdapter(FragmentManager fm, ArrayList<PuzzlePhoto> photos) {
        super(fm);
        mPhotos = photos;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoDetailFragment.newInstance(mPhotos.get(position));
    }

    @Override
    public int getCount() {
        return mPhotos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPhotos.get(position).getTitle();
    }

}
