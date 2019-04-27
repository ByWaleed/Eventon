package io.bywaleed.eventon;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class BookmarkedEvents extends Application {

    private Bookmarks bookmarks;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BOOKMARKS = "bookmarks";

    public BookmarkedEvents() {
        bookmarks = new Bookmarks();
    }

    public Bookmarks getEventBookmarks() {
        return bookmarks;
    }

    public void setEventBookmarks(Bookmarks bookmarks) {
        this.bookmarks = bookmarks;
    }
}