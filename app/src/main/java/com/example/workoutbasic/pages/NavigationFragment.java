package com.example.workoutbasic.pages;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

public class NavigationFragment extends Fragment {
    protected Context context;
    protected View view;
    protected NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        context = requireContext();
        navController = findNavController(this);
    }
}
