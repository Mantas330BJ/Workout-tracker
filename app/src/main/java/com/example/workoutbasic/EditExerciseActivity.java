package com.example.workoutbasic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class EditExerciseActivity extends DatabaseActivity implements OnInputListener {
    private RecyclerView recyclerView;
    private WorkoutLinearLayout table;
    private SetAdapter arrayAdapter;

    private WorkoutTextView currentClicked;
    private Exercise exerciseLayout;
    int workoutIdx;
    int exerciseIdx;

    private SetData removedSet;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        exerciseIdx = (int)getIntent().getExtras().get(Data.EXERCISE_IDX);
        exerciseLayout = new Exercise(Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx), this);
        exerciseLayout.getExerciseTextView().setTextEditListener();
        exerciseLayout.getExerciseTextView().setTextAppearance(this, android.R.style.TextAppearance_Large);


        WorkoutLinearLayout exercise = new WorkoutLinearLayout(this);
        exercise.setOrientation(LinearLayout.VERTICAL);
        exercise.addView(exerciseLayout.getLayout());

        WorkoutLinearLayout headers = new WorkoutLinearLayout(this);
        headers.addView(Data.createColumnNames(this, 2));

        WorkoutLinearLayout data = findViewById(R.id.data);
        data.addView(exercise);
        data.addView(headers);


        table = findViewById(R.id.table);
        arrayAdapter = new SetAdapter(exerciseLayout.getExerciseData().getSets());


        recyclerView = new RecyclerView(this);
        recyclerView.setAdapter(arrayAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.scrollToPosition(exerciseLayout.getExerciseData().getSets().size() - 1);
        recyclerView.setLayoutManager(linearLayoutManager);
        table.addView(recyclerView);

        setAdapterLongClickListener();
    }

    public void setAdapterLongClickListener() {
        arrayAdapter.setLongClickListener(position -> {
            ArrayList<SetData> setDatas = exerciseLayout.getExerciseData().getSets();
            removedSet = setDatas.get(position);
            setDatas.remove(position);
            for (int i = position; i < setDatas.size(); ++i) {
                setDatas.get(i).setSet(new Int(i + 1));
            }
            arrayAdapter.notifyItemRemoved(position);
            arrayAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
            Snackbar snackbar = Snackbar
                    .make(table, getString(R.string.removed, getString(R.string.set)), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.undo), view -> {
                        setDatas.add(position, removedSet);
                        for (int i = position; i < setDatas.size(); ++i) {
                            setDatas.get(i).setSet(new Int(i + 1));
                        }
                        linearLayoutManager.scrollToPosition(position);
                        arrayAdapter.notifyItemInserted(position);
                        arrayAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
                    });
            snackbar.show();
        });
    }

    @Override
    public void sendInput(TextViewData input) {
        currentClicked.setText(input.toString());
    }

    @Override
    public void setCurrentClicked(WorkoutTextView currentClicked) {
        this.currentClicked = currentClicked;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, EditWorkoutActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddSet(View view) {
        ArrayList<SetData> setDatas = exerciseLayout.getExerciseData().getSets();
        if (!setDatas.isEmpty()) {
            SetData setData = Data.copySet(setDatas.get(setDatas.size() - 1), 1);
            setDatas.add(setData);
        } else {
            setDatas.add(Data.createEmptySet());
        }
        arrayAdapter.notifyItemInserted(setDatas.size() - 1);
        linearLayoutManager.scrollToPosition(setDatas.size() - 1);
    }


}