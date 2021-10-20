package Pages.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ChooseTypeFragmentBinding;

import Interfaces.ButtonOptions;
import Utils.FragmentMethods;

public class ChooseTypeFragment extends DialogFragment {
    public static final String NAME_KEY = "name";
    public String name;
    public ButtonOptions parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ChooseTypeFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.choose_type_fragment, container, false);
        binding.setFrag(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        name = getArguments().getString(NAME_KEY);
        parent = (ButtonOptions)FragmentMethods.getParentFragment(this);
    }
}
