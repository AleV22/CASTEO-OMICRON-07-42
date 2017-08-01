package com.example.alejandroveronesi.omicron742.View.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alejandroveronesi.omicron742.Model.POJO.Event;
import com.example.alejandroveronesi.omicron742.R;
import com.example.alejandroveronesi.omicron742.View.Fragments.FragmentEvent;
import com.example.alejandroveronesi.omicron742.View.Fragments.FragmentEventManager;
import com.example.alejandroveronesi.omicron742.View.Fragments.FragmentStartEvent;

public class MainActivity extends AppCompatActivity implements FragmentEventManager.NotifyActivities {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentEventManager fragmentEventManager = new FragmentEventManager();
        traveler(fragmentEventManager);
//        FragmentStartEvent fragmentStartEvent = new FragmentStartEvent();
//        traveler(fragmentStartEvent);

    }

    //standard replace transaction method
    public void traveler(Fragment fragmentName) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentName).commit();
    }

    @Override
    public void receiveMessaje(Event event) {
        Bundle bundle = new Bundle();
        bundle.putString(FragmentStartEvent.EVENT_NAME, event.getEventName());
        bundle.putInt(FragmentStartEvent.EVENT_TIME, event.getEventTime());
        bundle.putString(FragmentStartEvent.EVENT_EMAIL, event.getContact());

        FragmentStartEvent fragmentStartEvent = new FragmentStartEvent();
        fragmentStartEvent.setArguments(bundle);
        pasarFragment(fragmentStartEvent,true);

    }

    @Override
    public void newEventPage() {
        FragmentEvent fragmentEvent = new FragmentEvent();
        traveler(fragmentEvent);
    }


    public void pasarFragment(Fragment fragment, Boolean back){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        if (back){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

}
