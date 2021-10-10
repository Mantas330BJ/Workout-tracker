package Activities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;

import Datas.ExerciseData;
import Fragments.ConfirmExerciseFragment;
import NavigationViewFragments.HistoryFragment;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CopyExerciseFragment extends HistoryFragment {
    private int workoutIdx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_copy_exercise, container, false);
        context = requireContext();
        initializeView();
        return view;
    }

    public CopyExerciseFragment(int workoutIdx) {
        this.workoutIdx = workoutIdx;
    }

    @Override
    public void scrollLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        linearLayoutManager.scrollToPosition(workoutIdx);
    }

    @Override
    public void makeClickListener() {
        Toast toast = Toast.makeText(context, context.getString(R.string.select_exercise), Toast.LENGTH_SHORT);
        toast.show();

        doubleClickListener = position -> childPos -> {
            if (childPos != -1) { //Not header clicked
                ExerciseData exerciseData = Data.getWorkoutDatas().get(position).getExercises().get(childPos);
                ConfirmExerciseFragment popup = new ConfirmExerciseFragment(exerciseData);
                popup.show(((AppCompatActivity)context).getSupportFragmentManager(), "ConfirmExerciseFragment");
                ((AppCompatActivity)context).getSupportFragmentManager().executePendingTransactions();
            }
        };
    }

}
