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

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.eViewHolder> {

    private Context eContext;
    private List<Event> eData;
    private OnEventListener onEventListener;

    public EventsAdapter(Context eContext, List<Event> eData, OnEventListener onEventListener) {
        this.eContext = eContext;
        this.eData = eData;
        this.onEventListener = onEventListener;
    }

    @NonNull
    @Override
    public eViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(eContext);
        View v = inflater.inflate(R.layout.events_rv_item, viewGroup, false);
        return new eViewHolder(v, onEventListener);
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

    public class eViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView background, logo;
        TextView title, date;
        OnEventListener onEventListener;

        public eViewHolder(@NonNull View itemView, OnEventListener onEventListener) {
            super(itemView);

            background = itemView.findViewById(R.id.event_background);
            logo = itemView.findViewById(R.id.event_logo);
            title = itemView.findViewById(R.id.event_title);
            date = itemView.findViewById(R.id.event_date);
            this.onEventListener = onEventListener;

            // Attach Event listener to Event
            itemView.setOnClickListener(this);
        }

        // Event listener
        @Override
        public void onClick(View v) {
            onEventListener.onEventClick(getAdapterPosition());
        }
    }

    public interface OnEventListener{
        void onEventClick(int position);
    }

}
