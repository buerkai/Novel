package com.happ.happlib;


import android.support.multidex.MultiDexApplication;

public class BaseAPP extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        HHandler.getInstance().start();
    }
}
