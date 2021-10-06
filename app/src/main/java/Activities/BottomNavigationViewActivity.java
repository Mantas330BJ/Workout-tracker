package Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workoutbasic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BottomNavigationViewActivity extends DatabaseActivity {

    public void createNavigationViewListener() {
        String className = this.getClass().getName();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.history_page && !className.equals(WorkoutActivity.class.getName())) {
                Intent intent = new Intent(this, WorkoutActivity.class);
                startActivity(intent);
            } else if (id == R.id.exercises_page && !className.equals(ExerciseActivity.class.getName())) {
                Intent intent = new Intent(this, ExerciseActivity.class);
                startActivity(intent);
            }
            return false;
        });
    }
}
