package com.nabil.wherethebusah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArraySet;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nabil.wherethebusah.Adapters.FavouritesAdapter;
import com.nabil.wherethebusah.Handlers.FavouritesHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FavouritesActivity extends AppCompatActivity {

    ListView listFavourites;
    List<String> converted_for_adapter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        listFavourites = findViewById(R.id.list_favourites);

        listFavourites.setAdapter (new FavouritesAdapter (FavouritesActivity.this, new FavouritesHandler (FavouritesActivity.this).getFavourites ()));
    }
}
