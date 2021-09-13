package Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;
import com.example.workoutbasic.Workout;
import Datas.WorkoutData;

import java.util.ArrayList;

import Interfaces.WorkoutListenerClickListener;
import Interfaces.WorkoutLongClickListener;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {
    private ArrayList<WorkoutData> listData;
    private boolean addExercise;
    private Context context;
    private View listItem;

    private WorkoutListenerClickListener workoutListenerClickListener;
    private WorkoutLongClickListener longClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout constraintLayout;
        private final TextView date;
        private final RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.list_item);
            date = itemView.findViewById(R.id.date);
            recyclerView = itemView.findViewById(R.id.recycler_view);
        }

        public ConstraintLayout getConstraintLayout() {
            return constraintLayout;
        }

        public TextView getDate() {
            return date;
        }

        public RecyclerView getRecyclerView() {
            return recyclerView;
        }
    }

    public WorkoutAdapter(ArrayList<WorkoutData> listData, boolean addExercise) {
        this.listData = listData;
        this.addExercise = addExercise;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //TODO: maybe pass layout id
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        listItem = layoutInflater.inflate(R.layout.workout, parent, false);
        return new ViewHolder(listItem);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WorkoutData myListData = listData.get(position);

        TextView date = holder.getDate();
        date.setText(myListData.getDate().toString());

        Workout workout = new Workout(myListData);
        workout.setAddExercise(addExercise);

        WorkoutInfoAdapter workoutInfoAdapter = new WorkoutInfoAdapter(workout.getMainWorkoutInfo());
        workoutInfoAdapter.setWorkoutLongClickListener(longClickListener); //Logic for table items.
        workoutInfoAdapter.setWorkoutClickListener(workoutListenerClickListener.onClick(position));

        RecyclerView recyclerView = holder.getRecyclerView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(workoutInfoAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        holder.getConstraintLayout().setOnClickListener(v -> {
            workoutListenerClickListener.onClick(position).onClick(-1); //Click headers.
        });

        holder.getConstraintLayout().setOnLongClickListener(v -> {
            longClickListener.onLongClick(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setLongClickListener(WorkoutLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setWorkoutListenerClickListener(WorkoutListenerClickListener workoutListenerClickListener) {
        this.workoutListenerClickListener = workoutListenerClickListener;
    }
}


