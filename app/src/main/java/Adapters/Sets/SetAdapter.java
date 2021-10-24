package Adapters.Sets;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import Datas.SetData;
import Interfaces.Input.WorkoutInput;

@RequiresApi(api = Build.VERSION_CODES.O)

public class SetAdapter extends SetListenerReadAdapter {
    public SetAdapter(ArrayList<SetData> setDatas) {
        super(setDatas);
    }

    @Override
    public void createClickListener(WorkoutInput workoutInput, int position) {
        int setPosition = 0;
        if (position != setPosition) {
            workoutInput.setTextClickListener();
        }
    }
}
