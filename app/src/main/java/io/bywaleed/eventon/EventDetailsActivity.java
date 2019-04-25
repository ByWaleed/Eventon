package io.bywaleed.eventon;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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

public class EventDetailsActivity extends AppCompatActivity {

    public  Bookmarks bookmarks;

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

        initBookmark(selected);

        initFooterNav(selected);

        setupButtons(selected);
    }

    private void initFooterNav(final Event selected) {
        // Footer Navigation & Listener
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

    private void initBookmark(final Event selected) {
        // Bookmark Button
        final FloatingActionButton bookmark_btn = (FloatingActionButton) findViewById(R.id.bookmark_btn);

        // Load previously saved bookmarks (global)
        bookmarks = ((BookmarkedEvents) getApplicationContext()).getEventBookmarks();

        // Change icon if event is already booked
        if (bookmarks.bookmarked(selected) != -1){
            bookmark_btn.setImageResource(R.drawable.ic_bookmark_remove);
        }

        // Bookmark Button Listener
        bookmark_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String notification;
                bookmark_btn.setImageDrawable(null);
                Integer eventArrayPosition = bookmarks.bookmarked(selected);

                if (eventArrayPosition != -1){
                    bookmarks.removeBookmark(eventArrayPosition);
                    notification = "Event removed from your bookmarks.";
                    bookmark_btn.setImageResource(R.drawable.ic_bookmark);
                } else {
                    bookmarks.addBookmark(selected);
                    notification = "Event added to your bookmarks.";
                    bookmark_btn.setImageResource(R.drawable.ic_bookmark_remove);
                }

                ((BookmarkedEvents) getApplicationContext()).setEventBookmarks(bookmarks);

                Snackbar.make(view, notification, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void footerNavMaps(String uri) {
        Intent mapsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mapsIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapsIntent);
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
                Intent mapsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(selected.getMapLocation()));
                mapsIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapsIntent);
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
