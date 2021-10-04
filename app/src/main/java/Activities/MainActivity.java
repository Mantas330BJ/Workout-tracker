package Activities;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import Fragments.ChooseTypeFragment;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.WorkoutAdapter;
import Datas.WorkoutData;
import Interfaces.DoubleClickListener;
import Interfaces.NestedListenerPasser;
import Interfaces.OnLongClickListener;

@RequiresApi(api = Build.VERSION_CODES.O)


public class MainActivity extends DatabaseActivity implements NestedListenerPasser {
    private static boolean firstTime = true;

    protected int layout = R.layout.activity_main;
    protected ChooseTypeFragment currentFragment;
    protected WorkoutAdapter workoutAdapter;

    private ArrayList<WorkoutData> workoutDatas;
    private LinearLayoutManager linearLayoutManager;

    protected DoubleClickListener doubleClickListener;
    protected OnLongClickListener onLongClickListener;

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

        workoutAdapter = new WorkoutAdapter(workoutDatas, this);
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

    public void onCreateEmpty(View view) {
        workoutDatas.add(Data.createEmptyWorkout());
        setIntentClickListener();
        workoutAdapter.notifyItemInserted(workoutDatas.size() - 1);
        linearLayoutManager.scrollToPosition(workoutDatas.size() - 1);
        currentFragment.dismiss();
    }

    public void onCreatePrevious(View view) {
        if (!workoutDatas.isEmpty()) {
            currentFragment.dismiss();
            setDoubleClickListener();
            Toast.makeText(this, getString(R.string.select_workout), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.no_available, getString(R.string.workout)), Toast.LENGTH_SHORT).show();
        }
    }

    public void setIntentClickListener() {
        doubleClickListener = position -> childPos -> {
            Intent intent = new Intent(this, EditWorkoutActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, position);
            if (childPos != -1)
                intent.putExtra(Data.EXERCISE_IDX, childPos);
            startActivity(intent);
        };
    }


    public void setDoubleClickListener() {
        doubleClickListener = position -> childPosition -> {
            ArrayList<WorkoutData> workoutDatas = Data.getWorkoutDatas();
            WorkoutData workoutData = Data.copyWorkout(workoutDatas.get(position));
            workoutDatas.add(workoutData);
            workoutAdapter.notifyItemInserted(workoutDatas.size() - 1);
            linearLayoutManager.scrollToPosition(workoutDatas.size() - 1);
            setIntentClickListener();
        };
    }

    public void setLongClickListener() {
        onLongClickListener = position -> {
            WorkoutData removedWorkout = workoutDatas.get(position);
            workoutDatas.remove(position);
            workoutAdapter.notifyItemRemoved(position);
            workoutAdapter.notifyItemRangeChanged(position, workoutDatas.size() - position);
            createUndoSnackbar(position, removedWorkout);
        };
    }

    @Override
    public DoubleClickListener getDoubleClickListener() {
        return doubleClickListener;
    }

    @Override
    public OnLongClickListener getOnLongClickListener() {
        return onLongClickListener;
    }
}