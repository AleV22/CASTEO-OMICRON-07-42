package com.example.alejandroveronesi.omicron742;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.twitter.sdk.android.core.Twitter;


public class MyApplicationTwitter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Twitter.initialize(this);
        MultiDex.install(this);

    }
    }

