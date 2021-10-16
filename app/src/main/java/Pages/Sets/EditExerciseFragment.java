package Pages.Sets;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.Sets.SetAdapter;
import Adapters.Sets.SetListenerReadAdapter;
import DataEdit.ImageViews.WorkoutFileView;
import DataEdit.TextViews.StringTextView;
import Datas.ExerciseData;
import Datas.SetData;
import Interfaces.TextViewData;
import Pages.Dialogs.ChooseFileOptionsFragment;
import Utils.FragmentMethods;
import Variables.IntPasser;
import Variables.UriPasser;
import ViewModels.FileViewModel;
import ViewModels.SharedViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseFragment extends Fragment {
    private NavController navController;
    private SetListenerReadAdapter setAdapter;
    private ArrayList<SetData> setDatas;

    private int workoutIdx;
    private int exerciseIdx;

    private LinearLayoutManager linearLayoutManager;

    private View view;
    private Context context;

    private UriPasser parentData; //TODO: pass by viewmodel

    public static final String permissionString = Manifest.permission.READ_EXTERNAL_STORAGE;

    private final ActivityResultLauncher<String> permissionResult = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            result -> {
                if (result) {
                    navController.navigate(R.id.action_editExerciseFragment_to_chooseFileOptionsFragment);
                    FragmentManager fm = ((FragmentActivity)getContext()).getSupportFragmentManager();
                    NavHostFragment navHostFragment = (NavHostFragment)fm.findFragmentById(R.id.nav_host_fragment);
                    navHostFragment.getChildFragmentManager().executePendingTransactions();

                    ChooseFileOptionsFragment calledFragment = (ChooseFileOptionsFragment) FragmentMethods.getParentFragment(getContext(), 1);
                    FileViewModel viewModel = new ViewModelProvider(calledFragment).get(FileViewModel.class);
                    viewModel.select(parentData);
                } else {
                    Toast.makeText(getContext(), "Storage permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
    );

    public void showPermission(UriPasser parentData) {
        this.parentData = parentData;
        permissionResult.launch(permissionString);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_exercise, container, false);
        context = requireContext();
        navController = findNavController(this);

        assert getArguments() != null;
        workoutIdx = getArguments().getInt(Data.WORKOUT_IDX);
        exerciseIdx = getArguments().getInt(Data.EXERCISE_IDX);
        ExerciseData exerciseData = Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx);

        setExercise(exerciseData);

        setDatas = exerciseData.getSets();
        linearLayoutManager = new LinearLayoutManager(context);
        scrollScreen();

        setAdapter = new SetAdapter(exerciseData.getSets());
        createRecyclerView();

        setLongClickListener();
        setSetButtonListener();

        return view;
    }

    public void createRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(setAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void scrollScreen() {
        assert getArguments() != null;
        int scrollPosition = getArguments().getInt(Data.SET_IDX);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? setDatas.size() - 1 : scrollPosition);
    }

    public void setExercise(ExerciseData exerciseData) {
        StringTextView exerciseName = view.findViewById(R.id.exercise);
        exerciseName.setText(exerciseData.getExercise());
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