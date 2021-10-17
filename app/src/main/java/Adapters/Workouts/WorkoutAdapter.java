package Adapters.Workouts;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;

import Datas.WorkoutData;

import java.util.ArrayList;

import Interfaces.Listeners.DoubleClickListener;
import Interfaces.Listeners.NestedListenerPasser;
import Interfaces.Listeners.OnLongClickListener;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> implements NestedListenerPasser {
    private final ArrayList<WorkoutData> listData;
    private Context context;
    private final NestedListenerPasser parent;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView date;
        private final RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            recyclerView = itemView.findViewById(R.id.recycler_view);
        }
    }

    public WorkoutAdapter(ArrayList<WorkoutData> listData, NestedListenerPasser parent) {
        this.listData = listData;
        this.parent = parent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //TODO: maybe pass layout id
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new ViewHolder(layoutInflater.inflate(R.layout.workout, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WorkoutData myListData = listData.get(position);

        TextView date = holder.date;
        date.setText(myListData.getDate().toString());

        WorkoutInfoAdapter workoutInfoAdapter = new WorkoutInfoAdapter(WorkoutDisplayer.getMainWorkoutInfo(myListData), this);
        workoutInfoAdapter.setParentPosition(position);

        RecyclerView recyclerView = holder.recyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(workoutInfoAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        holder.itemView.setOnClickListener(v -> {
            getDoubleClickListener().onClick(position).onClick(-1); //Click headers.
        });

        holder.itemView.setOnLongClickListener(v -> {
            getOnLongClickListener().onLongClick(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public DoubleClickListener getDoubleClickListener() {
        return parent.getDoubleClickListener();
    }

    @Override
    public OnLongClickListener getOnLongClickListener() {
        return parent.getOnLongClickListener();
    }

}


