package io.bywaleed.eventon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.eViewHolder> {

    private List<Event> eData;
    private Context eContext;
    private AllEventsFragment allEventsFragment;

    public EventsAdapter(Context eContext, List<Event> eData, AllEventsFragment allEventsFragment) {
        this.eContext = eContext;
        this.eData = eData;
        this.allEventsFragment = allEventsFragment;
    }

    @NonNull
    @Override
    public eViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(eContext).inflate(R.layout.events_rv_item, viewGroup, false);
        EventsAdapter.eViewHolder eViewHolder = new eViewHolder(view);
        return eViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull eViewHolder eViewHolder, int i) {
        eViewHolder.background.setImageResource(eData.get(i).getBackground());
        eViewHolder.logo.setImageResource(eData.get(i).getLogo());
        eViewHolder.title.setText(eData.get(i).getTitle());
        eViewHolder.date.setText(eData.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return eData.size();
    }

    // VIEW HOLDER CLASS
    public class eViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        ImageView background, logo;
        TextView title, date;

        public eViewHolder(@NonNull View itemView) {
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
            allEventsFragment.onEventClicked(getAdapterPosition());
        }
    }
}
