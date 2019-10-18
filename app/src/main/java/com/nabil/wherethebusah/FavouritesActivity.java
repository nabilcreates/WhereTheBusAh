package com.nabil.wherethebusah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArraySet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nabil.wherethebusah.Adapters.FavouritesAdapter;
import com.nabil.wherethebusah.Handlers.FavouritesHandler;

import java.lang.reflect.Array;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FavouritesActivity extends AppCompatActivity {

    ListView listFavourites;
    TextView no_favourites;
    FavouritesHandler favouritesHandler_init;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        no_favourites = findViewById (R.id.no_favourites);
        listFavourites = findViewById(R.id.list_favourites);
        favouritesHandler_init = new FavouritesHandler (FavouritesActivity.this);

        if (favouritesHandler_init.getFavourites ().length () == 0){
            no_favourites.setVisibility (View.VISIBLE);
        }

        listFavourites.setAdapter (new FavouritesAdapter (FavouritesActivity.this, favouritesHandler_init.getFavourites ()));

        // Implement onclick listener on the list items
        listFavourites.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                // TODO: (implement a delete feature)
                String temp_bus_stop_code = ((TextView) view.findViewById(R.id.list_favourites_bus_stop_code)).getText().toString();

                Intent to_timing = new Intent(getApplicationContext(), BusTimingActivity.class)
                        .putExtra("busStopCode", temp_bus_stop_code);

                startActivity(to_timing);
            }
        });


    }
}
