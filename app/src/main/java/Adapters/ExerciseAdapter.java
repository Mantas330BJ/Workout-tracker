package Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Datas.ExerciseData;
import com.example.workoutbasic.R;

import Interfaces.DoubleClickListener;
import TextViews.WorkoutTextView;
import Interfaces.OnLongClickListener;

import java.util.ArrayList;
@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private ArrayList<ExerciseData> listData;
    private Context context;
    private boolean shouldEdit;

    private DoubleClickListener doubleClickListener;
    private OnLongClickListener longClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout constraintLayout;
        private final WorkoutTextView exercise;
        private final RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.list_item);
            exercise = itemView.findViewById(R.id.exercise);
            recyclerView = itemView.findViewById(R.id.recycler_view);
        }

        public ConstraintLayout getConstraintLayout() {
            return constraintLayout;
        }

        public WorkoutTextView getExercise() {
            return exercise;
        }

        public RecyclerView getRecyclerView() {
            return recyclerView;
        }
    }

    public ExerciseAdapter(ArrayList<ExerciseData> listData, boolean shouldEdit) {
        this.listData = listData;
        this.shouldEdit = shouldEdit;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //TODO: maybe pass layout id
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new ViewHolder(layoutInflater.inflate(R.layout.exercise, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ExerciseData myListData = listData.get(position);
        WorkoutTextView exerciseName = holder.getExercise();
        exerciseName.setText(myListData.getExercise());
        if (shouldEdit) {
            exerciseName.setTextClickListener();
        }

        SetReadAdapter setReadAdapter = new SetReadAdapter(myListData.getSets());
        if (doubleClickListener != null) {
            setReadAdapter.setClickListener(doubleClickListener.onClick(position));
        }
        if (longClickListener != null) {
            setReadAdapter.setLongClickListener(childPos -> longClickListener.onLongClick(position));
        }
        RecyclerView recyclerView = holder.getRecyclerView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(setReadAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        if (doubleClickListener != null) {
            holder.getConstraintLayout().setOnClickListener(v -> {
                doubleClickListener.onClick(position).onClick(-1); //Click headers.
            });
        }

        if (longClickListener != null) {
            holder.getConstraintLayout().setOnLongClickListener(v -> {
                longClickListener.onLongClick(position);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setLongClickListener(OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setDoubleClickListener(DoubleClickListener doubleClickListener) {
        this.doubleClickListener = doubleClickListener;
    }
}
