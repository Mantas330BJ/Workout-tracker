package com.example.workoutbasic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ColumnFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pizzaRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_column, container, false);

        String[] columnNames = new String[Columns.names.length];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = Columns.names[i];
        }


        ColumnAdapter adapter = new ColumnAdapter(columnNames);
        pizzaRecycler.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), Columns.names.length);
        pizzaRecycler.setLayoutManager(layoutManager);

        return pizzaRecycler;
    }
}