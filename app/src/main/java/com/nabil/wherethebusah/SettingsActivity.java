package com.nabil.wherethebusah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_settings);

        ((Button) findViewById (R.id.delete_all_favourites)).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                getSharedPreferences (getPackageName (), MODE_PRIVATE).edit ().clear ().apply ();
            }
        });
    }
}
