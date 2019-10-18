package com.nabil.wherethebusah.Handlers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class FavouritesHandler {

    Activity activity;
    SharedPreferences sp;


    public FavouritesHandler (Activity _activity) {
        this.activity = _activity;
        this.sp = activity.getSharedPreferences ("com.nabil.wherethebusah", Context.MODE_PRIVATE);
    }

    // Implement to take in both name and code
    public void addBusStop (String bus_stop_name, String bus_stop_code) {
        JSONArray get_current_favourites = new JSONArray ();

        try {
            get_current_favourites = new JSONArray (sp.getString ("favourites", ""));
        } catch (JSONException e) {
            e.printStackTrace ();
        }

        // Illegal to "add item" to shared preferences... read this: https://stackoverflow.com/a/21401062 , so we create a temp which creates a new hashset which contains the current_set values and add to it
        JSONArray temp = new JSONArray ();
        JSONObject bus_stop_object = new JSONObject ();

        // This is the truthy to decide if the object can be pushed or not, the truthy depends on the if-conditions below
        Boolean canPush = false;

        try {
            temp = new JSONArray (get_current_favourites.toString ());

            // Try to loop
            for(int i = 0; i < temp.length (); i++){

                // Check for duplicates
                String condition = temp.getJSONObject (i).getString ("bus_stop_code");
                if(!condition.equals (bus_stop_code)){
                    // Truthy is true
                    canPush = true;
                }
            }

            // Check if its empty
            if(temp.length () == 0){

                // Truthy is true
                canPush = true;
            }

            if(canPush){
                bus_stop_object = new JSONObject ()
                        .put ("bus_stop_name", bus_stop_name)
                        .put ("bus_stop_code", bus_stop_code);

                temp.put (bus_stop_object);
                writeToSP (temp);
            }

        } catch (JSONException e) {
            e.printStackTrace ();
        }
    }

    public void writeToSP (JSONArray data_to_put) {
        sp.edit ().putString ("favourites", data_to_put.toString ()).apply ();
    }

    public void clearSP () {
        sp.edit ().clear ().apply ();
    }

    public JSONArray getFavourites () {
        JSONArray rtnval = new JSONArray ();
        try{
            rtnval = new JSONArray (sp.getString ("favourites", ""));
        }catch (JSONException e){
            e.printStackTrace ();
        }

        return rtnval;
    }
}
