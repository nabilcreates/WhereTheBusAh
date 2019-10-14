package com.nabil.wherethebusah.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nabil.wherethebusah.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BusStopListAdapter extends BaseAdapter {

    JSONArray busTimings;
    Activity activity;

    public BusStopListAdapter(Activity activity, JSONArray busTimings){
        this.busTimings = busTimings;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return busTimings.length();
    }

    @Override
    public Object getItem(int position) {
        Object rtn = null;
        try{
            rtn = busTimings.get(position);
        }catch (JSONException e){
            Log.e(this.getClass().getSimpleName(), e.toString());
        }

        return rtn;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JSONObject item;

        if(convertView == null){
            convertView = LayoutInflater.from(activity).inflate(R.layout.bus_stop_list_layout, null);
        }

        try{
            item = busTimings.getJSONObject(position);
            ((TextView) convertView.findViewById(R.id.list_bus_stop_name)).setText(item.getString("bus_stop_name"));
            ((TextView) convertView.findViewById(R.id.list_bus_stop_code)).setText(item.getString("bus_stop_code"));
        }catch (JSONException e){
            Log.e(this.getClass().getSimpleName(), e.toString());
        }

        return convertView;
    }
}
