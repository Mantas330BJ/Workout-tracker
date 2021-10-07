package NavigationViewFragments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import Activities.EditWorkoutActivity;
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


public class HistoryFragment extends Fragment implements NestedListenerPasser {
    private static final int NUM_PAGES = 2;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private View view;

    private static boolean firstTime = true;

    protected int layout = R.layout.fragment_workout;
    protected ChooseTypeFragment currentFragment;
    protected WorkoutAdapter workoutAdapter;

    private ArrayList<WorkoutData> workoutDatas;
    private LinearLayoutManager linearLayoutManager;

    protected DoubleClickListener doubleClickListener;
    protected OnLongClickListener onLongClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_workout, container, false);
        if (firstTime) {
            Data.initializeData(requireContext());
            firstTime = false;
        }

        //createNavigationViewListener(view);

        workoutDatas = Data.getWorkoutDatas();
        createAdapter();
        setLongClickListener();

        return view;
    }

    public void createAdapter() {
        RecyclerView table = view.findViewById(R.id.table);

        workoutAdapter = new WorkoutAdapter(workoutDatas, this);
        table.setAdapter(workoutAdapter);

        linearLayoutManager = new LinearLayoutManager(requireContext());
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
                .make(view.findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.workout)), Snackbar.LENGTH_LONG)
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
            Intent intent = new Intent(requireContext(), EditWorkoutActivity.class);
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
        currentFragment.show(((AppCompatActivity)requireContext()).getSupportFragmentManager(), "ChooseTypeFragment");
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
            Toast.makeText(requireContext(), getString(R.string.select_workout), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), getString(R.string.no_available, getString(R.string.workout)), Toast.LENGTH_SHORT).show();
        }
    }
}