package com.ade.mvvm.data.api;

import com.ade.mvvm.model.api.Product;
import com.ade.mvvm.model.api.ProductDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by aderifaldi on 08/08/2016.
 */
public interface ApiService {

    @GET("products.json")
    Call<Product> loadProduct();

    @GET("products/{id}.json")
    Call<ProductDetail> loadProductDetail(@Path("id") String id);

}
