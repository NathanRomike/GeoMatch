package comnathanromike.github.geomatch.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.models.PuzzlePhoto;
import comnathanromike.github.geomatch.ui.PuzzleListActivity;

/**
 * Created by nathanromike on 3/27/16.
 */
public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder> {
    private ArrayList<PuzzlePhoto> mPuzzlePhotos = new ArrayList<>();
    private Context mContext;

    public PhotoListAdapter(Context context, ArrayList<PuzzlePhoto> photos) {
        mContext = context;
        mPuzzlePhotos = photos;
    }

    @Override
    public PhotoListAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoListAdapter.PhotoViewHolder holder, int position) {
        holder.bindPhoto(mPuzzlePhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mPuzzlePhotos.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.mediumPhotoImageView) ImageView mMediumImageView;
        @Bind(R.id.photoTitleTextView) TextView mTitleTextView;
        private Context mContext;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int itemPosition = getLayoutPosition();
                    Intent intent = new Intent(mContext, PuzzleListActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("photos", Parcels.wrap(mPuzzlePhotos));
                    mContext.startActivity(intent);
                }
            });
        }

        public void bindPhoto(PuzzlePhoto photo) {
            Picasso.with(mContext).load(photo.getMediumPhotoUrl()).into(mMediumImageView);
            mTitleTextView.setText(photo.getTitle());
        }
    }
}
