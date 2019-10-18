package com.nabil.wherethebusah.Handlers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;

import java.util.HashSet;
import java.util.Set;

public class FavouritesHandler {

    Activity activity;
    SharedPreferences sp;



    public FavouritesHandler(Activity _activity){
        this.activity = _activity;
        this.sp = activity.getSharedPreferences("com.nabil.wherethebusah", Context.MODE_PRIVATE);
    }

    public void addBusStop(String bus_stop_code) {
        Set<String> get_current_set = sp.getStringSet("favourites_set", new ArraySet<String>());

        // Illegal to "add item" to shared preferences... read this: https://stackoverflow.com/a/21401062 , so we create a temp which creates a new hashset which contains the current_set values and add to it
        Set<String> temp = new HashSet<> (get_current_set);
        System.out.println("BEFORE_TEMP:" + temp);

        temp.add(bus_stop_code);
        System.out.println("AFTER_TEMP:" + temp);

        writeToSP (temp);
    }

    public void writeToSP(Set<String> data_to_put){
        sp.edit ().putStringSet ("favourites_set", data_to_put).apply ();
    }

    public void clearSP(){
        sp.edit ().clear ().apply ();
    }

    public Set<String> getFavourites_set() {
        return sp.getStringSet("favourites_set", new ArraySet<String>());
    }
}
