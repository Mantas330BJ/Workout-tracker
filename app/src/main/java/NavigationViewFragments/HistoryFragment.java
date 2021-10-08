package NavigationViewFragments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import Activities.EditWorkoutActivity;
import Fragments.ChooseTypeFragment;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.WorkoutAdapter;
import Datas.WorkoutData;
import Interfaces.ButtonOptions;
import Interfaces.CopyExercise;
import Interfaces.DoubleClickListener;
import Interfaces.NestedListenerPasser;
import Interfaces.OnLongClickListener;

@RequiresApi(api = Build.VERSION_CODES.O)


public class HistoryFragment extends Fragment {
    private static boolean firstTime = true;

    public static class BaseCopyExerciseProvider implements NestedListenerPasser, ButtonOptions, CopyExercise {
        protected Context context;
        protected View view;

        private ArrayList<WorkoutData> workoutDatas;
        private LinearLayoutManager linearLayoutManager;
        private ChooseTypeFragment currentFragment;

        protected WorkoutAdapter workoutAdapter;
        protected DoubleClickListener doubleClickListener;
        protected OnLongClickListener onLongClickListener;

        public BaseCopyExerciseProvider(Context context, View view) {
            this.context = context;
            this.view = view;
        }


        public void createAdapter() {
            RecyclerView table = view.findViewById(R.id.table);

            workoutAdapter = new WorkoutAdapter(workoutDatas, this);
            table.setAdapter(workoutAdapter);

            linearLayoutManager = new LinearLayoutManager(context);
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
                    .make(((AppCompatActivity)context).findViewById(android.R.id.content), context.getString(R.string.removed, context.getString(R.string.workout)), Snackbar.LENGTH_LONG)
                    .setAction(context.getString(R.string.undo), view -> {
                        workoutDatas.add(position, removedWorkout);
                        workoutAdapter.notifyItemInserted(position);
                        workoutAdapter.notifyItemRangeChanged(position, workoutDatas.size() - position);
                        linearLayoutManager.scrollToPosition(position);
                    });
            snackbar.show();
        }

        public void setIntentClickListener() {
            doubleClickListener = position -> childPos -> {
                Intent intent = new Intent(context, EditWorkoutActivity.class);
                intent.putExtra(Data.WORKOUT_IDX, position);
                if (childPos != -1)
                    intent.putExtra(Data.EXERCISE_IDX, childPos);
                context.startActivity(intent);
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

        public void setWorkoutButtonListener() {
            Button workoutButton = view.findViewById(R.id.workout_button);
            workoutButton.setOnClickListener(v -> {
                currentFragment = new ChooseTypeFragment(context.getString(R.string.workout), this);
                currentFragment.show(((AppCompatActivity)context).getSupportFragmentManager(), "ChooseTypeFragment");
            });
        }

        @Override
        public View.OnClickListener onCreateEmpty() {
            return v -> {
                workoutDatas.add(Data.createEmptyWorkout());
                setIntentClickListener();
                workoutAdapter.notifyItemInserted(workoutDatas.size() - 1);
                linearLayoutManager.scrollToPosition(workoutDatas.size() - 1);
                currentFragment.dismiss();
            };
        }

        @Override
        public View.OnClickListener onCreatePrevious() {
            return v -> {
                if (!workoutDatas.isEmpty()) {
                    currentFragment.dismiss();
                    setDoubleClickListener();
                    Toast.makeText(context, context.getString(R.string.select_workout), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, context.getString(R.string.no_available, context.getString(R.string.workout)), Toast.LENGTH_SHORT).show();
                }
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

        public void onCreateView() {
            workoutDatas = Data.getWorkoutDatas();
            createAdapter();
            setLongClickListener();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        BaseCopyExerciseProvider provider = new BaseCopyExerciseProvider(requireContext(), view);

        if (firstTime) {
            Data.initializeData(requireContext());
            firstTime = false;
        }

        provider.onCreateView();
        provider.setWorkoutButtonListener();

        return view;
    }
}