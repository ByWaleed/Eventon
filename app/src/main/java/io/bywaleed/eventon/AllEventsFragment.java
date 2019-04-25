package io.bywaleed.eventon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AllEventsFragment extends Fragment {

    private ArrayList<Event> events = new ArrayList<>(0);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_events_fragment, container, false);

        loadEvents();

        initRecyclerView(view);

        return view;
    }

    private void loadEvents() {
        String fake_description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        events.add(new Event("HUD UNI EVENT 1", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "http://hud.ac.uk", "HUDDESFIELD TOWN","https://goo.gl/maps/9Pik4Es3Xwu5VtGk8", "£12.00", R.drawable.bg1, R.drawable.profile));
        events.add(new Event("HUD UNI EVENT 2", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "http://hud.ac.uk", "HUDDESFIELD TOWN","https://goo.gl/maps/9Pik4Es3Xwu5VtGk8", "£12.00", R.drawable.bg2, R.drawable.profile));
        events.add(new Event("HUD UNI EVENT 3", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "http://hud.ac.uk", "HUDDESFIELD TOWN","https://goo.gl/maps/9Pik4Es3Xwu5VtGk8", "£12.00", R.drawable.bg3, R.drawable.profile));
        events.add(new Event("HUD UNI EVENT 4", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "http://hud.ac.uk", "HUDDESFIELD TOWN","https://goo.gl/maps/9Pik4Es3Xwu5VtGk8", "£12.00", R.drawable.bg4, R.drawable.profile));
        events.add(new Event("HUD UNI EVENT 5", fake_description, "HUDDESFIELD UNIVESITY", "01 APRIL 2019", "17:00 - 19:00", "http://hud.ac.uk", "HUDDESFIELD TOWN","https://goo.gl/maps/9Pik4Es3Xwu5VtGk8", "£12.00", R.drawable.bg5, R.drawable.profile));
    }

    public void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.events_rv_list);

        EventsAdapter adapter = new EventsAdapter(getContext(), events, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void onEventClicked(int position) {
        Event selected = events.get(position);

        Intent intent = new Intent(getContext(), EventDetailsActivity.class);
        intent.putExtra("SelectedEvent", selected);
        startActivity(intent);
    }
}
