package com.example.alejandroveronesi.omicron742.View.Fragments;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

import com.example.alejandroveronesi.omicron742.R;

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
    private LocationManager locationManager;
    private Context context;
    private Location actualLocation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_event, container, false);

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


        buttonStart = view.findViewById(R.id.startButton);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(time, 1000) { // adjust the milli seconds here

                    public void onTick(long millisUntilFinished) {
                        timeEvent.setText(String.format(Locale.getDefault(), "%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    }
                    public void onFinish() {
                        timeEvent.setText("done!");
                        //este funciono, esta probado
//                        sendSms("+5491132870691", "auxilio", true);

                        //este es el que funciona y se usa
//                        SmsManager smsManager = SmsManager.getDefault();
//                        smsManager.sendTextMessage("+5491132870691", null, "Auxilio", null, null);

                        actualLocation.getLatitude();
                        sendLocationSMS("+5491132870691",actualLocation);

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
        smsBody.append(currentLocation.getLatitude());
        smsBody.append(",");
        //smsBody.append(currentLocation.getLongitude());
        smsManager.sendTextMessage(phoneNumber, null, smsBody.toString(), null, null);
    }

    public void getLocation() {
        try {
            locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onLocationChanged(Location location) {
        actualLocation = location;
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


//    private void sendSms(String number,String message, boolean isBinary)
//    {
//        SmsManager manager = SmsManager.getDefault();
//
//        String SMS_SENT = "SMS_SENT";
//        String SMS_DELIVERED = "SMS_DELIVERED";
//
//        PendingIntent piSend = PendingIntent.getBroadcast(getContext(), 0, new Intent(SMS_SENT), 0);
//        PendingIntent piDelivered = PendingIntent.getBroadcast(getContext(), 0, new Intent(SMS_DELIVERED), 0);
//
//        if(isBinary)
//        {
//            byte[] data = new byte[message.length()];
//
//            for(int index=0; index<message.length() && index < 100; ++index)
//            {
//                data[index] = (byte)message.charAt(index);
//            }
//            manager.sendDataMessage(number, null, (short) 8901, data,piSend, piDelivered);
//        }
//        else
//        {
//            int length = message.length();
//
//            if(length > 100)
//            {
//                ArrayList<String> messagelist = manager.divideMessage(message);
//                manager.sendMultipartTextMessage(number, null, messagelist, null, null);
//            }
//            else
//            {
//                manager.sendTextMessage(number, null, message, piSend, piDelivered);
//            }
//        }
//    }
}
