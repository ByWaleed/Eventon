package com.bywaleed.eventon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EventsAdapter.OnEventListner {

    private List<event> eList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating events
        eList.add(new event(R.drawable.bg1, R.drawable.profile, "HUDDERSFIELD UNI EVENT 1", "Monday 01 April"));
        eList.add(new event(R.drawable.bg2, R.drawable.profile, "HUDDERSFIELD UNI EVENT 2", "Monday 02 April"));
        eList.add(new event(R.drawable.bg3, R.drawable.profile, "HUDDERSFIELD UNI EVENT 3", "Monday 03 April"));
        eList.add(new event(R.drawable.bg4, R.drawable.profile, "HUDDERSFIELD UNI EVENT 4", "Monday 04 April"));
        eList.add(new event(R.drawable.bg5, R.drawable.profile, "HUDDERSFIELD UNI EVENT 5", "Monday 05 April"));

        // Setting Events Recyclerview
        RecyclerView recyclerView = findViewById(R.id.events_rv_list);
        EventsAdapter eventsAdapter = new EventsAdapter(this, eList, this);

        recyclerView.setAdapter(eventsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Implements Event listener for clicking on the event
    @Override
    public void onEventClick(int position) {
        eList.get(position);
        Log.d("onEventClick", "onEventClick() returned: " + eList.get(position).title);
        // Create & pass data to intent here
    }
}
