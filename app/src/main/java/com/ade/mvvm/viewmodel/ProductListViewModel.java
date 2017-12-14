package com.ade.mvvm.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ade.mvvm.data.api.GetProductList;
import com.ade.mvvm.model.api.ApiResponse;
import com.ade.mvvm.model.api.Product;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class ProductListViewModel extends BaseViewModel implements BaseViewModel.IBaseViewModel {

    //Todo: Return Response
    @NonNull
    public LiveData<ApiResponse> getApiResponse() {
        return apiResponse;
    }

    //Todo: Get Product List
    public void getProductList() {
        GetProductList api = new GetProductList();
        apiResponse.addSource(
                api.getProduct(),
                apiResponse -> this.apiResponse.setValue(apiResponse)
        );
    }

    public Product loadCache(String cache){
        Product data = null;
        JsonObject object;
        JsonObject json;
        String rawContent;
        Gson gson;
        GsonBuilder gsonBuilder;

        try {
            rawContent = cache;
            json = new JsonParser().parse(rawContent).getAsJsonObject();
            object = json.getAsJsonObject();

            gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();

            data = gson.fromJson(object, Product.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return data;
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
