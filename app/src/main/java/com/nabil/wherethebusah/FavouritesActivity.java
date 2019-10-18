package com.nabil.wherethebusah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArraySet;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FavouritesActivity extends AppCompatActivity {

    // TODO: Implement favourites via sharedpreferences
    ListView listFavourites;
    SharedPreferences sp;
    List<String> converted = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        sp = getSharedPreferences("com.nabil.wherethebusah", MODE_PRIVATE);
        listFavourites = findViewById(R.id.list_favourites);

        Iterator i = sp.getStringSet("favouritesSet", new HashSet<String>()).iterator();

        while(i.hasNext()){
            converted.add(i.next().toString());
        }

        System.out.println(converted);

        // listFavourites.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.favourites_list,));


    }
}
