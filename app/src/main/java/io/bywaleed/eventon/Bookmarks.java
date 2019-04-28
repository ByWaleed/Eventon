package io.bywaleed.eventon;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Bookmarks {

    private static ArrayList<Event> bookmarks = new ArrayList<>(0);

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BOOKMARKS = "bookmarks";

    public Bookmarks() {
        if (bookmarks == null) {
            bookmarks = new ArrayList<Event>(0);
        }
    }

    public static ArrayList<Event> getBookmarks() {
        return bookmarks;
    }

    public static Integer bookmarked(Event event){
        Log.d("bookmarked", "title: " + event.getTitle());
        Log.d("bookmarked", "size: " + bookmarks.size());
        if (bookmarks.size() > 0) {
            Integer position = 0;
            for (Event current : bookmarks) {
                if (current.getTitle().equals(event.getTitle())) {
                    return position;
                }
                position++;
            }
        }
        return -1;
    }

    public static void addBookmark(Context context, Event event) {
        Bookmarks.bookmarks.add(event);
        saveToPreferences(context);
        Log.d("addBookmark", "addBookmark: " + bookmarks.size());
    }

    public static void removeBookmark(Context context, Event selected) {
        Event toRemove = null;
        for (Event event : bookmarks) {
            Log.d("saveToPreferences", "Event: " + event.getTitle());
            if (event.getTitle().equals(selected.getTitle())) {
                toRemove = event;
                Log.d("saveToPreferences", "Found: " + event.getTitle());
            }
        }
        if (toRemove != null) {
            bookmarks.remove(toRemove);
        }
        Log.d("saveToPreferences", "saveToPreferences: " + bookmarks.size());
        saveToPreferences(context);
    }

    private static void saveToPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String jsonBookmarks = gson.toJson(bookmarks);

        editor.putString(BOOKMARKS, jsonBookmarks);
        editor.apply();
    }

    public static void loadFromPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String savedBookmarks = sharedPreferences.getString(BOOKMARKS, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Event>>() {}.getType();

        bookmarks = gson.fromJson(savedBookmarks, type);
    }
}
