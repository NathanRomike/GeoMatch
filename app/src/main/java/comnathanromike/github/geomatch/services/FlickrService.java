package comnathanromike.github.geomatch.services;

import android.content.Context;

import comnathanromike.github.geomatch.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by nathanromike on 3/25/16.
 */
public class FlickrService {
    private Context mContext;

    public FlickrService(Context context) {
        this.mContext = context;
    }

    public void findPhotosByTag(String location, Callback callback) {
        String API_KEY = mContext.getString(R.string.api_key);
        String API_SECRET = mContext.getString(R.string.api_secret);

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://www.flickr.com/services/rest/?method=flickr.tags.getClusterPhotos&format=json&tag=SnapMap").newBuilder();

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
