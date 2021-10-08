package Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;

import java.util.ArrayList;

import Datas.ExerciseData;
import Fragments.ConfirmExerciseFragment;
import Interfaces.ExerciseConfirmer;
import NavigationViewFragments.HistoryFragment;


@RequiresApi(api = Build.VERSION_CODES.O)

public class CopyExerciseActivity extends DatabaseActivity implements ExerciseConfirmer {
    private int workoutIdx; //Workout index from which exercise is being copied.

    public class CopyExerciseProvider extends HistoryFragment.BaseCopyExerciseProvider {

        public CopyExerciseProvider(Context context, View view) {
            super(context, view);
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_exercise);
        workoutIdx = getIntent().getIntExtra(Data.WORKOUT_IDX, -1);
        CopyExerciseProvider provider = new CopyExerciseProvider(this, getWindow().getDecorView().getRootView());
        provider.onCreateView();
    }

    @Override
    public void confirmExercise(ExerciseData exerciseData) {
        Intent intent = new Intent(this, EditWorkoutActivity.class);
        intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
        ArrayList<ExerciseData> destinationDatas = Data.getWorkoutDatas().get(workoutIdx).getExercises();
        ExerciseData copiedExercise = Data.copyExercise(exerciseData);
        destinationDatas.add(copiedExercise);
        startActivity(intent);
    }
}


