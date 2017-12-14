package com.ade.mvvm.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ade.mvvm.BR;
import com.ade.mvvm.R;
import com.ade.mvvm.databinding.ProductListDataBinding;
import com.ade.mvvm.model.api.Product;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by RadyaLabs PC on 11/10/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<GenericViewHolder> {
    private ItemClickListener listener;
    private List<Product.Products> listItem;
    private GenericViewHolder viewHolder;

    public ProductListAdapter(ItemClickListener listener) {
        this.listener = listener;
        listItem = new ArrayList<>();
    }

    public List<Product.Products> getData() {
        return listItem;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.product_item, parent, false);

        viewHolder = new GenericViewHolder(binding);

        binding.getRoot().setOnClickListener(view ->
                listener.onItemClick(view, viewHolder.getAdapterPosition())
        );

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        Product.Products itemData = listItem.get(position);
        ProductListDataBinding viewModel = new ProductListDataBinding(itemData);
        holder.bindModel(BR.productData, viewModel);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }
}
