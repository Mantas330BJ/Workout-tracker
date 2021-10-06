package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.workoutbasic.R;

public class ExerciseActivity extends BottomNavigationViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        createNavigationViewListener();
    }
}