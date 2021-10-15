package Activities.Sets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.Sets.SetAdapter;
import Adapters.Sets.SetListenerReadAdapter;
import DataEdit.ImageViews.WorkoutFileView;
import DataEdit.TextViews.StringTextView;
import Datas.ExerciseData;
import Datas.SetData;
import Variables.IntPasser;
import Variables.UriPasser;

@RequiresApi(api = Build.VERSION_CODES.O)

public class EditExerciseFragment extends Fragment {
    private SetListenerReadAdapter setAdapter;
    private ArrayList<SetData> setDatas;

    private int workoutIdx;
    private int exerciseIdx;

    private LinearLayoutManager linearLayoutManager;
    private UriPasser fileUri;


    private View view;
    private Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_edit_exercise, container, false);
        context = requireContext();

        assert getArguments() != null;
        workoutIdx = getArguments().getInt(Data.WORKOUT_IDX);
        exerciseIdx = getArguments().getInt(Data.EXERCISE_IDX);
        ExerciseData exerciseData = Data.getWorkoutDatas().get(workoutIdx).getExercises().get(exerciseIdx);

        setExercise(exerciseData);

        setDatas = exerciseData.getSets();
        linearLayoutManager = new LinearLayoutManager(context);
        scrollScreen();

        setAdapter = new SetAdapter(exerciseData.getSets());
        createRecyclerView();

        setLongClickListener();


        return view;
    }

    public void createRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(setAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void scrollScreen() {
        assert getArguments() != null;
        int scrollPosition = getArguments().getInt(Data.SET_IDX);
        linearLayoutManager.scrollToPosition(scrollPosition == -1 ? setDatas.size() - 1 : scrollPosition);
    }

    public void setExercise(ExerciseData exerciseData) {
        StringTextView exerciseName = view.findViewById(R.id.exercise);
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
                .make(((AppCompatActivity)context).findViewById(android.R.id.content), getString(R.string.removed, getString(R.string.set)), Snackbar.LENGTH_LONG)
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

    /*
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
     */

    public void requestPermissions ( @NonNull String[] permissions, int requestCode, UriPasser parentData) {
        fileUri = parentData;
        ActivityCompat.requestPermissions(requireActivity(), permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WorkoutFileView.REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    WorkoutFileView.showFileOptions(context, fileUri);
                } else {
                    Toast.makeText(context, "Storage permission denied.", Toast.LENGTH_SHORT).show();
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