package com.example.alejandroveronesi.omicron742;

import android.app.Application;

import com.twitter.sdk.android.core.Twitter;

/**
 * Created by alejandroveronesi on 26/7/17.
 */

public class MyApplicationTwitter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Twitter.initialize(this);
        }
    }

