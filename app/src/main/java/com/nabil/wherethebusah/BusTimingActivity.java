package com.nabil.wherethebusah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArraySet;
import android.view.Menu;
import android.view.MenuItem;

import com.nabil.wherethebusah.Fetchers.FetchBusTimings;
import com.nabil.wherethebusah.Handlers.FavouritesHandler;

import java.util.HashSet;
import java.util.Set;

public class BusTimingActivity extends AppCompatActivity {

    SharedPreferences sp;
    String bus_stop_code;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_timing);


        bus_stop_code = getIntent ().getStringExtra ("busStopCode");

        if (bus_stop_code != null) {
            new FetchBusTimings (BusTimingActivity.this).execute (bus_stop_code);
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater ().inflate (R.menu.timing_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        switch (item.getItemId ()) {
            case R.id.refresh_menu_button:
                new FetchBusTimings (BusTimingActivity.this).execute (bus_stop_code);
                return true;
            case R.id.favourite:
                // Write the sharedPreferences
                new FavouritesHandler (BusTimingActivity.this).addBusStop(bus_stop_code);
                return true;
            default:
                return false;
        }
    }
}
