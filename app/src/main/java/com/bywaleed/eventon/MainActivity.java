package com.bywaleed.eventon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EventsAdapter.OnEventListner {

    private List<Event> eList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* External API could be used here to fetch the data. */

        // Creating events
        String fake_description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        eList.add(new Event("HUD UNI EVENT 1", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "hud.ac.uk", "HUDDESFIELD TOWN","123456,123456", "£12.00", R.drawable.bg1, R.drawable.profile));
        eList.add(new Event("HUD UNI EVENT 2", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "hud.ac.uk", "HUDDESFIELD TOWN","123456,123456", "£12.00", R.drawable.bg2, R.drawable.profile));
        eList.add(new Event("HUD UNI EVENT 3", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "hud.ac.uk", "HUDDESFIELD TOWN","123456,123456", "£12.00", R.drawable.bg3, R.drawable.profile));
        eList.add(new Event("HUD UNI EVENT 4", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "hud.ac.uk", "HUDDESFIELD TOWN","123456,123456", "£12.00", R.drawable.bg4, R.drawable.profile));
        eList.add(new Event("HUD UNI EVENT 5", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "hud.ac.uk", "HUDDESFIELD TOWN","123456,123456", "£12.00", R.drawable.bg5, R.drawable.profile));

        // Setting Events Recyclerview
        RecyclerView recyclerView = findViewById(R.id.events_rv_list);
        EventsAdapter eventsAdapter = new EventsAdapter(this, eList, this);

        recyclerView.setAdapter(eventsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Implements Event listener for clicking on a Event
    @Override
    public void onEventClick(int position) {
        Event selected = eList.get(position);
        Log.d("onEventClick", "onEventClick() returned: " + selected.title + " - " + selected.description);

        // Create Intent, pass data and show activity
        Intent intent = new Intent(this, SingleEvent.class);
        intent.putExtra("SelectedEvent", selected);
        startActivity(intent);
    }
}
