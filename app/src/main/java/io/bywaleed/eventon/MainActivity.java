package io.bywaleed.eventon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Default Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AllEventsFragment()).commit();
            navigationView.setCheckedItem(R.id.drawer_all_events);
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_all_events) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AllEventsFragment()).commit();

        } else if (id == R.id.drawer_bookmarks) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new BookmarksFragment()).commit();

        } else if (id == R.id.drawer_about_us) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AboutUsFragment()).commit();

        } else if (id == R.id.drawer_share) {
            appShare();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void appShare() {
        String shareText = getResources().getString(R.string.about_app);
        shareText += " Learn more by visiting bywaleed.github.io";

        Intent shareEvent = new Intent();
        shareEvent.setAction(Intent.ACTION_SEND);
        shareEvent.putExtra(Intent.EXTRA_TEXT, shareText);
        shareEvent.setType("text/plain");
        startActivity(shareEvent);
    }
}
