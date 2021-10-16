package Pages;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;
import com.example.workoutbasic.WorkoutDatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import DataEdit.ImageViews.WorkoutFileView;
import Datas.WorkoutData;

@RequiresApi(api = Build.VERSION_CODES.O)


public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        setUpNavigation();
    }

    public void setUpNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }

    protected void onPause() {
        super.onPause();
        SQLiteOpenHelper workoutDatabaseHelper = new WorkoutDatabaseHelper(this);
        SQLiteDatabase db = workoutDatabaseHelper.getWritableDatabase();
        db.execSQL("delete from WORKOUT");
        for (WorkoutData workoutData : Data.getWorkoutDatas()) {
            Data.addWorkoutData(db, workoutData);
        }
        db.close();
    }
}