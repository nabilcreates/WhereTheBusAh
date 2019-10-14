package com.nabil.wherethebusah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
}
