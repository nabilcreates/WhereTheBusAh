package com.nabil.wherethebusah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.nabil.wherethebusah.Fetchers.FetchBusStopList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    ListView busStopList;
    FetchBusStopList fetchBusStopList_init;
    Button searchButton;
    EditText searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchBusStopList_init = new FetchBusStopList(MainActivity.this);
        searchButton = findViewById(R.id.search_button);
        searchInput = findViewById(R.id.search_input);
        busStopList = findViewById(R.id.bus_stop_list_view);

        fetchBusStopList_init.execute();

        // ListView listener
        busStopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String temp_bus_stop_code = ((TextView) view.findViewById(R.id.list_bus_stop_code)).getText().toString();
                String temp_bus_stop_name = ((TextView) view.findViewById(R.id.list_bus_stop_name)).getText().toString();

                Intent to_timing = new Intent(getApplicationContext(), BusTimingActivity.class)
                        .putExtra("busStopCode", temp_bus_stop_code);

                startActivity(to_timing);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArray bus_stop_data = fetchBusStopList_init.getResults();
                String search_string = searchInput.getText().toString();
                System.out.println(filterFunction(search_string, bus_stop_data));
                fetchBusStopList_init.setAdapter(filterFunction(search_string, bus_stop_data));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // TODO: Implement Settings page!

        return true;
    }

    // setadapter with the data
    public JSONArray filterFunction(String searchQuery, JSONArray bus_stop_data) {
        JSONArray rtnval = new JSONArray();

        // Temp jsonarray to put data to
        JSONArray temp = new JSONArray();

        // Try needed for JSONException (or else the compiler or IDE will throw an error of uncaught errors)
        try {

            // Loop through for every bus stop data
            for (int i = 0; i < bus_stop_data.length(); i++) {

                // item is the current jsonobject looped through
                JSONObject item = bus_stop_data.getJSONObject(i);

                // Get the search_loop_string So the results or the input will be like e.g: BLK 988 27888 (to enable both search by string and bus stop code)
                String search_loop_string = item.getString("bus_stop_name") + " " + item.getString("bus_stop_code");

                // If search query is met
                if (search_loop_string.toUpperCase().contains(searchQuery.toUpperCase())) {

                    // Data is found!

                    // Put the item in the temporary jsonarray
                    temp.put(item);
                }
            }

            // Make sure the return value is set to the temp array
            rtnval = temp;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // return the jsonarray
        return rtnval;
    }

}
