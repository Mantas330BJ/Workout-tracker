package DataEdit.ImageViews;


import static Utils.FragmentMethods.unwrap;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.workoutbasic.R;

import Interfaces.Input.Inputs;
import Interfaces.Input.WorkoutInput;

abstract public class WorkoutImageView extends androidx.appcompat.widget.AppCompatImageView implements WorkoutInput {
    //protected InputDatas parentData;
    protected NavController navController;

    public WorkoutImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        navController = Navigation.findNavController(unwrap(context), R.id.nav_host_fragment);
    }

    /*
    public void setParentData(InputDatas parentData) {
        this.parentData = parentData;
    }
     */
}
