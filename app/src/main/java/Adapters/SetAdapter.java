package Adapters;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import Datas.SetData;
import Interfaces.WorkoutInput;

@RequiresApi(api = Build.VERSION_CODES.O)

public class SetAdapter extends SetReadAdapter {
    public SetAdapter(ArrayList<SetData> setDatas) {
        super(setDatas);
    }

    @Override
    public void createClickListener(WorkoutInput workoutInput, int position) {
        int setPosition = 0;
        if (position != setPosition) {
            workoutInput.setOnClickListener(v -> {
                workoutInput.setTextEditListener();
            });
        }
    }
}
