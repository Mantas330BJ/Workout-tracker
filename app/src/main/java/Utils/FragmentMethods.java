package Utils;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentMethods {

    public static Fragment getParentFragment(Fragment fragment) {
        FragmentManager fm = fragment.requireParentFragment().getChildFragmentManager();
        return fm.getFragments().get(0);
    }
}
