package Pages.Workouts;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;

import Pages.NavigationViewPages.HistoryFragment;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CopyExerciseFragment extends HistoryFragment {
    private int workoutIdx; //Workout index from which exercise is being copied.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_copy_exercise, container, false);
        context = requireContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        workoutIdx = getArguments().getInt(Data.WORKOUT_IDX);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        initializeView();
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
                Bundle bundle = new Bundle();
                bundle.putInt(Data.WORKOUT_IDX, position);
                bundle.putInt(Data.EXERCISE_IDX, childPos);
                bundle.putInt(Data.DEST_WORKOUT_IDX, workoutIdx);

                navController.navigate(R.id.action_copyExerciseFragment_to_confirmExerciseFragment, bundle);
            }
        };
    }
}
