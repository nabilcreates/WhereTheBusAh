package com.nabil.wherethebusah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nabil.wherethebusah.Fetchers.FetchBusTimings;

public class BusTimingActivity extends AppCompatActivity {

    String bus_stop_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);

        bus_stop_code = getIntent().getStringExtra("busStopCode");

        if(bus_stop_code != null){
            new FetchBusTimings(BusTimingActivity.this).execute(bus_stop_code);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timing_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.refresh_menu_button){
            new FetchBusTimings(BusTimingActivity.this).execute(bus_stop_code);
            return true;
        }else{
            return false;
        }
    }
}
