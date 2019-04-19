package com.bywaleed.eventon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Selected Event
        Intent intent = getIntent();
        Event selected = intent.getExtras().getParcelable("SelectedEvent");

        // Toolbar
        setContentView(R.layout.activity_single_event);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        displayEventDetails(selected);

        // Action bar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void displayEventDetails(Event event){
        // Title
        getSupportActionBar().setTitle(event.getTitle());

        // Title
        TextView bodyTitle = findViewById(R.id.event_body_title);
        bodyTitle.setText(event.getTitle());

        // Organisation Name
        TextView organisation = findViewById(R.id.event_orgasination);
        organisation.setText(event.getOrganisation());

        // Background Image
        ImageView background = findViewById(R.id.event_background);
        background.setImageResource(event.background);

        // Date
        Button date = findViewById(R.id.event_date);
        date.setText(event.getDate());

        // Time
        Button time = findViewById(R.id.event_time);
        time.setText(event.getTime());

        // Location
        Button location = findViewById(R.id.event_location);
        location.setText(event.getLocation());

        // Price
        Button price = findViewById(R.id.event_price);
        price.setText(event.price);

        // Description
        TextView description = findViewById(R.id.event_description);
        description.setText(event.getDescription());
    }
}
