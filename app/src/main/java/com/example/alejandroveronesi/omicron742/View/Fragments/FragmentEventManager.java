package com.example.alejandroveronesi.omicron742.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alejandroveronesi.omicron742.Model.POJO.Event;
import com.example.alejandroveronesi.omicron742.R;
import com.example.alejandroveronesi.omicron742.View.Activities.MainActivity;
import com.example.alejandroveronesi.omicron742.View.Adapters.EventsAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentEventManager extends Fragment {

    private List<Event> eventsList;
    private EventsAdapter eventsAdapter;
    private NotifyActivities notifyActivities;
    private FloatingActionButton newEventButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_manager, container, false);

        eventsList = new ArrayList<>();
        eventsAdapter = new EventsAdapter(eventsList, getContext());

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(eventsAdapter);

        eventsList.add(new Event("Llegada deposito A",10,"Seguridad 1","41.40338, 2.17403"));
        eventsList.add(new Event("Llegada a casa",5,"Seguridad 1","41.50338, 2.27403"));
        eventsList.add(new Event("Llegada al trabajo",12,"Seguridad 1","41.60338, 2.37403"));
        eventsList.add(new Event("Llegada a la quinta",20,"Seguridad 1","41.70338, 2.47403"));
        eventsList.add(new Event("Llegada al bunker",7,"Seguridad 1","41.80338, 2.57403"));
        eventsList.add(new Event("Llegada al banco",13,"Seguridad 1","41.90338, 2.67403"));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer position = recyclerView.getChildAdapterPosition(v);
                Event event = eventsAdapter.getItem(position);
                notifyActivities.receiveMessaje(event);
            }
        };

        eventsAdapter.setMyListener(listener);

        newEventButton = (FloatingActionButton) view.findViewById(R.id.newEvent);
        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyActivities.newEventPage();
            }
        });

        return view;
    }

    public interface NotifyActivities {
        public void receiveMessaje(Event event);
        public void newEventPage();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.notifyActivities = (NotifyActivities) context;
    }


}
