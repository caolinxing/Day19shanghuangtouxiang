package com.example.utils;

import android.app.Application;

import com.facebook.fresco.helper.Phoenix;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Phoenix.init(this);
    }
}
