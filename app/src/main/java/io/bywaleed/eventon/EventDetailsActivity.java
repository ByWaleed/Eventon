package io.bywaleed.eventon;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class EventDetailsActivity extends AppCompatActivity {

    Boolean bookmarked = false;
    EventDetailsActivity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final Event selected = intent.getExtras().getParcelable("SelectedEvent");

        // Toolbar
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        displayEventDetails(selected);

        // Animations removed due to slowing down the whole app

        if (initBookmark(selected)) {

            initFooterNav(selected);

            setupButtons(selected);
        }
    }

    private void initFooterNav(final Event selected) {
        // Footer Navigation & Listener
        BottomNavigationView footerNavigation = findViewById(R.id.footer_navigation);
        footerNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_maps:
                        footerNavMaps(selected);
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

    private boolean initBookmark(final Event selected) {
        // Bookmark Button
        final FloatingActionButton bookmark_btn = findViewById(R.id.bookmark_btn);

        // Load previously saved bookmarks (global)
        Bookmarks.loadFromPreferences(context);

        // Change icon if event is already booked
        if (Bookmarks.bookmarked(selected) != -1) {
            bookmark_btn.setImageResource(R.drawable.ic_bookmark_remove);
            bookmarked = true;
        }

        // Bookmark Button Listener
        bookmark_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bookmark_btn.setImageDrawable(null);
                String notification;

                if (bookmarked) {
                    bookmarked = false;
                    Bookmarks.removeBookmark(context, selected);
                    notification = "Event removed from your bookmarks.";
                    bookmark_btn.setImageResource(R.drawable.ic_bookmark);
                } else {
                    bookmarked = true;
                    Bookmarks.addBookmark(context, selected);
                    notification = "Event added to your bookmarks.";
                    bookmark_btn.setImageResource(R.drawable.ic_bookmark_remove);
                }

                Snackbar.make(view, notification, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return true;
    }

    private void footerNavMaps(final Event selected) {

        String[] locations = selected.getMapLocation().split(",");

        double latitude = Double.valueOf(locations[0]);
        double longitude = Double.valueOf(locations[1]);

        String label = selected.getTitle();
        String uriBegin = "geo:" + latitude + "," + longitude;
        String query = latitude + "," + longitude + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(intent);

        /*Intent mapsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mapsIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapsIntent);*/
    }

    private void footerNavShare(Event selected) {
        String shareText = "Don't miss out on " + selected.getTitle() + " organised by " +
                selected.getOrganisation() + " to be hosted on " + selected.getDate() + " at " +
                selected.getTime() + " in " + selected.getLocation() + ".\n Shared by Eventon App.";

        Intent shareEvent = new Intent();
        shareEvent.setAction(Intent.ACTION_SEND);
        shareEvent.putExtra(Intent.EXTRA_TEXT, shareText);
        shareEvent.setType("text/plain");
        startActivity(shareEvent);
    }

    private void footerNavBooking(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void displayEventDetails(Event event){
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

    private void setupButtons(final Event selected) {
        /*
         * Links can customized be made to take the use to certain page or section of a page.
         * Recommended to create attributes for each events e.g. specific urls
         * */

        // Event Date
        Button event_date = (Button) findViewById(R.id.event_date);
        event_date.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(selected.getBooking());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        // Event Time
        Button event_time = (Button) findViewById(R.id.event_time);
        event_time.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(selected.getBooking());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        // Event Location
        Button event_location = (Button) findViewById(R.id.event_location);
        event_location.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                footerNavMaps(selected);
            }
        });

        // Event Price
        Button event_price = (Button) findViewById(R.id.event_price);
        event_price.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(selected.getBooking());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
}
