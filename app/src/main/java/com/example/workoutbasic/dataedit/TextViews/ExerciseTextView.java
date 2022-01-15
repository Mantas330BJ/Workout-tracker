package com.example.workoutbasic.dataedit.TextViews;

import static com.example.workoutbasic.utils.FragmentMethods.unwrap;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.workoutbasic.R;

import com.example.workoutbasic.interfaces.Variables.InputDatas;
import com.example.workoutbasic.interfaces.Variables.TextViewData;

@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseTextView extends androidx.appcompat.widget.AppCompatTextView {
    private Context context;
    protected TextViewData textData; //Used in dialog fragments.
    protected NavController navController;

    public ExerciseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        navController = Navigation.findNavController(unwrap(context), R.id.nav_host_fragment);
    }

    @BindingAdapter("Data")
    public static void setData(ExerciseTextView workoutTextView, InputDatas parentData) {
        workoutTextView.textData = (TextViewData) parentData;
        workoutTextView.setText(parentData.toString());
    }
}
