package DataEdit.TextViews;

import static Utils.FragmentMethods.unwrap;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.workoutbasic.R;

import Interfaces.Variables.InputDatas;
import Interfaces.Variables.TextViewData;

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
