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

@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseTextView extends androidx.appcompat.widget.AppCompatTextView {
    protected NavController navController;

    public ExerciseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        navController = Navigation.findNavController(unwrap(context), R.id.nav_host_fragment);
    }
}
