package Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.workoutbasic.BuildConfig;
import com.example.workoutbasic.Data;
import com.example.workoutbasic.Exercise;
import com.example.workoutbasic.OnInputListener;
import com.example.workoutbasic.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import Adapters.SetAdapter;
import CustomViews.WorkoutFileView;
import CustomViews.WorkoutTextView;
import Datas.ExerciseData;
import Datas.SetData;
import Fragments.ChooseFileOptionsFragment;
import Interfaces.OnSuccessfulFileRead;
import Interfaces.WorkoutInput;
import Variables.Int;
import Variables.TextViewData;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseActivity extends DatabaseActivity implements OnInputListener, OnSuccessfulFileRead {
    private SetAdapter setAdapter;
    private Exercise exercise;

    private WorkoutInput currentClicked;
    int workoutIdx;
    int exerciseIdx;

    private SetData removedSet;
    private LinearLayoutManager linearLayoutManager;

    private ActivityResultLauncher<Intent> mediaPickerLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);

        workoutIdx = (int)getIntent().getExtras().get(Data.WORKOUT_IDX);
        exerciseIdx = (int)getIntent().getExtras().get(Data.EXERCISE_IDX);
        ExerciseData exerciseData = Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx);
        WorkoutTextView exerciseName = findViewById(R.id.exercise);
        exerciseName.setText(exerciseData.getExercise());
        exerciseName.setTextEditListener();

        exercise = new Exercise(exerciseData, this);
        exercise.getExerciseTextView().setTextEditListener();

        setAdapter = new SetAdapter(exerciseData.getSets(), true);
        linearLayoutManager = new LinearLayoutManager(this);
        int scrollPosition = getIntent().getIntExtra(Data.SET_IDX, -1);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? exercise.getExerciseData().getSets().size() - 1 : scrollPosition);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(setAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        setAdapterLongClickListener();

        createLauncher();
    }

    public void createLauncher() {
        mediaPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        Uri uri = data.getData();
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        intent.setDataAndType(uri, "video/*");
                        intent.setFlags(data.getFlags());
                        startActivity(intent);
                    }
                });
    }

    public void setAdapterLongClickListener() {
        setAdapter.setLongClickListener(position -> {
            ArrayList<SetData> setDatas = exercise.getExerciseData().getSets();
            removedSet = setDatas.get(position);
            setDatas.remove(position);
            for (int i = position; i < setDatas.size(); ++i) {
                setDatas.get(i).setSet(new Int(i + 1));
            }
            setAdapter.notifyItemRemoved(position);
            setAdapter.notifyItemRangeChanged(position, setDatas.size() - position);
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
        });
    }

    @Override
    public void sendInput(TextViewData input) {
        currentClicked.setText(input.toString());
    }

    @Override
    public void setCurrentClicked(WorkoutInput currentClicked) {
        this.currentClicked = currentClicked;
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
        ArrayList<SetData> setDatas = exercise.getExerciseData().getSets();
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
                    showFileOptions();
                } else {
                    Toast.makeText(this, "Storage permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void showFileOptions() {
        ChooseFileOptionsFragment chooseFileOptionsFragment = new ChooseFileOptionsFragment();
        chooseFileOptionsFragment.show(getSupportFragmentManager(), "ChooseFileOptionsFragment");
    }

    public void onSelectMedia(View view) {
        Intent pickIntent = new Intent(Intent.ACTION_PICK);
        pickIntent.setType("video/*");
        mediaPickerLauncher.launch(pickIntent);
    }

    public void onPlayMedia(View view) {

    }

}