package com.ade.mvvm.data.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.ade.mvvm.helper.AppUtility;
import com.ade.mvvm.model.api.ApiResponse;
import com.ade.mvvm.model.api.ProductDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class GetProductDetail extends BaseApi {

    //Todo: Get Product Detail
    public LiveData<ApiResponse> getProductDetail(String id) {

        liveData = new MutableLiveData<>();
        Call<ProductDetail> call = mApiService.loadProductDetail(id);

        call.enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(@NonNull Call<ProductDetail> call, @NonNull Response<ProductDetail> response) {
                try {
                    ApiResponse<ProductDetail> product = new ApiResponse<>();
                    product.setData(response.body());
                    liveData.setValue(product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductDetail> call, @NonNull Throwable t) {
                AppUtility.logD("GetProductDetail", "on failure : " + t);
                ApiResponse<Throwable> throwable = new ApiResponse<>();
                throwable.setError(t);
                liveData.setValue(throwable);
            }
        });

        return liveData;
    }

}
