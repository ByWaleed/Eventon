package io.bywaleed.eventon;

import android.app.Application;
import android.util.Log;

public class BookmarkedEvents extends Application {

    private Bookmarks bookmarks;

    public BookmarkedEvents() {
        bookmarks = new Bookmarks();
    }

    public Bookmarks getEventBookmarks() {
        return bookmarks;
    }

    public void setEventBookmarks(Bookmarks bookmarks) {
        Log.d("setEventBookmarks", "setEventBookmarks: " + bookmarks.numberOfBookmarks());
        this.bookmarks = bookmarks;
    }
}