package com.bywaleed.eventon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BookmarksActivity extends AppCompatActivity {

    public static Bookmarks bookmarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
    }
}
