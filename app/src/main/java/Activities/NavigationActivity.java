package Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import Fragments.ChooseTypeFragment;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.WorkoutAdapter;
import Datas.WorkoutData;
import Interfaces.DoubleClickListener;
import Interfaces.NestedListenerPasser;
import Interfaces.OnLongClickListener;
import NavigationViewFragments.ExercisesFragment;
import NavigationViewFragments.HistoryFragment;

@RequiresApi(api = Build.VERSION_CODES.O)


public class NavigationActivity extends AppCompatActivity {
    final Fragment historyFragment = new HistoryFragment();
    final Fragment exercisesFragment = new ExercisesFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = historyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        createNavigationViewListener();

        fm.beginTransaction().add(R.id.main_container, historyFragment, "1").commit();
        fm.beginTransaction().add(R.id.main_container, exercisesFragment, "2")
                .hide(exercisesFragment).commit();
    }

    public void createNavigationViewListener() {
        String className = this.getClass().getName();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.history_page && !className.equals(NavigationActivity.class.getName())) {
                Intent intent = new Intent(this, NavigationActivity.class);
                startActivity(intent);
            } else if (id == R.id.exercises_page && !className.equals(ExerciseActivity.class.getName())) {
                Intent intent = new Intent(this, ExerciseActivity.class);
                startActivity(intent);
            }
            return false;
        });
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
}