package com.ade.mvvm.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;

/**
 * Created by RadyaLabs PC on 14/11/2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void useCache(){
        realm = Realm.getDefaultInstance();
    }

}
