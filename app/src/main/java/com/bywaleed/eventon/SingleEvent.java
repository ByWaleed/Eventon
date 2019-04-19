package com.bywaleed.eventon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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
        final Event selected = intent.getExtras().getParcelable("SelectedEvent");

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
                Snackbar.make(view, "Event Bookmarked ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Footer Navigation
        BottomNavigationView footerNavigation = findViewById(R.id.footer_navigation);
        footerNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_maps:
                        footerNavMaps(selected.getMapLocation());
                        return true;
                    case R.id.nav_share:
                        footerNavShare(selected);
                        return true;
                    case R.id.nav_booking:
                        footerNavBooking(selected.getBooking());
                        return true;
                }
                return false;
            }
        });
    }

    private void footerNavMaps(String uri) {
        Log.d("FooterNav", "footerNavMaps: Maps");

        Intent mapsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mapsIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapsIntent);
    }

    private void footerNavShare(Event selected) {
        Log.d("FooterNav", "footerNavMaps: Share");

        String shareText = "Don't miss out on " + selected.getTitle() + " organised by " +
                selected.getOrganisation() + " to be hosted on " + selected.getDate() + " at " +
                selected.getTime() + " in " + selected.getLocation() + ".\nHosted on Eventon App.";

        Intent shareEvent = new Intent();
        shareEvent.setAction(Intent.ACTION_SEND);
        shareEvent.putExtra(Intent.EXTRA_TEXT, shareText);
        shareEvent.setType("text/plain");
        startActivity(shareEvent);
    }

    private void footerNavBooking(String url) {
        Log.d("FooterNav", "footerNavMaps: Booking");

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
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
