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

        if (loadBookmarks()) {
            initRecyclerView();
        } else {
            noBookmarkedEventsError();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (loadBookmarks()) {
            initRecyclerView();
        } else {
            noBookmarkedEventsError();
        }
    }

    private boolean loadBookmarks() {
        Bookmarks.loadFromPreferences(view.getContext());
        bookmarksList = Bookmarks.getBookmarks();

        if (bookmarksList == null) {
            bookmarksList = new ArrayList<>(0);
            return false;
        } else return bookmarksList.size() > 0;
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.bookmark_events_rv_list);
        recyclerView.setVisibility(View.VISIBLE);

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

    private void noBookmarkedEventsError() {
        view.findViewById(R.id.no_bookmarks_tv).setVisibility(View.VISIBLE);
        view.findViewById(R.id.bookmark_events_rv_list).setVisibility(View.GONE);
    }
}
