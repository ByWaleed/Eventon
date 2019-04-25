package io.bywaleed.eventon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AllEventsFragment extends Fragment {

    private ArrayList<Event> events = new ArrayList<>(0);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_events_fragment, container, false);

        loadEvents();

        initRecyclerView(view);

        return view;
    }

    private void loadEvents() {
        String event1_desc = "In collaboration with the department of Arabic, Islamic, and Middle Eastern Studies at the University of Leeds, the British Society for Middle Eastern Studies will hold its 2019 Annual Conference on the theme of 'Joining the Dots: Interdisciplinarity in Middle East Studies'.\n\nThe BRISMES Conference is the largest annual academic meeting of Middle East experts in the UK. We are delighted to welcome two outstanding keynote speakers: Professor Mona Harb (AUB) and Professor Salman Sayyid (University of Leeds). The event will also include over eighty panel sessions, a welcome drinks reception featuring The Nyawa Quartet musicians, a publishers' exhibition, and lunch for delegates on Tuesday 25th and Wednesday 26th June.\n\nWe will also host a conference dinner on Tuesday 25th June and will make separate tickets available for booking closer to the conference.";
        String event2_desc = "Rugby and Football Networking Event at John Smith's Stadium, Huddersfield on 16th May 2019.\n\nDelegate tickets allow you to meet and network with all other delegates and exhibitors. Club from all levels are welcome to attend with over 30 exhibitors expected to attend there is something for everyone.\n\nFree lunch and refreshments will also be provided on the day for all attendees. ";
        String event3_desc = "Web19 is a showcase event which proudly promotes and recognises excellence in design, creativity and innovation. Over three years ago, creative individuals assembled at the University of Huddersfield beginning a new chapter of their lives and careers. Collectively, they have inspired each other, motivated each other and excelled with determination and ambition to expand the now.\n\nExpanding the now, our event theme, is about making the most of what can be done now, in the present. There is no purpose in getting anywhere if, when you get there, all you do is think about getting to some other future moment. Life exists in the present, or nowhere at all. Your body is present, is your mind?\n\nThis showcase is open to all and aims to benefit both students and prospective employers from within the industry. Please join in celebrating the achievements.\n\nWe will be in the Oastler Building at the University of Huddersfield on Wednesday 1st May. The event will be running from 5pm til 7:30pm. Whilst allowing you to enjoy complimentary food and refreshments throughout the evening, you will be free to explore the exhibition and network with staff, students and industry representatives. We hope to see any many of you there.\n\nIf you would like more information please visit our website and join our mailing list which can be done via the form on our website (we won't spam you, just tell you information around the event).";
        String event4_desc = "On Saturday 13th July, Ed Vital (pianist) and David Heathcote (tenor) will perform Schumann's Dichterliebe. We're discovering more about the songs as we work but it's basically a collection of 16 songs/poems told by a heartbroken guy, reflecting on the love he once had. All the things he once adored about her - when she said 'I love you', when the birds sang, when the flowers bloomed, the songs she sang - are now painful, bitter reminders.\n\nIch grolle nicht (I bear no grudge) - I don't think so!\n\nDichterliebe, \"A Poet's Love\" (composed 1840), is the best-known song cycle of Robert Schumann (Op. 48). The texts for the 16 songs come from the Lyrisches Intermezzo of Heinrich Heine, written 1822–23 and published as part of the poet's Das Buch der Lieder.";
        String event5_desc = "On Saturday 13th July, Ed Vital (pianist) and David Heathcote (tenor) will perform Schumann's Dichterliebe. We're discovering more about the songs as we work but it's basically a collection of 16 songs/poems told by a heartbroken guy, reflecting on the love he once had. All the things he once adored about her - when she said 'I love you', when the birds sang, when the flowers bloomed, the songs she sang - are now painful, bitter reminders.\n\\nIch grolle nicht (I bear no grudge) - I don't think so!\n\nDichterliebe, \"A Poet's Love\" (composed 1840), is the best-known song cycle of Robert Schumann (Op. 48). The texts for the 16 songs come from the Lyrisches Intermezzo of Heinrich Heine, written 1822–23 and published as part of the poet's Das Buch der Lieder.";

        events.add(new Event("BRISMES Annual Conference", event1_desc, "BRISMES", "24 Jun 2019", "11:00 - 21:00", "https://www.eventbrite.co.uk/e/brismes-annual-conference-2019-interdisciplinarity-in-middle-east-studies-tickets-51455358362?aff=ebdssbdestsearch#tickets", "Leeds","https://goo.gl/maps/DU8efmVznvLBQz3BA", "£60 - 948", R.drawable.bg1, R.drawable.profile));
        events.add(new Event("Rugby Networking Day", event2_desc, "RUGBY TRADE DIRECTORY", "01 May 2019", "11:00 - 15:00", "https://www.eventbrite.co.uk/e/rugby-football-networking-day-john-smiths-stadium-16th-may-tickets-53963163274?aff=ebdssbdestsearch#tickets", "Huddersfield","https://goo.gl/maps/ZhAqxvynkYKeqPYg9", "FREE", R.drawable.bg2, R.drawable.profile));
        events.add(new Event("Web 19 Showcase", event3_desc, "UNIVERSITY OF HUDDERSFIELD", "01 APRIL 2019", "17:00 - 19:30", "https://www.eventbrite.co.uk/e/web19-showcase-tickets-55565500909?aff=ebdssbcitybrowse#tickets", "HUDDERSFIELD","https://goo.gl/maps/9Pik4Es3Xwu5VtGk8", "£12.00", R.drawable.bg3, R.drawable.profile));
        events.add(new Event("Schumann's Dichterliebe", event4_desc, "DAVID HEATHCOTE", "13 JULY 2019", "11:30 - 12:00", "https://www.eventbrite.co.uk/e/schumanns-dichterliebe-tickets-58946767368?aff=ebdssbcitybrowse#tickets", "HUDDERSFIELD","https://goo.gl/maps/Npy267ro9RUwW9C89", "FREE", R.drawable.bg4, R.drawable.profile));
        events.add(new Event("A Magical Evening of Brass", event5_desc, "ALMONDBURY METHODIST CHURCH", "22 JUNE 2019", "19:30 - 21:30", "https://www.eventbrite.co.uk/e/a-magical-evening-of-brass-tickets-60022334418?aff=ebdssbcitybrowse#tickets", "HUDDERSFIELD","https://goo.gl/maps/ggka2CMUKiKPtjmPA", "£8.97", R.drawable.bg5, R.drawable.profile));
    }

    public void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.events_rv_list);

        EventsAdapter adapter = new EventsAdapter(getContext(), events, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void onEventClicked(int position) {
        Event selected = events.get(position);

        Intent intent = new Intent(getContext(), EventDetailsActivity.class);
        intent.putExtra("SelectedEvent", selected);
        startActivity(intent);
    }
}
