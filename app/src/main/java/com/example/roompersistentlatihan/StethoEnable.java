package com.example.roompersistentlatihan;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class StethoEnable extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}