package com.ade.mvvm.data.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.ade.mvvm.helper.AppUtility;
import com.ade.mvvm.model.api.ApiResponse;
import com.ade.mvvm.model.api.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class GetProductList extends BaseApi {

    //Todo: Get Product List
    public LiveData<ApiResponse> getProduct() {

        liveData = new MutableLiveData<>();
        Call<Product> call = mApiService.loadProduct();

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                try {
                    ApiResponse<Product> product = new ApiResponse<>();
                    product.setData(response.body());
                    liveData.setValue(product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                AppUtility.logD("GetProductList", "on failure : " + t);
                ApiResponse<Throwable> throwable = new ApiResponse<>();
                throwable.setError(t);
                liveData.setValue(throwable);
            }
        });

        return liveData;
    }

}
