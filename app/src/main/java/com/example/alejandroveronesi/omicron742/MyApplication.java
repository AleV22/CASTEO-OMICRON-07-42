package com.example.alejandroveronesi.omicron742;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by alejandroveronesi on 16/8/17.
 */

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
