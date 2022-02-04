package com.example.workoutbasic.dataedit.textviews;

import static com.example.workoutbasic.utils.FragmentMethods.unwrap;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.workoutbasic.R;

@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseTextView extends androidx.appcompat.widget.AppCompatTextView {
    private Context context;
//    protected TextViewData textData; //Used in dialog fragments.
    protected NavController navController;

    public ExerciseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        navController = Navigation.findNavController(unwrap(context), R.id.nav_host_fragment);
    }
}
