package com.example.alejandroveronesi.omicron742.Model.DAO;


import android.content.Context;
import android.preference.PreferenceManager;

import com.example.alejandroveronesi.omicron742.Model.POJO.Event;
import com.example.alejandroveronesi.omicron742.util.ResultListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAOEvents {

    private List<Event> eventList = new ArrayList<>();
    public FirebaseDatabase database;

    public void obtainEventsFromDatabase(final ResultListener<List<Event>> listener) {
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReferenceFromUrl("https://fir-omicron742.firebaseio.com/"+ FirebaseAuth.getInstance().getCurrentUser().getUid() +  "/eventList");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot:dataSnapshot.getChildren()) {
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


    public void addEventToDatabase(Event event){

        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("eventList").child(event.getEventName()).setValue(event);
    }

}


