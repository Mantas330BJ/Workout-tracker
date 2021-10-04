package Adapters;

import android.os.Build;
import androidx.annotation.RequiresApi;
import Datas.ExerciseData;
import java.util.ArrayList;
@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseAdapter extends ExerciseListenerReadAdapter {

    public ExerciseAdapter(ArrayList<ExerciseData> listData) {
        super(listData);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        setAdapter.setClickListener(doubleClickListener.onClick(position));
        setAdapter.setLongClickListener(childPos -> onLongClickListener.onLongClick(position));

        holder.itemView.setOnClickListener(v -> {
            doubleClickListener.onClick(position).onClick(-1); //Click headers.
        });


        holder.itemView.setOnLongClickListener(v -> {
            onLongClickListener.onLongClick(position);
            return true;
        });

    }
}
