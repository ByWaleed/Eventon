package io.bywaleed.eventon;

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

    private Context eContext;
    private List<Event> eData;
    private OnEventListner onEventListner;

    public EventsAdapter(Context eContext, List<Event> eData, OnEventListner onEventListner) {
        this.eContext = eContext;
        this.eData = eData;
        this.onEventListner = onEventListner;
    }

    @NonNull
    @Override
    public eventsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(eContext);
        View v = inflater.inflate(R.layout.events_rv_item, viewGroup, false);
        return new eventsViewHolder(v, onEventListner);
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

    public class eventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView background, logo;
        TextView title, date;
        OnEventListner onEventListner;

        public eventsViewHolder(@NonNull View itemView, OnEventListner onEventListner) {
            super(itemView);

            background = itemView.findViewById(R.id.event_background);
            logo = itemView.findViewById(R.id.event_logo);
            title = itemView.findViewById(R.id.event_title);
            date = itemView.findViewById(R.id.event_date);
            this.onEventListner = onEventListner;

            // Attach Event listener to Event
            itemView.setOnClickListener(this);
        }

        // Event listener
        @Override
        public void onClick(View v) {
            onEventListner.onEventClick(getAdapterPosition());
        }
    }

    public interface OnEventListner{
        void onEventClick(int position);
    }

}
