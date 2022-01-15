package com.example.workoutbasic.adapters;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BindingViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder  {
    private final B binding;

    public BindingViewHolder(B binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public B getBinding() {
        return binding;
    }
}
