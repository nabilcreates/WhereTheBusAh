package com.nabil.wherethebusah.Handlers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;

import java.util.Set;

public class FavouritesHandler {

    Activity activity;
    Set<String> favourites_set;
    SharedPreferences sp;



    public FavouritesHandler(Activity _activity){
        this.activity = _activity;
        this.sp = activity.getSharedPreferences("com.nabil.wherethebusah", Context.MODE_PRIVATE);
        this.favourites_set = sp.getStringSet("favourites_set", new ArraySet<String>());
    }

    public void addBusStop(String bus_stop_code) {
        Set<String> temp = favourites_set;
        System.out.println("BEFORE_TEMP:" + temp);

        temp.add(bus_stop_code);
        System.out.println("AFTER_TEMP:" + temp);

        writeToSP(temp);
        System.out.println(favourites_set);
    }

    public void writeToSP(Set<String> data_to_put){
        sp.edit().putStringSet("favourites_set", data_to_put).apply();
    }

    public void clearSP(){
        sp.edit ().clear ().apply ();
    }

    public Set<String> getFavourites_set() {
        return favourites_set;
    }
}
