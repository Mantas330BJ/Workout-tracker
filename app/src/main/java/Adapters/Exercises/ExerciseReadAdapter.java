package Adapters.Exercises;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Adapters.BindingViewHolder;
import Adapters.Sets.SetReadAdapter;
import Datas.ExerciseData;
import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseBinding;
import com.example.workoutbasic.databinding.ExerciseInfoBinding;
import com.example.workoutbasic.databinding.ExerciseTextsBinding;

import Datas.SetData;
import DataEdit.TextViews.WorkoutTextView;

import java.util.ArrayList;
@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseReadAdapter extends RecyclerView.Adapter<BindingViewHolder<ExerciseBinding>> {
    protected final ArrayList<ExerciseData> listData;

    public ExerciseReadAdapter(ArrayList<ExerciseData> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public BindingViewHolder<ExerciseBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExerciseBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.exercise, parent, false);
        return new BindingViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ExerciseBinding> holder, int position) {
        final ExerciseData myListData = listData.get(position);
        WorkoutTextView exerciseName = holder.binding.textsLayout.exercise;
        exerciseName.setText(myListData.getExercise());

        RecyclerView recyclerView = holder.binding.recyclerView;
        createSetAdapter(recyclerView, myListData.getSets());
    }

    public void createSetAdapter(RecyclerView recyclerView, ArrayList<SetData> setData) {
        SetReadAdapter setAdapter = new SetReadAdapter(setData);
        recyclerView.setAdapter(setAdapter);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
