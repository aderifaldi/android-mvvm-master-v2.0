package com.ade.mvvm.data.cache;

import com.ade.mvvm.model.local.ProductCache;

import io.realm.Realm;

/**
 * Created by RadyaLabs PC on 12/12/2017.
 */

public class CacheProduct implements ICacheData {

    public CacheProduct() {}

    @Override
    public void checkCache(Realm realm) {
        ProductCache productCache = realm.where(ProductCache.class).findFirst();
        if (productCache == null){
            realm.executeTransaction(cache -> {
                ProductCache product = cache.createObject(ProductCache.class);
                product.setJson("");
            });
        }
    }

    @Override
    public void storeCache(Realm realm, String data) {
        final ProductCache productCache = realm.where(ProductCache.class).findFirst();
        realm.executeTransaction(cache -> productCache.setJson(data));
    }

    @Override
    public String readCache(Realm realm) {
        ProductCache productCache = realm.where(ProductCache.class).findFirst();
        return productCache.getJson();
    }

}
