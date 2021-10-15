package Utils;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentMethods {

    public static void dismissChooseTypeFragment(Fragment fragment) {
        FragmentManager fm = fragment.requireParentFragment().getChildFragmentManager();
        ((DialogFragment)fm.getFragments().get(1)).dismiss();
    }
}
