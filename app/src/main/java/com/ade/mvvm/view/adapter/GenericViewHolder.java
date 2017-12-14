package com.ade.mvvm.view.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Irfan AFif on 10/6/2017.
 */

public class GenericViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;

    public GenericViewHolder(ViewDataBinding dataBinding) {
        super(dataBinding.getRoot());
        binding = dataBinding;
    }

    public void bindModel(int modelType, Object obj) {
        binding.setVariable(modelType, obj);
        binding.executePendingBindings();
    }
}
