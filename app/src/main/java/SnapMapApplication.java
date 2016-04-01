import android.app.Application;

import com.firebase.client.Firebase;

import comnathanromike.github.geomatch.R;

/**
 * Created by nathanromike on 4/1/16.
 */
public class SnapMapApplication extends Application {
    private static SnapMapApplication app;
    private Firebase mFirebaseRef;

    public static SnapMapApplication getAppInstance() {
        return app;
    }

    public Firebase getFirebaseRef() {
        return mFirebaseRef;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase(this.getString(R.string.firebase_url));
    }
}
