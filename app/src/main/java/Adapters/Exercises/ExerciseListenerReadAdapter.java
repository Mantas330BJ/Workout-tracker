package Adapters.Exercises;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import Adapters.Sets.SetListenerReadAdapter;
import Datas.ExerciseData;

import Datas.SetData;
import Interfaces.Listeners.DoubleClickListener;
import Interfaces.Listeners.OnLongClickListener;

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
