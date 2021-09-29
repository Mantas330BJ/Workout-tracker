package Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;

import java.util.ArrayList;

import Datas.ExerciseData;
import Fragments.ConfirmExercisePopup;
import Interfaces.ExerciseConfirmer;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CopyExerciseActivity extends MainActivity implements ExerciseConfirmer {
    private int workoutIdx; //Workout idx from which exercise is being copied.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        workoutIdx = getIntent().getIntExtra(Data.WORKOUT_IDX, -1);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void scrollLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        linearLayoutManager.scrollToPosition(workoutIdx);
    }

    @Override
    public void makeClickListener() {
        addWorkoutButton.setVisibility(View.GONE);
        Toast toast = Toast.makeText(this, getString(R.string.select_exercise), Toast.LENGTH_SHORT);
        toast.show();
        setAdapterWorkoutListenerClickListener();
    }

    @Override
    public void confirmExercise(ExerciseData exerciseData) {
        Intent intent = new Intent(this, EditWorkoutActivity.class);
        intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
        ArrayList<ExerciseData> destinationDatas = Data.getWorkoutDatas().get(workoutIdx).getExercises();
        ExerciseData copiedExercise = Data.copyExercise(exerciseData);
        destinationDatas.add(copiedExercise);
        startActivity(intent);
    }

    public void setAdapterWorkoutListenerClickListener() {
        workoutAdapter.setWorkoutListenerClickListener(position -> childPos -> {
            if (childPos != -1) { //Not header clicked
                ExerciseData exerciseData = Data.getWorkoutDatas().get(position).getExercises().get(childPos);
                ConfirmExercisePopup popup = new ConfirmExercisePopup(exerciseData);
                popup.show(getSupportFragmentManager(), "ConfirmExercisePopup");
                getSupportFragmentManager().executePendingTransactions();
            }
        });
    }
}
