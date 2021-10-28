package Pages.Sets;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.FragmentEditExerciseBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.Sets.SetAdapter;
import Adapters.Sets.SetListenerReadAdapter;
import DataEdit.TextViews.StringTextView;
import Datas.ExerciseData;
import Datas.SetData;
import Pages.Dialogs.ChooseFileOptionsFragment;
import Pages.NavigationFragment;
import Utils.FragmentMethods;
import Variables.IntPasser;
import Variables.UriPasser;
import ViewModels.FileViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseFragment extends NavigationFragment {
    private SetListenerReadAdapter setAdapter;
    private ArrayList<SetData> setDatas;
    private ExerciseData exerciseData;

    private LinearLayoutManager linearLayoutManager;



    public static final String permissionString = Manifest.permission.READ_EXTERNAL_STORAGE;

    private final ActivityResultLauncher<String> permissionResult = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            result -> {
                if (result) {
                    navController.navigate(R.id.action_editExerciseFragment_to_chooseFileOptionsFragment);
                } else {
                    Toast.makeText(getContext(), "Storage permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
    );

    public void handlePermissions() {
        FileViewModel viewModel = new ViewModelProvider(requireActivity()).get(FileViewModel.class);
        viewModel.getSelected().observe(requireActivity(),text ->
                permissionResult.launch(permissionString));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentEditExerciseBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_edit_exercise, container, false);
        requireActivity().getViewModelStore().clear();
        handlePermissions();
        exerciseData = getExerciseData();
        binding.setData(exerciseData);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setExercise();
        setDatas = exerciseData.getSets();

        setAdapter = new SetAdapter(exerciseData.getSets());
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(setAdapter);

        linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        scrollScreen();

        setLongClickListener();
        setSetButtonListener();

    }

    public ExerciseData getExerciseData() {
        assert getArguments() != null;
        int workoutIdx = getArguments().getInt(Data.WORKOUT_IDX);
        int exerciseIdx = getArguments().getInt(Data.EXERCISE_IDX);
        return Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx);
    }

    public void scrollScreen() {
        assert getArguments() != null;
        int scrollPosition = getArguments().getInt(Data.SET_IDX);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? setDatas.size() - 1 : scrollPosition);
    }

    public void setExercise() {
        StringTextView exerciseName = view.findViewById(R.id.exercise);
        exerciseName.setTextClickListener();
    }

    public void incrementSets(int position, ArrayList<SetData> setDatas) {
        for (int i = position; i < setDatas.size(); ++i) {
            setDatas.get(i).setSet(new IntPasser(i + 1));
        }
    }

    public void createUndoSnackbar(int position, SetData removedSet) {
        Snackbar snackbar = Snackbar
                .make(((AppCompatActivity)context).findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.set)), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.undo), view -> {
                    setDatas.add(position, removedSet);
                    for (int i = position; i < setDatas.size(); ++i) {
                        setDatas.get(i).setSet(new IntPasser(i + 1));
                    }
                    linearLayoutManager.scrollToPosition(position);
                    setAdapter.notifyItemInserted(position);
                    setAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
                });
        snackbar.show();
    }

    public void setSetButtonListener() {
        Button setButton = view.findViewById(R.id.set_button);
        setButton.setOnClickListener(v -> {
            if (!setDatas.isEmpty()) {
                SetData setData = Data.copySet(setDatas.get(setDatas.size() - 1));
                setData.setSet(new IntPasser(setData.getSet().getVal() + 1));
                setDatas.add(setData);
            } else {
                setDatas.add(Data.createEmptySet());
            }
            setAdapter.notifyItemInserted(setDatas.size() - 1);
            linearLayoutManager.scrollToPosition(setDatas.size() - 1);
        });
    }

    public void setLongClickListener() {
        setAdapter.setLongClickListener(position -> {
            SetData removedSet = setDatas.get(position);
            setDatas.remove(position);
            incrementSets(position, setDatas);

            setAdapter.notifyItemRemoved(position);
            setAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
            createUndoSnackbar(position, removedSet);
        });
    }
}