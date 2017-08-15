package com.example.alejandroveronesi.omicron742.View.Fragments;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alejandroveronesi.omicron742.R;
import com.google.android.gms.vision.text.Text;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.R.attr.phoneNumber;


public class FragmentStartEvent extends Fragment implements LocationListener {

    public static final String EVENT_NAME = "name";
    public static final String EVENT_TIME = "time";
    public static final String EVENT_EMAIL = "email";
    public static final String EVENT_PHONE = "phone";

    private Button buttonStart;
    private Location currentLocation;
    private LocationManager locationManager;
    private Button btnObtener;
    private TextView tvCoordenates;
    Double latitud;
    Double longitud;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_event, container, false);

        //currentLocation = new Location("http://maps.google.com?q=");

        Bundle bundle = getArguments();

        String name = bundle.getString(EVENT_NAME);
        final Integer time = bundle.getInt(EVENT_TIME)*60000;
        String email = bundle.getString(EVENT_EMAIL);
        Integer phone = bundle.getInt(EVENT_PHONE);

        TextView nameEvent =  view.findViewById(R.id.nameEventTextView);
        TextView emailEvent =  view.findViewById(R.id.emailEventTextView);
        TextView phoneEvent =  view.findViewById(R.id.phoneEventTextView);
        final TextView timeEvent = view.findViewById(R.id.timerTextView);

        nameEvent.setText(name);
        emailEvent.setText(email);
        phoneEvent.setText(phone.toString());
        timeEvent.setText(String.format(Locale.getDefault(),"%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes( time),
                TimeUnit.MILLISECONDS.toSeconds(time) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time))));

        tvCoordenates = (TextView)view.findViewById(R.id.textViewCoordenates);
        btnObtener = (Button)view.findViewById(R.id.btnObtener);
        btnObtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

        buttonStart = view.findViewById(R.id.startButton);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
                new CountDownTimer(time, 1000) { // adjust the milli seconds here

                    public void onTick(long millisUntilFinished) {
                        timeEvent.setText(String.format(Locale.getDefault(), "%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                        currentLocation = new Location("http://maps.google.com?q=");

                    }
                    public void onFinish() {
                        timeEvent.setText("done!");
                        //este funciono, esta probado
//                        sendSms("+5491132870691", "auxilio", true);

                        //este es el que funciona y se usa
//                        SmsManager smsManager = SmsManager.getDefault();
//                        smsManager.sendTextMessage("+5491132870691", null, "Auxilio", null, null);

                          sendLocationSMS("+5491132870691", currentLocation);
//                          Toast.makeText(getContext(),"Current Location:" + currentLocation, Toast.LENGTH_LONG).show();

                    }
                }.start();
            }
        });
        return view;
    }

    public void sendLocationSMS(String phoneNumber, Location currentLocation) {

        SmsManager smsManager = SmsManager.getDefault();
        StringBuffer smsBody = new StringBuffer();
        smsBody.append("http://maps.google.com?q=");
        smsBody.append(latitud);
        smsBody.append(",");
        smsBody.append(longitud);
        smsManager.sendTextMessage(phoneNumber, null, smsBody.toString(), null, null);
    }


    void getLocation() {
        try {
            locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
//            LocationListener locationListener = new LocationListener();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        longitud = location.getLongitude();
        latitud = location.getLatitude();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


//    private class LocationListener implements android.location.LocationListener{
//
//        @Override
//        public void onLocationChanged(Location location) {
//            latitud = location.getLatitude();
//            longitud = location.getLongitude();
//            tvCoordenates.setText("Current Location:" + location.getLongitude() + location.getLatitude());
//        }
//        @Override
//        public void onStatusChanged(String s, int i, Bundle bundle) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String s) {
//            Toast.makeText(getContext(), "s", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onProviderDisabled(String s) {
//
//        }
//    }
}
