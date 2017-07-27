package com.example.alejandroveronesi.omicron742.View.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alejandroveronesi.omicron742.Model.POJO.Event;
import com.example.alejandroveronesi.omicron742.R;
import com.example.alejandroveronesi.omicron742.View.Fragments.FragmentEventManager;

public class MainActivity extends AppCompatActivity implements FragmentEventManager.NotifyActivities {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentEventManager fragmentEventManager = new FragmentEventManager();
//        Intent anIntent = getIntent();
//        Bundle bundle = anIntent.getExtras();
//
//        fragmentEventManager.setArguments(bundle);
        traveler(fragmentEventManager);

    }

    //standard replace transaction method
    public void traveler(Fragment fragmentName) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragmentContainer, fragmentName).commit();
    }

    @Override
    public void receiveMessaje(Event event) {

    }

}
