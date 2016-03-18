package comnathanromike.github.geomatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddClueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clue);

        this.getSupportActionBar().setTitle(R.string.blank_text);
        this.getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.getSupportActionBar().setLogo(R.drawable.icon);
    }
}
