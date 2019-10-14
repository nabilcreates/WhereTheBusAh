package com.nabil.wherethebusah.Fetchers;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;

import com.nabil.wherethebusah.Adapters.BusStopListAdapter;
import com.nabil.wherethebusah.Adapters.BusTimingsAdapter;
import com.nabil.wherethebusah.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchBusTimings extends AsyncTask<String, Void, JSONArray> {

    Activity activity;

    public FetchBusTimings(Activity activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        activity.findViewById(R.id.timing_progressbar).setVisibility(View.VISIBLE);
    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        JSONArray r = null;
        String URL = "https://arrivelah.herokuapp.com/?id=";
        OkHttpClient client = new OkHttpClient();

        System.out.println(URL + strings[0]);

        Request request = new Request.Builder()
                .url(URL + strings[0])
                .build();

        try{
            Response response = client.newCall(request).execute();
            r = new JSONObject(response.body().string()).getJSONArray("services");
        }catch(JSONException | IOException e){
            e.printStackTrace();
        }

        return r;
    }

    @Override
    protected void onPostExecute(JSONArray r) {
        activity.findViewById(R.id.timing_progressbar).setVisibility(View.INVISIBLE);
        if(r != null){
            ((ListView) activity.findViewById(R.id.bus_timing_list_view)).setAdapter(new BusTimingsAdapter(activity, r));
        }
    }
}
