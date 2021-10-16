package Utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import com.example.workoutbasic.R;

public class FragmentMethods {

    public static Fragment getParentFragment(Fragment fragment) {
        FragmentManager fm = fragment.requireParentFragment().getChildFragmentManager();
        return fm.getFragments().get(0);
    }

    public static Fragment getParentFragment(Context context, int index) {
        FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment)fm.findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        System.out.println(navHostFragment.getChildFragmentManager().getFragments());
        return navHostFragment.getChildFragmentManager().getFragments().get(index);
    }

    public static Activity unwrap(Context context) {
        while (!(context instanceof Activity)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return (Activity) context;
    }
}
