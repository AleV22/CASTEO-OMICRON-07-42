package com.example.alejandroveronesi.omicron742.View.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alejandroveronesi.omicron742.R;
import com.example.alejandroveronesi.omicron742.View.Fragments.FragmentEventManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragmentEventManager = new FragmentEventManager();
        traveler(fragmentEventManager);

        
    }


    //standard replace transaction method
    public void traveler(Fragment fragmentName) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentName).commit();
    }
}
