

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

public class BusTimingsAdapter extends BaseAdapter {

    JSONArray busStopList;
    Activity activity;

    public BusTimingsAdapter(Activity activity, JSONArray busStopList){
        this.busStopList = busStopList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return busStopList.length();
    }

    @Override
    public Object getItem(int position) {
        Object rtn = null;
        try{
            rtn = busStopList.get(position);
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
            convertView = LayoutInflater.from(activity).inflate(R.layout.bus_timings_list_layout, null);
        }

        try{
            item = busStopList.getJSONObject(position);
            ((TextView) convertView.findViewById(R.id.list_timing_bus_no)).setText(item.getString("no"));


            // TODO: make methods to parse the data below to be human-readable
            ((TextView) convertView.findViewById(R.id.list_timing_next_timing)).setText(item.getJSONObject("next").getString("duration_ms"));
            ((TextView) convertView.findViewById(R.id.list_timing_next_type)).setText(item.getJSONObject("next").getString("type"));
            ((TextView) convertView.findViewById(R.id.list_timing_next_load)).setText(item.getJSONObject("next").getString("load"));

            ((TextView) convertView.findViewById(R.id.list_timing_next2_timing)).setText(item.getJSONObject("next2").getString("duration_ms"));
            ((TextView) convertView.findViewById(R.id.list_timing_next2_type)).setText(item.getJSONObject("next2").getString("type"));
            ((TextView) convertView.findViewById(R.id.list_timing_next2_load)).setText(item.getJSONObject("next2").getString("load"));

            ((TextView) convertView.findViewById(R.id.list_timing_next3_timing)).setText(item.getJSONObject("next3").getString("duration_ms"));
            ((TextView) convertView.findViewById(R.id.list_timing_next3_type)).setText(item.getJSONObject("next3").getString("type"));
            ((TextView) convertView.findViewById(R.id.list_timing_next3_load)).setText(item.getJSONObject("next3").getString("load"));
        }catch (JSONException e){
            Log.e(this.getClass().getSimpleName(), e.toString());
        }

        return convertView;
    }
}
