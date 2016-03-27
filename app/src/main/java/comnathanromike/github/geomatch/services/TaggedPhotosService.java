package comnathanromike.github.geomatch.services;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import comnathanromike.github.geomatch.R;
import comnathanromike.github.geomatch.models.PuzzlePhoto;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by nathanromike on 3/25/16.
 */
public class TaggedPhotosService {
    private Context mContext;

    public TaggedPhotosService(Context context) {
        this.mContext = context;
    }

    public void findPhotosInGroup(Callback callback) {
        String API_KEY = mContext.getString(R.string.api_key);
        String API_SECRET = mContext.getString(R.string.api_secret);
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(API_KEY, API_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://www.flickr.com/services/rest/?method=flickr.photos.search&tags=SnapMap&extras=geo%2C+url_s%2C+url_m%2C+url_z&&format=json&nojsoncallback=1").newBuilder();

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<PuzzlePhoto> processResults(Response response) {
        ArrayList<PuzzlePhoto> snapMapGroups = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            MediaType jsonResponse = response.body().contentType();

            if (response.isSuccessful()) {
                JSONObject groupJSON = new JSONObject(jsonData);
                JSONObject photosJSON = groupJSON.getJSONObject("photos");
                JSONArray listOfPhotosArray = photosJSON.getJSONArray("photo");
                for (int i = 0; i < listOfPhotosArray.length(); i++) {
                    JSONObject photoJSON = listOfPhotosArray.getJSONObject(i);
                    String photoId = photoJSON.getString("id");
                    String ownerId = photoJSON.getString("owner");
                    String title = photoJSON.getString("title");
                    Double latitude = photoJSON.getDouble("latitude");
                    Double longitude = photoJSON.getDouble("longitude");
                    String thumbnailUrl = photoJSON.getString("url_s");
                    String mediumPhotoUrl = photoJSON.getString("url_m");
                    String largePhotoUrl = photoJSON.getString("url_z");

                    PuzzlePhoto snapMapGroup = new PuzzlePhoto(photoId, ownerId, title, latitude, longitude, thumbnailUrl, mediumPhotoUrl, largePhotoUrl);
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
