package com.pc;

import android.content.Context;
import android.support.multidex.MultiDexApplication;


public class ImApplication extends MultiDexApplication {

    public static Context context;



    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }



}
