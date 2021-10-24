package Adapters.Sets;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ExerciseInfoBinding;

import Adapters.BindingViewHolder;
import Datas.SetData;
import Interfaces.Input.WorkoutInput;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SetReadAdapter extends RecyclerView.Adapter<SetReadAdapter.ViewHolder> {

    private final ArrayList<SetData> setDatas;
    public static class ViewHolder extends BindingViewHolder<ExerciseInfoBinding> {

        public ViewHolder(ExerciseInfoBinding binding) {
            super(binding);
        }

        public WorkoutInput[] getViews() {
            return new WorkoutInput[] {binding.set, binding.weight, binding.reps, binding.rir,
            binding.rest, binding.comment, binding.file};
        }

        public void bind(SetData setData) {
            binding.setData(setData);
            binding.executePendingBindings();
        }
    }

    public SetReadAdapter(ArrayList<SetData> setDatas) {
        this.setDatas = setDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExerciseInfoBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.exercise_info, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SetData setData = setDatas.get(position);
        holder.bind(setData); //TODO: add real bindings
    }

    @Override
    public int getItemCount() {
        return setDatas.size();
    }

}