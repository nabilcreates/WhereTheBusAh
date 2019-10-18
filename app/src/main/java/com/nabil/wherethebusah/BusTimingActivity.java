package com.nabil.wherethebusah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArraySet;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nabil.wherethebusah.Fetchers.FetchBusTimings;
import com.nabil.wherethebusah.Handlers.FavouritesHandler;

import java.util.HashSet;
import java.util.Set;

public class BusTimingActivity extends AppCompatActivity {

    SharedPreferences sp;
    String bus_stop_code;
    String bus_stop_name;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_timing);


        bus_stop_code = getIntent ().getStringExtra ("busStopCode");
        bus_stop_name = getIntent ().getStringExtra ("busStopName");

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
                new FavouritesHandler (BusTimingActivity.this).addBusStop(bus_stop_name, bus_stop_code);

                // Show a toast to notify that it is added to favourites
                Toast.makeText (getApplicationContext (), "Added to Favourites!", Toast.LENGTH_LONG).show ();
                return true;
            default:
                return false;
        }
    }
}
