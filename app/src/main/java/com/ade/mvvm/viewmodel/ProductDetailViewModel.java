package com.ade.mvvm.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ade.mvvm.data.api.GetProductDetail;
import com.ade.mvvm.model.api.ApiResponse;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class ProductDetailViewModel extends BaseViewModel implements BaseViewModel.IBaseViewModel {

    //Todo: Return Response
    @NonNull
    public LiveData<ApiResponse> getApiResponse() {
        return apiResponse;
    }

    //Todo: Get Product Detail
    public void getProductDetail(String id) {
        GetProductDetail api = new GetProductDetail();
        apiResponse.addSource(
                api.getProductDetail(id),
                apiResponse -> this.apiResponse.setValue(apiResponse)
        );
    }

    @Override
    public void showLoading(Context context, boolean isCancelable) {
        showProgressLoading(context, isCancelable);
    }

    @Override
    public void dismissLoading() {
        dismissProgressLoading();
    }

    @Override
    public void showAlert(Activity activity, String message) {
        showAlertDialog(activity, message);
    }
}
