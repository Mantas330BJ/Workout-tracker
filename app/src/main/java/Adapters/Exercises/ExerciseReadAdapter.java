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
import Adapters.Sets.SetListenerReadAdapter;
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

public class ExerciseReadAdapter extends RecyclerView.Adapter<ExerciseReadAdapter.ViewHolder> {
    protected final ArrayList<ExerciseData> listData;

    public ExerciseReadAdapter(ArrayList<ExerciseData> listData) {
        this.listData = listData;
    }

    public static class ViewHolder extends BindingViewHolder<ExerciseBinding> {
        public ViewHolder(ExerciseBinding binding) {
            super(binding);
        }

        public void bind(ExerciseData exerciseData) {
            binding.setData(exerciseData);
            binding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExerciseBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.exercise, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ExerciseData exerciseData = listData.get(position);
        holder.bind(exerciseData);

        RecyclerView recyclerView = holder.binding.recyclerView;
        createSetAdapter(recyclerView, exerciseData.getSets());

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
