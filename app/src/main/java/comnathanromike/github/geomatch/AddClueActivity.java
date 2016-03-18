package comnathanromike.github.geomatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddClueActivity extends AppCompatActivity {
    private Button mAddPhotoButton;
    private EditText mAddHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clue);

        this.getSupportActionBar().setTitle(R.string.blank_text);
        this.getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.getSupportActionBar().setLogo(R.drawable.icon);

        mAddPhotoButton = (Button) findViewById(R.id.addPhotoButton);
        mAddHint = (EditText) findViewById(R.id.photoHintEditText);

        mAddPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String photoHints = mAddHint.getText().toString();
                Intent intent = new Intent(AddClueActivity.this, ViewCluesActivity.class);
                intent.putExtra("photoHints", photoHints);
                startActivity(intent);
            }
        });


    }
}
