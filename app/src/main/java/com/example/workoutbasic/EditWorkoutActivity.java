package com.example.workoutbasic;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditWorkoutActivity extends DatabaseActivity implements OnInputListener {
    private RecyclerView recyclerView;
    private LinearLayout table;
    private LinearLayoutAdapter arrayAdapter; //TODO: Add to parent activity???
    private ChooseTypeFragment currentFragment; //TODO: come on, is this really the best.
    private WorkoutTextView currentClicked; //TODO: Change this
    public int workoutIdx;
    WorkoutLayout workoutLayout;

    private ExerciseData removedExercise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        workoutLayout = new WorkoutLayout(Data.getWorkoutDatas().get(workoutIdx), this, false);
        workoutLayout.getDateTextView().setTextEditListener();


        LinearLayout date = new LinearLayout(this);
        date.setOrientation(LinearLayout.VERTICAL);
        //date.addView(Data.createHeader(this, 0));
        date.addView(workoutLayout.getLayout());

        LinearLayout headers = new LinearLayout(this);
        headers.addView(Data.createColumnNames(this, 1));

        LinearLayout data = findViewById(R.id.data);
        data.addView(date);
        data.addView(headers);

        table = findViewById(R.id.table);
        //table.addView(workoutLayout.getLayout());

        arrayAdapter = new LinearLayoutAdapter(workoutLayout.getWorkoutData().getExercises());

        recyclerView = new RecyclerView(this);
        recyclerView.setAdapter(arrayAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayAdapter.setClickListener(exerciseIdx -> {
            Intent intent = new Intent(this, EditExerciseActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            intent.putExtra(Data.EXERCISE_IDX, exerciseIdx);
            startActivity(intent);
            finish();
        });
        arrayAdapter.setLongClickListener(position -> {
            ArrayList<ExerciseData> exerciseDatas = workoutLayout.getWorkoutData().getExercises();
            removedExercise = exerciseDatas.get(position);
            exerciseDatas.remove(position);
            arrayAdapter.notifyItemRemoved(position);
            arrayAdapter.notifyItemRangeChanged(position, 1);
            Snackbar snackbar = Snackbar
                    .make(headers, getString(R.string.removed, getString(R.string.exercise)), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.undo), view -> {
                        exerciseDatas.add(position, removedExercise);
                        arrayAdapter.notifyItemInserted(position);
                        arrayAdapter.notifyItemRangeChanged(position, 1);
                    });
            snackbar.show();
        });
        table.addView(recyclerView);
    }

    @Override
    public void sendInput(TextViewData input) {
        currentClicked.setText(input.toString());
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCurrentClicked(WorkoutTextView currentClicked) {
        this.currentClicked = currentClicked;
    }

    public void onAddExercise(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(ChooseTypeFragment.PARENT, "exercise"); //TODO: add resource probably
        currentFragment = new ChooseTypeFragment();
        currentFragment.setArguments(bundle);
        currentFragment.show(getSupportFragmentManager(), "ChooseTypeFragment");
        /*
        ArrayList<ExerciseData> exerciseDatas = workoutLayout.getWorkoutData().getExercises();
        ExerciseData exerciseData = Data.copyExercise(exerciseDatas.get(exerciseDatas.size() - 1), 0);
        exerciseDatas.add(exerciseData);
        arrayAdapter.notifyItemInserted(exerciseDatas.size() - 1);
         */
    }

    public void onCreateEmpty(View view) {
        ArrayList<ExerciseData> exerciseDatas = workoutLayout.getWorkoutData().getExercises();
        exerciseDatas.add(Data.createEmptyExercise());
        arrayAdapter.notifyItemInserted(exerciseDatas.size() - 1);
        currentFragment.dismiss();
    }

    public void onCreatePrevious(View view) {
        currentFragment.dismiss();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
        intent.putExtra(Data.METHOD, "getExercise");
        startActivity(intent);
    }
}