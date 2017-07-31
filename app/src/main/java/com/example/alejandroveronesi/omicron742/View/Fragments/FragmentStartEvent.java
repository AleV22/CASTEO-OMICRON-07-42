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

    private Button buttonStart;
    private TextView timerTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_event, container, false);

        buttonStart = (Button) view.findViewById(R.id.startButton);
        timerTextView = (TextView) view.findViewById(R.id.timerTextView);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(30000, 1000) { // adjust the milli seconds here

                    public void onTick(long millisUntilFinished) {
                        timerTextView.setText(""+String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    }

                    public void onFinish() {
                        timerTextView.setText("done!");
                    }
                }.start();

            }
        });
        
        return view;
    }

}
