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
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.Constants;
import com.example.workoutbasic.R;
import com.example.workoutbasic.helper.ItemModifier;
import com.example.workoutbasic.interfaces.ButtonOptions;
import com.example.workoutbasic.interfaces.listeners.Listenable;
import com.example.workoutbasic.models.Workout;
import com.example.workoutbasic.pages.NavigationFragment;
import com.example.workoutbasic.pages.dialogs.ChooseTypeFragment;
import com.example.workoutbasic.utils.DataRetriever;
import com.example.workoutbasic.viewadapters.workouts.WorkoutAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)


public class HistoryFragment extends NavigationFragment implements Listenable, ButtonOptions {
    private static boolean firstTime = true;

    private List<Workout> workouts;
    private Button workoutButton;

    protected View.OnClickListener biIntConsumer;
    protected View.OnLongClickListener positionLongClickListener;
    private ItemModifier<Workout> modifier;
    protected Bundle adapterParams;

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

        WorkoutAdapter workoutAdapter = new WorkoutAdapter(workouts, this);
        table.setAdapter(workoutAdapter);

        RecyclerView.LayoutManager linearLayoutManager = table.getLayoutManager();
        assert linearLayoutManager != null;
        linearLayoutManager.scrollToPosition(getInitialScrollPosition());
        setListeners();
        modifier = new ItemModifier<>(workouts, workoutAdapter, linearLayoutManager);
        adapterParams = new Bundle();
    }

    protected int getInitialScrollPosition() {
        return workouts.size() - 1;
    }

    public void setListeners() {
        biIntConsumer = this::createIntentClickListener;
        positionLongClickListener = this::removeWorkoutWithUndo;
        workoutButton.setOnClickListener(this::createWorkoutButtonListener);
    }

    private void createIntentClickListener(View view) {
        navController.navigate(R.id.action_historyFragment_to_editWorkoutFragment, adapterParams);
    }

    protected void addCopiedWorkout(View view) {
        int workoutPos = adapterParams.getInt(Constants.WORKOUT_IDX);
        Workout workout = new Workout(workouts.get(workoutPos));
        modifier.addItem(workouts.size(), workout);
        biIntConsumer = this::createIntentClickListener;
    }

    protected boolean removeWorkoutWithUndo(View view) {
        int workoutPos = adapterParams.getInt(Constants.WORKOUT_IDX);
        Workout removedWorkout = modifier.removeItem(workoutPos);
        String removedWorkoutText = context.getString(R.string.removed, context.getString(R.string.workout));
        Snackbar.make(((AppCompatActivity)context).findViewById(android.R.id.content), removedWorkoutText, Snackbar.LENGTH_LONG)
            .setAction(context.getString(R.string.undo), v -> modifier.addItem(workoutPos, removedWorkout))
            .show();
        return true;
    }

    private void createWorkoutButtonListener(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(ChooseTypeFragment.NAME_KEY, context.getString(R.string.workout));
        navController.navigate(R.id.action_historyFragment_to_chooseTypeFragment, bundle);
    }

    @Override
    public View.OnClickListener getOnClickListener() {
        return biIntConsumer;
    }

    @Override
    public View.OnLongClickListener getOnLongClickListener() {
        return positionLongClickListener;
    }

    @Override
    public void onCreateEmpty(DialogFragment dialogFragment) {
        modifier.addItem(workouts.size(), new Workout());
        biIntConsumer = this::createIntentClickListener;
        dialogFragment.dismiss();
    }

    @Override
    public void onCreatePrevious(DialogFragment dialogFragment) {
        if (!workouts.isEmpty()) {
            dialogFragment.dismiss();
            biIntConsumer = this::addCopiedWorkout;
            Toast.makeText(context, context.getString(R.string.select_workout), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getString(R.string.no_available, context.getString(R.string.workout)), Toast.LENGTH_SHORT).show();
        }
    }

    public Bundle getAdapterParams() {
        return adapterParams;
    }
}