package com.nabil.wherethebusah.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nabil.wherethebusah.FavouritesActivity;
import com.nabil.wherethebusah.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class FavouritesAdapter extends BaseAdapter {

    JSONArray data;
    Activity activity;

    public FavouritesAdapter(Activity _activity, JSONArray _data){
        this.data = _data;
        this.activity = _activity;
    }

    @Override
    public int getCount () {
        return data.length ();
    }

    @Override
    public Object getItem (int position) {
        Object rtnval = null;

        try {
            rtnval = data.get (position);
        } catch (JSONException e) {
            e.printStackTrace ();
        }

        return rtnval;
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        JSONObject item = new JSONObject ();
        convertView = activity.getLayoutInflater ().inflate (R.layout.favourites_list, null);

        try {
            item = data.getJSONObject (position);
            ((TextView) convertView.findViewById (R.id.list_favourites_bus_stop_name)).setText (item.getString ("bus_stop_name"));
            ((TextView) convertView.findViewById (R.id.list_favourites_bus_stop_code)).setText (item.getString ("bus_stop_code"));
        } catch (JSONException e) {
            e.printStackTrace ();
        }

        return convertView;
    }
}
