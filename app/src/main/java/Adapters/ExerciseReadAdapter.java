package Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Datas.ExerciseData;
import com.example.workoutbasic.R;

import Datas.SetData;
import Interfaces.DoubleClickListener;
import TextViews.WorkoutTextView;
import Interfaces.OnLongClickListener;

import java.util.ArrayList;
@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseReadAdapter extends RecyclerView.Adapter<ExerciseReadAdapter.ViewHolder> {
    protected ArrayList<ExerciseData> listData;
    private Context context;

    private SetReadAdapter setAdapter;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final WorkoutTextView exercise;
        private final RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            exercise = itemView.findViewById(R.id.exercise);
            recyclerView = itemView.findViewById(R.id.recycler_view);
        }

        public WorkoutTextView getExercise() {
            return exercise;
        }

        public RecyclerView getRecyclerView() {
            return recyclerView;
        }
    }

    public ExerciseReadAdapter(ArrayList<ExerciseData> listData) {
        this.listData = listData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new ViewHolder(layoutInflater.inflate(R.layout.exercise, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ExerciseData myListData = listData.get(position);
        WorkoutTextView exerciseName = holder.getExercise();
        exerciseName.setText(myListData.getExercise());

        RecyclerView recyclerView = holder.getRecyclerView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        createSetAdapter(recyclerView, myListData.getSets());
    }

    public void createSetAdapter(RecyclerView recyclerView, ArrayList<SetData> setData) {
        setAdapter = new SetReadAdapter(setData);
        recyclerView.setAdapter(setAdapter);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
