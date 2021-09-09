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
    private WorkoutClickListener clickListener;
    private final ArrayList<SetData> setDatas;
    private Context context;
    private boolean shouldEdit;

    private int parentPosition;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final WorkoutInput[] views;
        private final LinearLayout linearLayout;

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
                        ((WorkoutTextView) views[i]).setTextEditListener();
                    }
                } else {
                    ((WorkoutImageView) views[i]).setImageResource(resources[i - (views.length - 2)]);
                }
            }

            linearLayout = view.findViewById(R.id.layout);
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

        public LinearLayout getLinearLayout() {
            return linearLayout;
        }

        public WorkoutInput[] getViews() {
            return views;
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
        holder.setIsRecyclable(false);
        SetData setData = setDatas.get(position);
        holder.getSetTextView().setBaseParams(setData.getSet());
        holder.getWeightTextView().setBaseParams(setData.getWeight());
        holder.getRepsTextView().setBaseParams(setData.getReps());
        holder.getRirTextView().setBaseParams(setData.getRIR());
        holder.getRestTextView().setBaseParams(setData.getRest());
        createListeners(holder, position);
    }

    public void createListeners(ViewHolder holder, int position) {
        SetData setData = setDatas.get(position);
        if (shouldEdit) {
            holder.getComment().setOnClickListener(v -> {
                TextEditPopupFragment popup = new TextEditPopupFragment();
                popup.show(((FragmentActivity) context).getSupportFragmentManager(), "TextEditPopupFragment");
                ((FragmentActivity) context).getSupportFragmentManager().executePendingTransactions();
                setData.getComment().setFragmentInput(popup);
                popup.setParentData(setData.getComment());
                ((OnInputListener) context).setCurrentClicked(holder.getComment());
            });

            for (WorkoutInput workoutInput : holder.getViews()) {
                workoutInput.setOnLongClickListener(v -> {
                    longClickListener.onLongClick(position);
                    return true;
                });
            }
        }
        else {
            holder.getLinearLayout().setOnLongClickListener(v -> {
                longClickListener.onLongClick(parentPosition);
                return true;
            });

            holder.getLinearLayout().setOnClickListener(v -> {
                clickListener.onClick(parentPosition);
            });
        }
    }

    public void setLongClickListener(WorkoutLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setClickListener(WorkoutClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setParentInfo(int parentPosition) {
        this.parentPosition = parentPosition;
    }

    @Override
    public int getItemCount() {
        return setDatas.size();
    }

}