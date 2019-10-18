package com.nabil.wherethebusah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArraySet;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nabil.wherethebusah.Handlers.FavouritesHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FavouritesActivity extends AppCompatActivity {

    // TODO: Implement favourites via sharedpreferences
    ListView listFavourites;
    List<String> converted_for_adapter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        listFavourites = findViewById(R.id.list_favourites);

        Iterator i = new FavouritesHandler (FavouritesActivity.this).getFavourites_set ().iterator ();

        while(i.hasNext ()){
            converted_for_adapter.add(i.next ().toString ());
        }

        // Sets dont work with adapter, so we add every value to a List
        listFavourites.setAdapter (new ArrayAdapter(getApplicationContext (), R.layout.favourites_list, converted_for_adapter));
    }
}
