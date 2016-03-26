package comnathanromike.github.geomatch.services;

import android.content.Context;

import comnathanromike.github.geomatch.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by nathanromike on 3/25/16.
 */
public class GroupCollectionService {
    private Context mContext;

    public GroupCollectionService(Context context) {
        this.mContext = context;
    }

    public void findPhotosByTag(Callback callback) {
        String API_KEY = mContext.getString(R.string.api_key);
        String API_SECRET = mContext.getString(R.string.api_secret);
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(API_KEY, API_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://www.flickr.com/services/rest/?method=flickr.groups.pools.getPhotos&format=json&group_id=2930075%40N20&").newBuilder();

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
