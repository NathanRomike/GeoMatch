package comnathanromike.github.geomatch.services;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.models.SnapMapGroup;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    public ArrayList<SnapMapGroup> processResults(Response response) {
        ArrayList<SnapMapGroup> snapMapGroups = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject groupJSON = new JSONObject(jsonData);
                JSONObject photosJSON = groupJSON.getJSONObject("photos");
                String totalPhotos = photosJSON.getString("total");
                JSONArray listOfPhotosArray = photosJSON.getJSONArray("photo");
                for (int i = 0; i < listOfPhotosArray.length(); i++) {
                    JSONObject photoJSON = listOfPhotosArray.getJSONObject(i);
                    String photoId = photoJSON.getString("id");
                    String ownerId = photoJSON.getString("owner");
                    String title = photoJSON.getString("title");
                    String ownerName = photoJSON.getString("ownername");

                    SnapMapGroup snapMapGroup = new SnapMapGroup(totalPhotos, photoId, ownerId, title, ownerName);
                    snapMapGroups.add(snapMapGroup);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return snapMapGroups;
    }
}
