package Adapters;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.recyclerview.widget.RecyclerView;

public class BindingViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder  {
    public final B binding;

    public BindingViewHolder(B binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
