package com.ade.mvvm.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ade.mvvm.R;
import com.ade.mvvm.databinding.ProductDetailActivityBinding;
import com.ade.mvvm.databinding.ProductDetailDataBinding;
import com.ade.mvvm.helper.Constant;
import com.ade.mvvm.model.api.Product;
import com.ade.mvvm.model.api.ProductDetail;
import com.ade.mvvm.viewmodel.ProductDetailViewModel;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class ProductDetailActivity extends BaseActivity {

    private ProductDetailActivityBinding productDetailActivityBinding;
    private ProductDetailViewModel viewModel;
    private Product.Products product;
    private ProductDetailDataBinding productDetailDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productDetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.product_detail_activity);

        product = (Product.Products) getIntent().getExtras().getSerializable("product");
        viewModel = ViewModelProviders.of(this).get(ProductDetailViewModel.class);

        loadProductDetail();

    }

    private void loadProductDetail() {

        viewModel.showLoading(this, true);
        viewModel.getProductDetail(product.getId());
        viewModel.getApiResponse().observe(this, apiResponse -> {

            viewModel.dismissLoading();

            if (apiResponse.getData() != null) {
                ProductDetail data = (ProductDetail) apiResponse.getData();
                if (data.getStatus().equals(Constant.SUCCESS)) {
                    productDetailDataBinding = new ProductDetailDataBinding(data.getProduct());
                    productDetailActivityBinding.setProductDetailData(productDetailDataBinding);
                } else {
                    viewModel.showAlert(ProductDetailActivity.this, data.getAlerts().getMessage());
                }
            } else {
                viewModel.showAlert(ProductDetailActivity.this, getString(R.string.connection_problem_message));
            }

        });

    }

}
