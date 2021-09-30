package Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import Adapters.ExerciseListenerReadAdapter;
import Datas.ExerciseData;
import Interfaces.ExerciseConfirmer;

public class ConfirmExerciseFragment extends DialogFragment {
    private ExerciseData exerciseData;

    public ConfirmExerciseFragment(ExerciseData exerciseData) {
        this.exerciseData = exerciseData;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.confirmation_fragment, container, false);

        Button yesButton = view.findViewById(R.id.yes_button);
        yesButton.setOnClickListener(v -> ((ExerciseConfirmer) requireActivity()).confirmExercise(exerciseData));

        Button noButton = view.findViewById(R.id.no_button);
        noButton.setOnClickListener(v -> dismiss());

        ExerciseListenerReadAdapter exerciseAdapter = new ExerciseListenerReadAdapter(new ArrayList<>(Collections.singletonList(exerciseData)));
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
