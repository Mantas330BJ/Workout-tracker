package com.example.workoutbasic.pages.workouts;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.interfaces.ButtonOptions;
import com.example.workoutbasic.interfaces.listeners.BiIntConsumer;
import com.example.workoutbasic.interfaces.listeners.NestedListenerPasser;
import com.example.workoutbasic.interfaces.listeners.PositionLongClickListener;
import com.example.workoutbasic.models.Workout;
import com.example.workoutbasic.pages.NavigationFragment;
import com.example.workoutbasic.pages.dialogs.ChooseTypeFragment;
import com.example.workoutbasic.utils.DataRetriever;
import com.example.workoutbasic.viewadapters.workouts.WorkoutAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)


public class HistoryFragment extends NavigationFragment implements NestedListenerPasser, ButtonOptions {
    private static boolean firstTime = true;

    private List<Workout> workouts;
    private Button workoutButton;

    protected LinearLayoutManager linearLayoutManager;
    protected WorkoutAdapter workoutAdapter;
    protected BiIntConsumer biIntConsumer;
    protected PositionLongClickListener positionLongClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (firstTime) {
            DataRetriever.initializeData(requireContext());
            firstTime = false;
        }

        workoutButton = view.findViewById(R.id.workout_button);
        initializeView(view, savedInstanceState);
    }

    protected void initializeView(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        workouts = DataRetriever.getWorkoutDatas();
        RecyclerView table = view.findViewById(R.id.table);

        workoutAdapter = new WorkoutAdapter(workouts, this);
        table.setAdapter(workoutAdapter);

        linearLayoutManager = (LinearLayoutManager)table.getLayoutManager();
        assert linearLayoutManager != null;
        linearLayoutManager.scrollToPosition(workouts.size() - 1);
        setListeners();
    }

    public void setListeners() {
        biIntConsumer = this::createIntentClickListener;
        positionLongClickListener = this::createLongClickListener;
        workoutButton.setOnClickListener(this::createWorkoutButtonListener);
    }

    public void createUndoSnackbar(int position, Workout removedWorkout) {
        Snackbar snackbar = Snackbar
                .make(((AppCompatActivity)context).findViewById(android.R.id.content), context.getString(R.string.removed, context.getString(R.string.workout)), Snackbar.LENGTH_LONG)
                .setAction(context.getString(R.string.undo), view -> {
                    workouts.add(position, removedWorkout);
                    workoutAdapter.notifyItemInserted(position);
                    workoutAdapter.notifyItemRangeChanged(position, workouts.size() - position);
                    linearLayoutManager.scrollToPosition(position);
                });
        snackbar.show();
    }

    private void createIntentClickListener(int workoutPos, int exercisePos, View view) {
        Bundle bundle = new Bundle();
        bundle.putInt(DataRetriever.WORKOUT_IDX, workoutPos);
        bundle.putInt(DataRetriever.EXERCISE_IDX, exercisePos); //-1 if headers

        navController.navigate(R.id.action_historyFragment_to_editWorkoutFragment, bundle);
    }

    protected void createDoubleClickListener(int workoutPos, int exercisePos, View view) {
        Workout workout = DataRetriever.copyWorkout(workouts.get(workoutPos));
        workouts.add(workout);
        workoutAdapter.notifyItemInserted(workouts.size() - 1);
        linearLayoutManager.scrollToPosition(workouts.size() - 1);
        biIntConsumer = this::createIntentClickListener;
    }

    protected void createLongClickListener(int position) {
        Workout removedWorkout = workouts.get(position);
        workouts.remove(position);
        workoutAdapter.notifyItemRemoved(position);
        workoutAdapter.notifyItemRangeChanged(position, workouts.size() - position);
        createUndoSnackbar(position, removedWorkout);
    }

    private void createWorkoutButtonListener(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(ChooseTypeFragment.NAME_KEY, context.getString(R.string.workout));
        navController.navigate(R.id.action_historyFragment_to_chooseTypeFragment, bundle);
    }

    @Override
    public BiIntConsumer getDoubleClickListener() {
        return biIntConsumer;
    }

    @Override
    public PositionLongClickListener getOnLongClickListener() {
        return positionLongClickListener;
    }

    @Override
    public void onCreateEmpty(DialogFragment dialogFragment) {
        workouts.add(DataRetriever.createEmptyWorkout());
        biIntConsumer = this::createIntentClickListener;
        workoutAdapter.notifyItemInserted(workouts.size() - 1);
        linearLayoutManager.scrollToPosition(workouts.size() - 1);
        dialogFragment.dismiss();
    }

    @Override
    public void onCreatePrevious(DialogFragment dialogFragment) {
        if (!workouts.isEmpty()) {
            dialogFragment.dismiss();
            biIntConsumer = this::createDoubleClickListener;
            Toast.makeText(context, context.getString(R.string.select_workout), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getString(R.string.no_available, context.getString(R.string.workout)), Toast.LENGTH_SHORT).show();
        }
    }
}