package Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;

import DataEdit.ImageViews.WorkoutImageView;
import DataEdit.TextViews.WorkoutTextView;
import Interfaces.Input.Inputs;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CustomProperties {
    @BindingAdapter("propertyIdx")
    public static void setPropertyIdx(WorkoutTextView input, int idx) {
        input.setPropertyIdx(idx);
    }
    @BindingAdapter("propertyIdx")
    public static void setPropertyIdx(WorkoutImageView input, int idx) {
        input.setPropertyIdx(idx);
    }
}
