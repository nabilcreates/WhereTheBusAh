package com.nabil.wherethebusah.Fetchers;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.nabil.wherethebusah.Adapters.BusStopListAdapter;
import com.nabil.wherethebusah.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchBusStopList extends AsyncTask<Void, Void, JSONArray> {
    String API_URL = "https://raw.githubusercontent.com/nabilcreates/BusService/master/data/object_name_code.json";
    Activity activity;
    JSONArray results;

    public FetchBusStopList(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        // VISIBILITY:
        activity.findViewById(R.id.parent_layout).setVisibility(View.INVISIBLE);
        activity.findViewById(R.id.bus_stop_list_progressbar).setVisibility(View.VISIBLE);
    }

    @Override
    protected JSONArray doInBackground(Void... voids) {
        JSONArray r = null;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try {
            Response response = client.newCall(request).execute();
            r = new JSONArray(response.body().string());
        } catch (JSONException | IOException e) {
            Log.e(this.getClass().getSimpleName(), e.toString());
        }

        return r;
    }

    @Override
    protected void onPostExecute(JSONArray r) {
        if (r != null) {

            // VISIBILITY:
            activity.findViewById(R.id.parent_layout).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.bus_stop_list_progressbar).setVisibility(View.INVISIBLE);

            setAdapter(r);
            results = r;
        }
    }

    public void setAdapter(JSONArray results) {
        ((ListView) activity.findViewById(R.id.bus_stop_list_view)).setAdapter(new BusStopListAdapter(activity, results));
    }

    public JSONArray getResults() {
        return results;
    }
}
