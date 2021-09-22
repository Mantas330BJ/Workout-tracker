package Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
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
import com.example.workoutbasic.OnInputListener;
import com.example.workoutbasic.R;
import com.example.workoutbasic.Workout;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.ExerciseAdapter;
import CustomViews.WorkoutTextView;
import Datas.ExerciseData;
import Datas.WorkoutData;
import Interfaces.WorkoutInput;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditWorkoutActivity extends DatabaseActivity implements OnInputListener {
    private ExerciseAdapter exerciseAdapter;
    private ChooseTypeFragment currentFragment;
    private WorkoutInput currentClicked;
    private ExerciseData removedExercise;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ExerciseData> exerciseDatas;
    private WorkoutTextView date;

    public int workoutIdx;
    public Workout workout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        WorkoutData workoutData = Data.getWorkoutDatas().get(workoutIdx);
        workout = new Workout(workoutData);

        date = findViewById(R.id.date);
        date.setBaseParams(workoutData.getDate());
        date.setTextEditListener();

        exerciseDatas = workout.getWorkoutData().getExercises();
        exerciseAdapter = new ExerciseAdapter(exerciseDatas, false);

        RecyclerView recyclerView = findViewById(R.id.table);
        recyclerView.setAdapter(exerciseAdapter);
        linearLayoutManager = new LinearLayoutManager(this);

        int scrollPosition = getIntent().getIntExtra(Data.EXERCISE_IDX, -1);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? exerciseDatas.size() - 1 : scrollPosition);

        recyclerView.setLayoutManager(linearLayoutManager);

        exerciseAdapter.setWorkoutListenerClickListener(exerciseIdx -> setIdx -> {
            Intent intent = new Intent(this, EditExerciseActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            intent.putExtra(Data.EXERCISE_IDX, exerciseIdx);
            if (setIdx != -1)
                intent.putExtra(Data.SET_IDX, setIdx);
            startActivity(intent);
            finish();
        });

        setAdapterLongClickListener();
    }

    public void setAdapterLongClickListener() {
        exerciseAdapter.setLongClickListener(position -> {
            removedExercise = exerciseDatas.get(position);
            exerciseDatas.remove(position);
            exerciseAdapter.notifyItemRemoved(position);
            exerciseAdapter.notifyItemRangeChanged(position, exerciseDatas.size() - position);
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.exercise)), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.undo), view -> {
                        exerciseDatas.add(position, removedExercise);
                        linearLayoutManager.scrollToPosition(position);
                        exerciseAdapter.notifyItemInserted(position);
                        exerciseAdapter.notifyItemRangeChanged(position, exerciseDatas.size() - position);
                    });
            snackbar.show();
        });
    }

    @Override
    public void sendInput(String input) {
        currentClicked.setText(input.toString());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCurrentClicked(WorkoutInput currentClicked) {
        this.currentClicked = currentClicked;
    }

    public void onAddExercise(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(ChooseTypeFragment.PARENT, getString(R.string.exercise));
        currentFragment = new ChooseTypeFragment();
        currentFragment.setArguments(bundle);
        currentFragment.show(getSupportFragmentManager(), "ChooseTypeFragment");
    }

    public void onCreateEmpty(View view) {
        currentFragment.dismiss();
        exerciseDatas.add(Data.createEmptyExercise());
        exerciseAdapter.notifyItemInserted(exerciseDatas.size() - 1);
        linearLayoutManager.scrollToPosition(exerciseDatas.size() - 1);
    }

    public void onCreatePrevious(View view) {
        boolean allEmpty = true;
        for (WorkoutData workoutData : Data.getWorkoutDatas()) {
            if (!workoutData.getExercises().isEmpty()) {
                allEmpty = false;
                break;
            }
        }

        if (allEmpty) {
            Toast.makeText(this, getString(R.string.no_available, getString(R.string.exercise)), Toast.LENGTH_SHORT).show();
        } else {
            currentFragment.dismiss();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            intent.putExtra(Data.METHOD, "getExercise");
            startActivity(intent);
        }
    }
}