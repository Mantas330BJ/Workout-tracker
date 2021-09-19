package Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;
import Datas.SetData;
import CustomViews.WorkoutImageView;
import Interfaces.WorkoutInput;
import CustomViews.WorkoutTextView;

import java.util.ArrayList;

import Interfaces.WorkoutClickListener;
import Interfaces.WorkoutLongClickListener;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SetAdapter extends RecyclerView.Adapter<SetAdapter.ViewHolder> {
    private WorkoutLongClickListener longClickListener;
    private WorkoutClickListener clickListener;
    private final ArrayList<SetData> setDatas;
    private Context context;
    private boolean shouldEdit;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final WorkoutInput[] views;

        public ViewHolder(View view, boolean shouldEdit) {
            super(view);
            int[] ids = new int[] {
                    R.id.set, R.id.weight, R.id.reps, R.id.rir, R.id.rest, R.id.comment, R.id.file
            };
            int [] resources = new int[] {
                    R.drawable.comment, R.drawable.file
            };

            views = new WorkoutInput[ids.length];
            for (int i = 0; i < views.length; ++i) {
                views[i] = view.findViewById(ids[i]);
                if (i < views.length - 2) {
                    ((WorkoutTextView) views[i]).setTextSize(Data.textSize);
                    if (shouldEdit && ids[i] != R.id.set) {
                        ((WorkoutTextView) views[i]).setTextEditListener(); //TODO: maybe pass functions to prevent conditions
                    }
                } else {
                    if (shouldEdit) {
                        ((WorkoutImageView) views[i]).setTextEditListener();
                    }
                    ((WorkoutImageView) views[i]).setImageResource(resources[i - (views.length - 2)]);
                }
            }
        }

        public WorkoutTextView getSetTextView() {
            return (WorkoutTextView) views[0];
        }

        public WorkoutTextView getWeightTextView() {
            return (WorkoutTextView) views[1];
        }

        public WorkoutTextView getRepsTextView() {
            return (WorkoutTextView) views[2];
        }

        public WorkoutTextView getRirTextView() {
            return (WorkoutTextView) views[3];
        }

        public WorkoutTextView getRestTextView() {
            return (WorkoutTextView) views[4];
        }

        public WorkoutImageView getComment() {
            return (WorkoutImageView) views[5];
        }

        public WorkoutImageView getFile() {
            return (WorkoutImageView) views[6];
        }

        public WorkoutInput[] getViews() {
            return views;
        }
    }

    public SetAdapter(ArrayList<SetData> setDatas, boolean shouldEdit) {
        this.setDatas = setDatas;
        this.shouldEdit = shouldEdit;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.exercise_info, parent, false);
        return new ViewHolder(listItem, shouldEdit);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        createListeners(holder, position);
        SetData setData = setDatas.get(position);
        holder.getSetTextView().setBaseParams(setData.getSet());
        holder.getWeightTextView().setBaseParams(setData.getWeight());
        holder.getRepsTextView().setBaseParams(setData.getReps());
        holder.getRirTextView().setBaseParams(setData.getRIR());
        holder.getRestTextView().setBaseParams(setData.getRest());
        holder.getComment().setParentData(setData.getComment());
        holder.getFile().setParentData(setData.getFilePath());
    }

    public void createListeners(ViewHolder holder, int position) {
        for (WorkoutInput workoutInput : holder.getViews()) {
            if (!shouldEdit) {
                workoutInput.setOnClickListener(v -> {
                    if (clickListener != null) {
                        clickListener.onClick(position);
                    }
                });
            }
            workoutInput.setOnLongClickListener(v -> {
                if (longClickListener != null) {
                    longClickListener.onLongClick(position);
                }
                return true;
            });
        }
    }

    public void setLongClickListener(WorkoutLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setClickListener(WorkoutClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return setDatas.size();
    }

}