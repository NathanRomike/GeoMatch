package comnathanromike.github.geomatch.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.models.PuzzlePhoto;

public class PhotoDetailFragment extends Fragment {
    @Bind(R.id.photoImageView) ImageView mImageMedium;
    private PuzzlePhoto mPuzzlePhoto;

    public PhotoDetailFragment() {}

    public static PhotoDetailFragment newInstance(PuzzlePhoto puzzlePhoto) {
        PhotoDetailFragment fragment = new PhotoDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("photo", Parcels.wrap(puzzlePhoto));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPuzzlePhoto = Parcels.unwrap(getArguments().getParcelable("photo"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mPuzzlePhoto.getMediumPhotoUrl())
                .into(mImageMedium);
        return view;
    }
}
