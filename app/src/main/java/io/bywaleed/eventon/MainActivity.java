package io.bywaleed.eventon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, EventsAdapter.OnEventListner {

    private List<Event> eList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        createEvents();

        setupEventsRecycler();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void createEvents() {
        String fake_description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        eList.add(new Event("HUD UNI EVENT 1", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "http://hud.ac.uk", "HUDDESFIELD TOWN","https://goo.gl/maps/9Pik4Es3Xwu5VtGk8", "£12.00", R.drawable.bg1, R.drawable.profile));
        eList.add(new Event("HUD UNI EVENT 2", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "http://hud.ac.uk", "HUDDESFIELD TOWN","https://goo.gl/maps/9Pik4Es3Xwu5VtGk8", "£12.00", R.drawable.bg2, R.drawable.profile));
        eList.add(new Event("HUD UNI EVENT 3", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "http://hud.ac.uk", "HUDDESFIELD TOWN","https://goo.gl/maps/9Pik4Es3Xwu5VtGk8", "£12.00", R.drawable.bg3, R.drawable.profile));
        eList.add(new Event("HUD UNI EVENT 4", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "http://hud.ac.uk", "HUDDESFIELD TOWN","https://goo.gl/maps/9Pik4Es3Xwu5VtGk8", "£12.00", R.drawable.bg4, R.drawable.profile));
        eList.add(new Event("HUD UNI EVENT 5", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "http://hud.ac.uk", "HUDDESFIELD TOWN","https://goo.gl/maps/9Pik4Es3Xwu5VtGk8", "£12.00", R.drawable.bg5, R.drawable.profile));
    }

    public void setupEventsRecycler() {
        RecyclerView recyclerView = findViewById(R.id.events_rv_list);
        EventsAdapter eventsAdapter = new EventsAdapter(this, eList, this);

        recyclerView.setAdapter(eventsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Events Click Listener
    @Override
    public void onEventClick(int position) {
        Event selected = eList.get(position);

        Intent intent = new Intent(this, EventDetailsActivity.class);
        intent.putExtra("SelectedEvent", selected);
        startActivity(intent);
    }
}
