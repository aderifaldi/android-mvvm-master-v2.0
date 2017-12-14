package com.ade.mvvm.data.cache;

import io.realm.Realm;

/**
 * Created by RadyaLabs PC on 13/12/2017.
 */

public interface ICacheData {
    void checkCache(Realm realm);
    void storeCache(Realm realm, String data);
    String readCache(Realm realm);
}
