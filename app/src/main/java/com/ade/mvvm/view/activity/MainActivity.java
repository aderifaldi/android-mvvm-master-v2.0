package com.ade.mvvm.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ade.mvvm.R;
import com.ade.mvvm.data.cache.CacheProduct;
import com.ade.mvvm.helper.Constant;
import com.ade.mvvm.model.api.Product;
import com.ade.mvvm.view.adapter.ProductListAdapter;
import com.ade.mvvm.viewmodel.ProductListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.list_product)
    RecyclerView listProduct;

    private ProductListViewModel viewModel;
    private LinearLayoutManager linearLayoutManager;
    private ProductListAdapter adapter;
    private CacheProduct cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        adapter = new ProductListAdapter((v, position) ->
                startActivity(new Intent(getApplicationContext(), ProductDetailActivity.class)
                        .putExtra("product", adapter.getData().get(position)))
        );

        listProduct.setLayoutManager(linearLayoutManager);
        listProduct.setAdapter(adapter);

        cacheProduct();

        loadProductList();

    }

    private void cacheProduct() {
        useCache();
        cache = new CacheProduct();
        cache.checkCache(realm);
    }

    private void loadProductList() {

        viewModel.showLoading(this, false);
        viewModel.getProductList();
        viewModel.getApiResponse().observe(this, apiResponse -> {

            viewModel.dismissLoading();

            if (apiResponse.getData() != null) {
                Product data = (Product) apiResponse.getData();
                storeDataToList(data);

                //Todo: Store Cache
                cache.storeCache(realm, new Gson().toJson(data));
            } else {
                //Todo: Load Cache
                Product data = viewModel.loadCache(cache.readCache(realm));
                storeDataToList(data);

                Toast.makeText(this, R.string.connection_problem_message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void storeDataToList(Product data) {
        if (data != null) {
            adapter.getData().clear();
            if (data.getStatus().equals(Constant.SUCCESS)) {
                for (int i = 0; i < data.getProducts().size(); i++) {
                    adapter.getData().add(data.getProducts().get(i));
                    adapter.notifyItemInserted(adapter.getData().size() - 1);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

}
