package Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import Adapters.Exercises.ExerciseListenerReadAdapter;
import Datas.ExerciseData;

public class ConfirmExerciseFragment extends DialogFragment {
    private NavController navController;
    private Bundle parentBundle;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.confirmation_fragment, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        assert getArguments() != null;
        parentBundle = getArguments();
        int workoutIdx = parentBundle.getInt(Data.WORKOUT_IDX);
        int exerciseIdx = parentBundle.getInt(Data.EXERCISE_IDX);
        ExerciseData exerciseData = Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx);


        Button yesButton = view.findViewById(R.id.yes_button);
        yesButton.setOnClickListener(v -> confirmExercise(exerciseData));

        Button noButton = view.findViewById(R.id.no_button);
        noButton.setOnClickListener(v ->  {
            dismiss();
            navController.popBackStack();
        });

        ExerciseListenerReadAdapter exerciseAdapter = new ExerciseListenerReadAdapter(new ArrayList<>(Collections.singletonList(exerciseData)));
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)

    public void confirmExercise(ExerciseData exerciseData) {

        int destWorkoutIdx = parentBundle.getInt(Data.DEST_WORKOUT_IDX);

        Bundle bundle = new Bundle();
        bundle.putInt(Data.WORKOUT_IDX, destWorkoutIdx);

        ArrayList<ExerciseData> destinationDatas = Data.getWorkoutDatas().get(destWorkoutIdx).getExercises();
        ExerciseData copiedExercise = Data.copyExercise(exerciseData);
        destinationDatas.add(copiedExercise);
        navController.navigate(R.id.action_confirmExerciseFragment_to_editWorkoutFragment, bundle);
    }
}
