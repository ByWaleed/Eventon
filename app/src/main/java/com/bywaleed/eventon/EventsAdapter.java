package com.bywaleed.eventon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.eventsViewHolder> {

    Context eContext;
    List<event> eData;

    public EventsAdapter(Context eContext, List<event> eData) {
        this.eContext = eContext;
        this.eData = eData;
    }

    @NonNull
    @Override
    public eventsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(eContext);
        View v = inflater.inflate(R.layout.event_item, viewGroup, false);
        return new eventsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull eventsViewHolder eventsViewHolder, int i) {
        eventsViewHolder.background.setImageResource(eData.get(i).getBackground());
        eventsViewHolder.logo.setImageResource(eData.get(i).getLogo());
        eventsViewHolder.title.setText(eData.get(i).getTitle());
        eventsViewHolder.date.setText(eData.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return eData.size();
    }

    public class eventsViewHolder extends RecyclerView.ViewHolder{

        ImageView background, logo;
        TextView title, date;

        public eventsViewHolder(@NonNull View itemView) {
            super(itemView);

            background = itemView.findViewById(R.id.event_background);
            logo = itemView.findViewById(R.id.event_logo);
            title = itemView.findViewById(R.id.event_title);
            date = itemView.findViewById(R.id.event_date);
        }
    }

}
