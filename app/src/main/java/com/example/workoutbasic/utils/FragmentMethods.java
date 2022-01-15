package com.example.workoutbasic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentMethods {

    public static Fragment getParentFragment(Fragment fragment) {
        FragmentManager fm = fragment.requireParentFragment().getChildFragmentManager();
        return fm.getFragments().get(0);
    }

    public static Activity unwrap(Context context) {
        while (!(context instanceof Activity)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return (Activity) context;
    }
}
