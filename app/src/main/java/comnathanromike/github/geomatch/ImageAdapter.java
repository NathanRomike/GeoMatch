package comnathanromike.github.geomatch;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Guest on 3/18/16.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public Integer[] sample_clues = {
            R.drawable.whistler,
            R.drawable.toronto_buildings,
            R.drawable.toronto_buildings_2,
            R.drawable.vancouver_water,
            R.drawable.muskoka,
    };

    public ImageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return sample_clues.length;
    }

    @Override
    public Object getItem(int position) {
        return sample_clues[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        Picasso
                .with(mContext)
                .load("R.drawable.whistler")
                .fit()
                .into(imageView);

//        ImageView.setImageResource(sample_clues[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}
