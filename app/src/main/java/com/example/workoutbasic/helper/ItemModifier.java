package com.example.workoutbasic.helper;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemModifier<T> {
    private final List<T> elems;
    private final RecyclerView.Adapter<?> adapter;
    private final RecyclerView.LayoutManager layoutManager;

    public ItemModifier(List<T> elems, RecyclerView.Adapter<?> adapter, RecyclerView.LayoutManager layoutManager) {
        this.elems = elems;
        this.adapter = adapter;
        this.layoutManager = layoutManager;
    }

    public void addItem(int position, T itemToAdd) {
        elems.add(position, itemToAdd);
        adapter.notifyItemInserted(position);
        adapter.notifyItemRangeChanged(position, elems.size() - position);
        layoutManager.scrollToPosition(position);
    }

    public T removeItem(int position) {
        T removedElem = elems.get(position);
        elems.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, elems.size() - position);
        return removedElem; //TODO: auto create undo?
    }
}
