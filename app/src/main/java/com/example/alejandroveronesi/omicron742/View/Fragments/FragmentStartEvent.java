package com.example.alejandroveronesi.omicron742.View.Fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alejandroveronesi.omicron742.R;

import java.util.concurrent.TimeUnit;


public class FragmentStartEvent extends Fragment {

    public static final String EVENT_NAME = "name";
    public static final String EVENT_TIME = "time";
    public static final String EVENT_EMAIL = "email";
    public static final String EVENT_PHONE = "phone";

    private Button buttonStart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_event, container, false);

        Bundle bundle = getArguments();

        String name = bundle.getString(EVENT_NAME);
        final Integer time = bundle.getInt(EVENT_TIME);
        String email = bundle.getString(EVENT_EMAIL);
        Integer phone = bundle.getInt(EVENT_PHONE);

        TextView nameEvent = (TextView) view.findViewById(R.id.nameEventTextView);
        TextView emailEvent = (TextView) view.findViewById(R.id.emailEventTextView);
        TextView phoneEvent = (TextView) view.findViewById(R.id.phoneEventTextView);
        final TextView timeEvent = (TextView) view.findViewById(R.id.timerTextView);

        nameEvent.setText(name);
        emailEvent.setText(email);
        phoneEvent.setText(phone.toString());


        buttonStart = (Button) view.findViewById(R.id.startButton);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(time, 1000) { // adjust the milli seconds here

                    public void onTick(long millisUntilFinished) {
                        timeEvent.setText(""+String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    }

                    public void onFinish() {
                        timeEvent.setText("done!");
                    }
                }.start();

            }
        });
        
        return view;
    }

}
