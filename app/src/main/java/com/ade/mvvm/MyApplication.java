package com.ade.mvvm;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by RadyaLabs PC on 07/11/2017.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
