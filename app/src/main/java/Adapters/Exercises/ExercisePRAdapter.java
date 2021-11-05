package Adapters.Exercises;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseBinding;
import com.example.workoutbasic.databinding.ExerciseprBinding;

import java.util.ArrayList;

import Adapters.BindingViewHolder;
import Datas.ExercisePRData;
import Interfaces.Listeners.OnClickListener;

public class ExercisePRAdapter extends RecyclerView.Adapter<ExercisePRAdapter.ViewHolder> {
    private ArrayList<ExercisePRData> exercisePRData;
    private OnClickListener onClickListener;

    public static class ViewHolder extends BindingViewHolder<ExerciseprBinding> {

        public ViewHolder(ExerciseprBinding binding) {
            super(binding);
        }

        public void bind(ExercisePRData prData) {
            binding.setData(prData);
            binding.executePendingBindings();
        }
    }

    public ExercisePRAdapter(ArrayList<ExercisePRData> exercisePRData) {
        this.exercisePRData = exercisePRData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExerciseprBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.exercisepr, parent, false);
        return new ExercisePRAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ExercisePRData prData = exercisePRData.get(position);
        holder.bind(prData);
        holder.itemView.setOnClickListener(v -> {
            onClickListener.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return exercisePRData.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
