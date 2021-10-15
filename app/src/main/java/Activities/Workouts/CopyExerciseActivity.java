package Activities.Workouts;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;

import java.util.ArrayList;

import Activities.DatabaseActivity;
import Datas.ExerciseData;
import Interfaces.ExerciseConfirmer;


@RequiresApi(api = Build.VERSION_CODES.O)

public class CopyExerciseActivity extends DatabaseActivity implements ExerciseConfirmer {
    final FragmentManager fm = getSupportFragmentManager();
    private int workoutIdx; //Workout index from which exercise is being copied.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);

        workoutIdx = getIntent().getIntExtra(Data.WORKOUT_IDX, -1);
        final Fragment copyExerciseFragment = new CopyExerciseFragment();
        Bundle args = new Bundle();
        args.putInt(CopyExerciseFragment.WORKOUT_STRING, workoutIdx);
        copyExerciseFragment.setArguments(args);

        fm.beginTransaction().add(R.id.main_container, copyExerciseFragment, "CopyExerciseFragment").commit();
    }

    @Override
    public void confirmExercise(ExerciseData exerciseData) {
        //Intent intent = new Intent(this, EditWorkoutActivity.class);
        //intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
        ArrayList<ExerciseData> destinationDatas = Data.getWorkoutDatas().get(workoutIdx).getExercises();
        ExerciseData copiedExercise = Data.copyExercise(exerciseData);
        destinationDatas.add(copiedExercise);
        //startActivity(intent);
    }
}


