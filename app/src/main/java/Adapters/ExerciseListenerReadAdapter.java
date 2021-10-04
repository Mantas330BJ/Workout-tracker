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

import Datas.SetData;
import Interfaces.DoubleClickListener;
import TextViews.WorkoutTextView;
import Interfaces.OnLongClickListener;

import java.util.ArrayList;
@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseListenerReadAdapter extends ExerciseReadAdapter {

    protected DoubleClickListener doubleClickListener;
    protected OnLongClickListener onLongClickListener;
    protected SetListenerReadAdapter setAdapter;

    public ExerciseListenerReadAdapter(ArrayList<ExerciseData> listData) {
        super(listData);
    }

    @Override
    public void createSetAdapter(RecyclerView recyclerView, ArrayList<SetData> setData) {
        setAdapter = new SetListenerReadAdapter(setData);
        recyclerView.setAdapter(setAdapter);
    }


    public void setLongClickListener(OnLongClickListener longClickListener) {
        this.onLongClickListener = longClickListener;
    }

    public void setDoubleClickListener(DoubleClickListener doubleClickListener) {
        this.doubleClickListener = doubleClickListener;
    }
}
