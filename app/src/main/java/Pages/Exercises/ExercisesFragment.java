package Pages.Exercises;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;

import java.util.ArrayList;

import Adapters.Exercises.ExerciseAdapter;
import Adapters.Exercises.ExerciseReadAdapter;
import Adapters.Workouts.WorkoutInfoAdapter;
import Datas.ExerciseData;
import Pages.NavigationFragment;

public class ExercisesFragment extends NavigationFragment {
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercises, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<ExerciseData> exercises = Data.getWorkoutDatas().get(0).getExercises();
        ExerciseReadAdapter workoutInfoAdapter = new ExerciseReadAdapter(exercises);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(workoutInfoAdapter);

        linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();

    }
}
