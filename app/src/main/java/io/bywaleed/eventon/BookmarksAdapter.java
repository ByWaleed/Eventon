package io.bywaleed.eventon;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookmarksAdapter extends RecyclerView.Adapter<BookmarksAdapter.bViewHolder>{

    private ArrayList<Event> eData = new ArrayList<>(0);
    private Context eContext;
    private BookmarksFragment bookmarksFragment;

    public BookmarksAdapter(Context eContext, ArrayList<Event> eData, BookmarksFragment bookmarksFragment) {
        this.eData = eData;
        this.eContext = eContext;
        this.bookmarksFragment = bookmarksFragment;
    }

    @NonNull
    @Override
    public bViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(eContext).inflate(R.layout.events_rv_item, viewGroup, false);
        bViewHolder bViewHolder = new bViewHolder(view);
        return bViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull bViewHolder bViewHolder, int i) {
        bViewHolder.background.setImageResource(eData.get(i).getBackground());
        bViewHolder.logo.setImageResource(eData.get(i).getLogo());
        bViewHolder.title.setText(eData.get(i).getTitle());
        bViewHolder.date.setText(eData.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return eData.size();
    }

    // VIEW HOLDER CLASS
    public class bViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        ImageView background, logo;
        TextView title, date;

        public bViewHolder(@NonNull View itemView) {
            super(itemView);

            background = itemView.findViewById(R.id.event_background);
            logo = itemView.findViewById(R.id.event_logo);
            title = itemView.findViewById(R.id.event_title);
            date = itemView.findViewById(R.id.event_date);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("bookmarkClicked", "onClick Adapter Position: " + getAdapterPosition());
            bookmarksFragment.onBookmarkClicked(getAdapterPosition());
        }
    }

}