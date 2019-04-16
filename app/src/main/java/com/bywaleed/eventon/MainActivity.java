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
        eList.add(new Event(R.drawable.bg1, R.drawable.profile, "HUD UNI EVENT 1", "Monday 01 April", "Some Description Here"));
        eList.add(new Event(R.drawable.bg2, R.drawable.profile, "HUD UNI EVENT 2", "Monday 02 April", "Some Description Here"));
        eList.add(new Event(R.drawable.bg3, R.drawable.profile, "HUD UNI EVENT 3", "Monday 03 April", "Some Description Here"));
        eList.add(new Event(R.drawable.bg4, R.drawable.profile, "HUD UNI EVENT 4", "Monday 04 April", "Some Description Here"));
        eList.add(new Event(R.drawable.bg5, R.drawable.profile, "HUD UNI EVENT 5", "Monday 05 April", "Some Description Here"));

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
