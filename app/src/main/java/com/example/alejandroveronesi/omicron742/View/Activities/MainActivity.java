package com.example.alejandroveronesi.omicron742.View.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.alejandroveronesi.omicron742.Model.POJO.Event;
import com.example.alejandroveronesi.omicron742.R;
import com.example.alejandroveronesi.omicron742.View.Fragments.FragmentEvent;
import com.example.alejandroveronesi.omicron742.View.Fragments.FragmentEventManager;
import com.example.alejandroveronesi.omicron742.View.Fragments.FragmentStartEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements FragmentEventManager.NotifyActivities, FragmentEvent.NotifyActivities2 {


    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        toolbar.setTitle("My Restorapp");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer1);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.usuario:

                        break;
                    case R.id.logOut:
                        signOut();
                        break;

                }
                drawerLayout.closeDrawers();
                return false;
            }
        });


        FragmentEventManager fragmentEventManager = new FragmentEventManager();
        traveler(fragmentEventManager);



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

    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //des loggear
    public void signOut() {
        // Firebase sign out
        mAuth.getInstance().signOut();
        goToLoginActivity();
    }

    @Override
    public void receiveMessaje2() {
        FragmentEventManager fragmentEventManager = new FragmentEventManager();
        pasarFragment(fragmentEventManager,false);
    }
}
