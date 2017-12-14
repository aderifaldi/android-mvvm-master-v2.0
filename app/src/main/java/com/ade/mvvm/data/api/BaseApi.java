package com.ade.mvvm.data.api;

import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ade.mvvm.helper.Constant;
import com.ade.mvvm.model.api.ApiResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

class BaseApi {

    static final String BASE_URL = Constant.BUKALAPAK;
    static final int TIMEOUT = 60;

    ApiService mApiService;
    MutableLiveData<ApiResponse> liveData;
    HttpLoggingInterceptor logInterceptor;
    OkHttpClient.Builder builder;
    OkHttpClient okHttpClient;
    Retrofit retrofit;
    Gson gson;

    BaseApi() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder = new OkHttpClient.Builder();
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(logInterceptor);

        okHttpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient).build();

        mApiService = retrofit.create(ApiService.class);
    }

}
