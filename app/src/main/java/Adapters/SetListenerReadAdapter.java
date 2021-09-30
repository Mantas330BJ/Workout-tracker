package Adapters;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import Datas.SetData;
import Interfaces.OnClickListener;
import Interfaces.OnLongClickListener;
import Interfaces.WorkoutInput;

@RequiresApi(api = Build.VERSION_CODES.O)

public class SetListenerReadAdapter extends SetReadAdapter {
    private OnLongClickListener longClickListener;
    private OnClickListener clickListener;

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        createListeners(holder, position);
        super.onBindViewHolder(holder, position);
    }

    public void createListeners(ViewHolder holder, int position) {
        int pos = 0;
        for (WorkoutInput workoutInput : holder.getViews()) {
            createClickListener(workoutInput, pos);
            workoutInput.setOnLongClickListener(v -> {
                if (longClickListener != null) {
                    longClickListener.onLongClick(position);
                }
                return true;
            });
            ++pos;
        }
    }

    public void createClickListener(WorkoutInput workoutInput, int position) {
        workoutInput.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onClick(position);
            }
        });
    }

    public void setLongClickListener(OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public SetListenerReadAdapter(ArrayList<SetData> setDatas) {
        super(setDatas);
    }
}
