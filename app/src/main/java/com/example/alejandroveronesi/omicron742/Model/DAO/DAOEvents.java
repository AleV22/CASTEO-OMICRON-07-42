package com.example.alejandroveronesi.omicron742.Model.DAO;


import android.preference.PreferenceManager;

import com.example.alejandroveronesi.omicron742.Model.POJO.Event;
import com.example.alejandroveronesi.omicron742.util.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAOEvents {

    private List<Event> eventList = new ArrayList<>();

    public void obtainEvents(final ResultListener<List<Event>> listener) {

        //Firebase database creation
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference event = firebaseDatabase.getReferenceFromUrl("https://fir-omicron742.firebaseio.com/" + "eventList");

        event.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Event event = childSnapshot.getValue(Event.class);
                    eventList.add(event);
                }
                listener.finish(eventList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
