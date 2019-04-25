package io.bywaleed.eventon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BookmarksFragment extends Fragment{

    private static final String TAG = "BookmarksFragment";
    private ArrayList<Event> bookmarksList = new ArrayList<>(0);

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_bookmarks, container, false);

        loadBookmarks();

        initRecyclerView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadBookmarks();
        initRecyclerView();
    }

    private void loadBookmarks() {
        bookmarksList.clear();
        bookmarksList.addAll(((BookmarkedEvents) getActivity().getApplicationContext()).getEventBookmarks().getBookmarks());
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.bookmark_events_rv_list);

        BookmarksAdapter adapter = new BookmarksAdapter(getContext(), bookmarksList, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    public void onBookmarkClicked(int position) {
        Event selected = bookmarksList.get(position);

        Intent intent = new Intent(getContext(), EventDetailsActivity.class);
        intent.putExtra("SelectedEvent", selected);
        startActivity(intent);
    }
}
