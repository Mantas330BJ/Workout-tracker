package Pages.Dialogs;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
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
@RequiresApi(api = Build.VERSION_CODES.O)

public class ConfirmExerciseFragment extends DialogFragment {
    private NavController navController;
    private Bundle parentBundle;
    private ExerciseData exerciseData;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.confirmation_fragment, container, false);
        navController = findNavController(this);

        assert getArguments() != null;
        parentBundle = getArguments();
        exerciseData = getExerciseData();

        setButtons(view);
        setRecyclerView(view);

        return view;
    }

    public void setRecyclerView(View view) {
        ExerciseListenerReadAdapter exerciseAdapter = new ExerciseListenerReadAdapter(new ArrayList<>(Collections.singletonList(exerciseData)));
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exerciseAdapter);
    }

    public void setButtons(View view) {
        Button yesButton = view.findViewById(R.id.yes_button);
        yesButton.setOnClickListener(v -> confirmExercise(exerciseData));

        Button noButton = view.findViewById(R.id.no_button);
        noButton.setOnClickListener(v -> navController.popBackStack());
    }

    public ExerciseData getExerciseData() {
        int workoutIdx = parentBundle.getInt(Data.WORKOUT_IDX);
        int exerciseIdx = parentBundle.getInt(Data.EXERCISE_IDX);
        return Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx);
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

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
