package Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
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
import com.example.workoutbasic.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.SetAdapter;
import Adapters.SetListenerReadAdapter;
import ImageViews.WorkoutFileView;
import TextViews.StringTextView;
import Datas.ExerciseData;
import Datas.SetData;
import Variables.IntPasser;
import Variables.UriPasser;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseActivity extends InputListenerActivity {
    private SetListenerReadAdapter setAdapter;
    private ArrayList<SetData> setDatas;

    private int workoutIdx;
    private int exerciseIdx;

    private LinearLayoutManager linearLayoutManager;
    private UriPasser fileUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        exerciseIdx = (int)getIntent().getExtras().get(Data.EXERCISE_IDX);
        ExerciseData exerciseData = Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx);
        setExercise(exerciseData);

        setDatas = exerciseData.getSets();
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
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? setDatas.size() - 1 : scrollPosition);
    }

    public void setExercise(ExerciseData exerciseData) {
        StringTextView exerciseName = findViewById(R.id.exercise);
        exerciseName.setText(exerciseData.getExercise());
        exerciseName.setTextClickListener();
    }

    public void incrementSets(int position, ArrayList<SetData> setDatas) {
        for (int i = position; i < setDatas.size(); ++i) {
            setDatas.get(i).setSet(new IntPasser(i + 1));
        }
    }

    public void createUndoSnackbar(int position, SetData removedSet) {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.set)), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.undo), view -> {
                    setDatas.add(position, removedSet);
                    for (int i = position; i < setDatas.size(); ++i) {
                        setDatas.get(i).setSet(new IntPasser(i + 1));
                    }
                    linearLayoutManager.scrollToPosition(position);
                    setAdapter.notifyItemInserted(position);
                    setAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
                });
        snackbar.show();
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

    public void requestPermissions ( @NonNull String[] permissions, int requestCode, UriPasser parentData) {
        fileUri = parentData;
        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WorkoutFileView.REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    WorkoutFileView.showFileOptions(this, fileUri);
                } else {
                    Toast.makeText(this, "Storage permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void setLongClickListener() {
        setAdapter.setLongClickListener(position -> {
            SetData removedSet = setDatas.get(position);
            setDatas.remove(position);
            incrementSets(position, setDatas);

            setAdapter.notifyItemRemoved(position);
            setAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
            createUndoSnackbar(position, removedSet);
        });
    }

    public void onAddSet(View view) {
        if (!setDatas.isEmpty()) {
            SetData setData = Data.copySet(setDatas.get(setDatas.size() - 1));
            setData.setSet(new IntPasser(setData.getSet().getVal() + 1));
            setDatas.add(setData);
        } else {
            setDatas.add(Data.createEmptySet());
        }
        setAdapter.notifyItemInserted(setDatas.size() - 1);
        linearLayoutManager.scrollToPosition(setDatas.size() - 1);
    }
}