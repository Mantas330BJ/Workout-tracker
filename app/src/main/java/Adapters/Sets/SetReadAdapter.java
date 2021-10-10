package Adapters.Sets;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbasic.R;

import ImageViews.WorkoutCommentView;
import ImageViews.WorkoutFileView;
import Datas.SetData;
import Interfaces.WorkoutInput;

import java.util.ArrayList;

import TextViews.ChooseRestTextView;
import TextViews.FloatTextView;
import TextViews.IntegerTextView;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SetReadAdapter extends RecyclerView.Adapter<SetReadAdapter.ViewHolder> {

    private final ArrayList<SetData> setDatas;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final IntegerTextView setTextView;
        private final FloatTextView weightTextView;
        private final FloatTextView repsTextView;
        private final FloatTextView rirTextView;
        private final ChooseRestTextView restTextView;
        private final WorkoutCommentView commentImageView;
        private final WorkoutFileView fileImageView;

        public ViewHolder(View view) {
            super(view);
            setTextView = view.findViewById(R.id.set);
            weightTextView = view.findViewById(R.id.weight);
            repsTextView = view.findViewById(R.id.reps);
            rirTextView = view.findViewById(R.id.rir);
            restTextView = view.findViewById(R.id.rest);
            commentImageView = view.findViewById(R.id.comment);
            fileImageView = view.findViewById(R.id.file);

            commentImageView.setImageResource(R.drawable.comment);
            fileImageView.setImageResource(R.drawable.file);
        }

        public IntegerTextView getSetTextView() {
            return setTextView;
        }

        public FloatTextView getWeightTextView() {
            return weightTextView;
        }

        public FloatTextView getRepsTextView() {
            return repsTextView;
        }

        public FloatTextView getRirTextView() {
            return rirTextView;
        }

        public ChooseRestTextView getRestTextView() {
            return restTextView;
        }

        public WorkoutCommentView getCommentImageView() {
            return commentImageView;
        }

        public WorkoutFileView getFileImageView() {
            return fileImageView;
        }

        public WorkoutInput[] getViews() {
            return new WorkoutInput[] {setTextView, weightTextView, repsTextView, rirTextView,
            restTextView, commentImageView, fileImageView};
        }
    }

    public SetReadAdapter(ArrayList<SetData> setDatas) {
        this.setDatas = setDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.exercise_info, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SetData setData = setDatas.get(position);
        holder.getSetTextView().setText(setData.getSet());
        holder.getWeightTextView().setText(setData.getWeight());
        holder.getRepsTextView().setText(setData.getReps());
        holder.getRirTextView().setText(setData.getRIR());
        holder.getRestTextView().setText(setData.getRest());
        holder.getCommentImageView().setParentData(setData.getComment());
        holder.getFileImageView().setParentData(setData.getFilePath());
    }

    @Override
    public int getItemCount() {
        return setDatas.size();
    }

}