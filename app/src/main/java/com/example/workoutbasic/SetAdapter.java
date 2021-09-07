package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SetAdapter extends RecyclerView.Adapter<SetAdapter.ViewHolder> {
    private WorkoutLongClickListener longClickListener;
    private final ArrayList<SetData> setDatas;
    private Context context;
    private boolean shouldEdit;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final WorkoutTextView[] workoutTextViews;
        private final WorkoutImageView comment;
        private final WorkoutImageView file;

        public ViewHolder(View view, boolean shouldEdit) {
            super(view);
            int[] ids = new int[] {
                    R.id.set, R.id.weight, R.id.reps, R.id.rir, R.id.rest
            };
            workoutTextViews = new WorkoutTextView[ids.length];
            for (int i = 0; i < workoutTextViews.length; ++i) {
                workoutTextViews[i] = view.findViewById(ids[i]);
                workoutTextViews[i].setTextSize(Data.textSize);

                if (shouldEdit && ids[i] != R.id.set) {
                    workoutTextViews[i].setTextEditListener();
                }
            }

            comment = view.findViewById(R.id.comment);
            comment.setImageResource(R.drawable.comment);
            file = view.findViewById(R.id.file);
            file.setImageResource(R.drawable.file);
        }

        public WorkoutTextView getSetTextView() {
            return workoutTextViews[0];
        }

        public WorkoutTextView getWeightTextView() {
            return workoutTextViews[1];
        }

        public WorkoutTextView getRepsTextView() {
            return workoutTextViews[2];
        }

        public WorkoutTextView getRirTextView() {
            return workoutTextViews[3];
        }

        public WorkoutTextView getRestTextView() {
            return workoutTextViews[4];
        }

        public WorkoutImageView getComment() {
            return comment;
        }

        public WorkoutImageView getFile() {
            return file;
        }

        public WorkoutTextView[] getWorkoutTextViews() {
            return workoutTextViews;
        }
    }

    public SetAdapter(ArrayList<SetData> setDatas) {
        this.setDatas = setDatas;
    }

    public void setShouldEdit(boolean shouldEdit) {
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
        SetData setData = setDatas.get(position);
        holder.getSetTextView().setBaseParams(setData.getSet());
        holder.getWeightTextView().setBaseParams(setData.getWeight());
        holder.getRepsTextView().setBaseParams(setData.getReps());
        holder.getRirTextView().setBaseParams(setData.getRIR());
        holder.getRestTextView().setBaseParams(setData.getRest());
        holder.getComment().setOnClickListener(v -> {
            TextEditPopupFragment popup = new TextEditPopupFragment();
            popup.show(((FragmentActivity)context).getSupportFragmentManager(), "TextEditPopupFragment");
            ((FragmentActivity)context).getSupportFragmentManager().executePendingTransactions();
            setData.getComment().setFragmentInput(popup);
            popup.setParentData(setData.getComment());
            ((OnInputListener)context).setCurrentClicked(holder.getComment());
        });


        for (WorkoutTextView workoutTextView : holder.getWorkoutTextViews()) {
            workoutTextView.setOnLongClickListener(v -> {
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

    @Override
    public int getItemCount() {
        return setDatas.size();
    }

}