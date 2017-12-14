package com.ade.mvvm.databinding;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.text.Html;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ade.mvvm.helper.AppUtility;
import com.ade.mvvm.model.api.Product;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class ProductListDataBinding extends BaseObservable {

    private Product.Products product;

    public ProductListDataBinding(Product.Products data) {
        product = data;
    }

    public String getProductName() {
        return product.getName();
    }

    public String getProductPrice() {
        return AppUtility.formatMoney("Rp. ", product.getPrice(), '.', "");
    }

    public String getProductDesc() {
        return String.valueOf(Html.fromHtml(product.getDesc()));
    }

    public String getProductImage() {
        return product.getImages().get(0);
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

}
