package Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.OnInputListener;
import com.example.workoutbasic.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.SetAdapter;
import Adapters.SetReadAdapter;
import ImageViews.WorkoutFileView;
import Interfaces.TextViewInput;
import TextViews.StringTextView;
import Datas.ExerciseData;
import Datas.SetData;
import Variables.Int;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseActivity extends InputListenerActivity {
    private SetReadAdapter setAdapter;
    private ExerciseData exerciseData;

    private int workoutIdx;
    private int exerciseIdx;

    private SetData removedSet;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        getExerciseData();
        setExercise();

        linearLayoutManager = new LinearLayoutManager(this);
        scrollScreen();


        setAdapter = new SetAdapter(exerciseData.getSets());
        createRecyclerView();

        setLongClickListener();
    }

    public void createRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(setAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void scrollScreen() {
        int scrollPosition = getIntent().getIntExtra(Data.SET_IDX, -1);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? exerciseData.getSets().size() - 1 : scrollPosition);
    }

    public void getExerciseData() {
        workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        exerciseIdx = (int)getIntent().getExtras().get(Data.EXERCISE_IDX);
        exerciseData = Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx);
    }

    public void setExercise() {
        StringTextView exerciseName = findViewById(R.id.exercise);
        exerciseName.setText(exerciseData.getExercise());
        exerciseName.setTextClickListener();
    }

    public void incrementSets(int position, ArrayList<SetData> setDatas) {
        for (int i = position; i < setDatas.size(); ++i) {
            setDatas.get(i).setSet(new Int(i + 1));
        }
    }

    public void createUndoSnackbar(int position, ArrayList<SetData> setDatas) {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.set)), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.undo), view -> {
                    setDatas.add(position, removedSet);
                    for (int i = position; i < setDatas.size(); ++i) {
                        setDatas.get(i).setSet(new Int(i + 1));
                    }
                    linearLayoutManager.scrollToPosition(position);
                    setAdapter.notifyItemInserted(position);
                    setAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
                });
        snackbar.show();
    }

    public void setLongClickListener() {
        setAdapter.setLongClickListener(position -> {
            ArrayList<SetData> setDatas = exerciseData.getSets();
            removedSet = setDatas.get(position);
            setDatas.remove(position);
            incrementSets(position, setDatas);

            setAdapter.notifyItemRemoved(position);
            setAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
            createUndoSnackbar(position, setDatas);
        });
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, EditWorkoutActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            intent.putExtra(Data.EXERCISE_IDX, exerciseIdx);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddSet(View view) {
        ArrayList<SetData> setDatas = exerciseData.getSets();
        if (!setDatas.isEmpty()) {
            SetData setData = Data.copySet(setDatas.get(setDatas.size() - 1));
            setData.setSet(new Int(setData.getSet().getVal() + 1));
            setDatas.add(setData);
        } else {
            setDatas.add(Data.createEmptySet());
        }
        setAdapter.notifyItemInserted(setDatas.size() - 1);
        linearLayoutManager.scrollToPosition(setDatas.size() - 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WorkoutFileView.REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //showFileOptions();
                } else {
                    Toast.makeText(this, "Storage permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}