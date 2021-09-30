package Activities;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import Fragments.ChooseTypeFragment;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.WorkoutAdapter;
import Datas.WorkoutData;

@RequiresApi(api = Build.VERSION_CODES.O)


public class MainActivity extends DatabaseActivity {
    private static boolean firstTime = true;

    protected int layout = R.layout.activity_main;
    protected ChooseTypeFragment currentFragment;
    protected WorkoutAdapter workoutAdapter;

    private ArrayList<WorkoutData> workoutDatas;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);

        if (firstTime) {
            Data.initializeData(this);
            firstTime = false;
        }

        workoutDatas = Data.getWorkoutDatas();
        createAdapter();
        setLongClickListener();
    }

    public void createAdapter() {
        RecyclerView table = findViewById(R.id.table);

        workoutAdapter = new WorkoutAdapter(workoutDatas);
        table.setAdapter(workoutAdapter);

        linearLayoutManager = new LinearLayoutManager(this);
        makeClickListener();
        scrollLinearLayoutManager(linearLayoutManager);
        table.setLayoutManager(linearLayoutManager);
    }

    public void scrollLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        linearLayoutManager.scrollToPosition(workoutDatas.size() - 1);
    }

    public void makeClickListener() {
        setIntentClickListener();
    }

    public void setLongClickListener() {
        workoutAdapter.setLongClickListener(position -> { //childPosition does not matter
            WorkoutData removedWorkout = workoutDatas.get(position);
            workoutDatas.remove(position);
            workoutAdapter.notifyItemRemoved(position);
            workoutAdapter.notifyItemRangeChanged(position, workoutDatas.size() - position);
            createUndoSnackbar(position, removedWorkout);
        });
    }

    public void createUndoSnackbar(int position, WorkoutData removedWorkout) {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.workout)), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.undo), view -> {
                    workoutDatas.add(position, removedWorkout);
                    workoutAdapter.notifyItemInserted(position);
                    workoutAdapter.notifyItemRangeChanged(position, workoutDatas.size() - position);
                    linearLayoutManager.scrollToPosition(position);
                });
        snackbar.show();
    }

    public void onAddWorkout(View view) {
        currentFragment = new ChooseTypeFragment(getString(R.string.workout));
        currentFragment.show(getSupportFragmentManager(), "ChooseTypeFragment");
    }

    public void setIntentClickListener() {
        workoutAdapter.setDoubleClickListener(position -> childPos -> {
            Intent intent = new Intent(this, EditWorkoutActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, position);
            if (childPos != -1)
                intent.putExtra(Data.EXERCISE_IDX, childPos);
            startActivity(intent);
        });
    }

    public void onCreateEmpty(View view) {
        workoutDatas.add(Data.createEmptyWorkout());
        setIntentClickListener();
        workoutAdapter.notifyItemInserted(workoutDatas.size() - 1);
        linearLayoutManager.scrollToPosition(workoutDatas.size() - 1);
        currentFragment.dismiss();
    }

    public void setDoubleClickListener() {
        workoutAdapter.setDoubleClickListener(position -> childPosition -> {
            ArrayList<WorkoutData> workoutDatas = Data.getWorkoutDatas();
            WorkoutData workoutData = Data.copyWorkout(workoutDatas.get(position));
            workoutDatas.add(workoutData);
            workoutAdapter.notifyItemInserted(workoutDatas.size() - 1);
            linearLayoutManager.scrollToPosition(workoutDatas.size() - 1);
            setIntentClickListener();
        });
    }

    public void onCreatePrevious(View view) {
        if (!workoutDatas.isEmpty()) {
            currentFragment.dismiss();
            Toast.makeText(this, getString(R.string.select_workout), Toast.LENGTH_SHORT).show();

            workoutAdapter.notifyItemRangeChanged(0, workoutAdapter.getItemCount()); //Notifies listeners
            setDoubleClickListener();
        } else {
            Toast.makeText(this, getString(R.string.no_available, getString(R.string.workout)), Toast.LENGTH_SHORT).show();
        }
    }
}