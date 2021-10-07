package Activities;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import Fragments.ChooseTypeFragment;
import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.ExerciseAdapter;
import TextViews.DatePickTextView;
import Datas.ExerciseData;
import Datas.WorkoutData;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditWorkoutActivity extends InputListenerActivity {
    private ExerciseAdapter exerciseAdapter;
    private LinearLayoutManager linearLayoutManager;

    private int workoutIdx;
    private ArrayList<ExerciseData> exerciseDatas;

    private ChooseTypeFragment currentFragment; //Choosing create empty or previous

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        WorkoutData workoutData = Data.getWorkoutDatas().get(workoutIdx);
        setDate(workoutData);

        exerciseDatas = workoutData.getExercises();
        exerciseAdapter = new ExerciseAdapter(exerciseDatas);

        RecyclerView recyclerView = findViewById(R.id.table);
        recyclerView.setAdapter(exerciseAdapter);

        linearLayoutManager = new LinearLayoutManager(this);
        scrollScreen();

        recyclerView.setLayoutManager(linearLayoutManager);
        setDoubleClickListener();
        setLongClickListener();
    }

    public void setDate(WorkoutData workoutData) {
        DatePickTextView date = findViewById(R.id.date);
        date.setText(workoutData.getDate());
        date.setTextClickListener();
    }

    public void scrollScreen() {
        int scrollPosition = getIntent().getIntExtra(Data.EXERCISE_IDX, -1);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? exerciseDatas.size() - 1 : scrollPosition);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, NavigationActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean areExercises() {
        for (WorkoutData workoutData : Data.getWorkoutDatas()) {
            if (!workoutData.getExercises().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void createUndoSnackbar(int position, ExerciseData removedExercise) {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.exercise)), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.undo), view -> {
                    exerciseDatas.add(position, removedExercise);
                    linearLayoutManager.scrollToPosition(position);
                    exerciseAdapter.notifyItemInserted(position);
                    exerciseAdapter.notifyItemRangeChanged(position, exerciseDatas.size() - position);
                });
        snackbar.show();
    }

    public void setDoubleClickListener() {
        exerciseAdapter.setDoubleClickListener(exerciseIdx -> setIdx -> {
            Intent intent = new Intent(this, EditExerciseActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            intent.putExtra(Data.EXERCISE_IDX, exerciseIdx);
            if (setIdx != -1)
                intent.putExtra(Data.SET_IDX, setIdx);
            startActivity(intent);
            finish();
        });
    }

    public void setLongClickListener() {
        exerciseAdapter.setLongClickListener(position -> {
            ExerciseData removedExercise = exerciseDatas.get(position);
            exerciseDatas.remove(position);
            exerciseAdapter.notifyItemRemoved(position);
            exerciseAdapter.notifyItemRangeChanged(position, exerciseDatas.size() - position);
            createUndoSnackbar(position, removedExercise);
        });
    }

    public void onAddExercise(View view) {
        currentFragment = new ChooseTypeFragment(getString(R.string.exercise));
        currentFragment.show(getSupportFragmentManager(), "ChooseTypeFragment");
    }

    public void onCreateEmpty(View view) {
        currentFragment.dismiss();
        exerciseDatas.add(Data.createEmptyExercise());
        exerciseAdapter.notifyItemInserted(exerciseDatas.size() - 1);
        linearLayoutManager.scrollToPosition(exerciseDatas.size() - 1);
    }

    public void onCreatePrevious(View view) {
        if (!areExercises()) {
            currentFragment.dismiss();
            Intent intent = new Intent(this, CopyExerciseActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.no_available, getString(R.string.exercise)), Toast.LENGTH_SHORT).show();
        }
    }
}