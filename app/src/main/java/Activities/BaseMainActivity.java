package Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;

import java.util.ArrayList;

import Datas.ExerciseData;
import Fragments.ConfirmExerciseFragment;
import Interfaces.CopyExercise;
import Interfaces.ExerciseConfirmer;
import NavigationViewFragments.HistoryFragment;


@RequiresApi(api = Build.VERSION_CODES.O)

class BaseMainActivity extends DatabaseActivity {

    public class CopyExerciseActivity extends HistoryFragment implements ExerciseConfirmer {
        private int workoutIdx; //Workout index from which exercise is being copied.

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_copy_exercise);

            workoutIdx = getIntent().getIntExtra(Data.WORKOUT_IDX, -1);
        }

        @Override
        public void scrollLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
            linearLayoutManager.scrollToPosition(workoutIdx);
        }

        @Override
        public void makeClickListener() {
            Toast toast = Toast.makeText(requireContext(), getString(R.string.select_exercise), Toast.LENGTH_SHORT);
            toast.show();
            setAdapterDoubleClickListener();
        }

        @Override
        public void confirmExercise(ExerciseData exerciseData) {
            Intent intent = new Intent(requireContext(), EditWorkoutActivity.class);
            intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
            ArrayList<ExerciseData> destinationDatas = Data.getWorkoutDatas().get(workoutIdx).getExercises();
            ExerciseData copiedExercise = Data.copyExercise(exerciseData);
            destinationDatas.add(copiedExercise);
            startActivity(intent);
        }

        public void setAdapterDoubleClickListener() {
            doubleClickListener = position -> childPos -> {
                if (childPos != -1) { //Not header clicked
                    ExerciseData exerciseData = Data.getWorkoutDatas().get(position).getExercises().get(childPos);
                    ConfirmExerciseFragment popup = new ConfirmExerciseFragment(exerciseData);
                    popup.show(getSupportFragmentManager(), "ConfirmExerciseFragment");
                    getSupportFragmentManager().executePendingTransactions();
                }
            };
        }
    }
}


