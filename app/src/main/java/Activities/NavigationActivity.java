package Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import Fragments.ChooseTypeFragment;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapters.WorkoutAdapter;
import Datas.WorkoutData;
import Interfaces.DoubleClickListener;
import Interfaces.NestedListenerPasser;
import Interfaces.OnLongClickListener;
import NavigationViewFragments.ExercisesFragment;
import NavigationViewFragments.HistoryFragment;

@RequiresApi(api = Build.VERSION_CODES.O)


public class NavigationActivity extends AppCompatActivity {
    final Fragment historyFragment = new HistoryFragment();
    final Fragment exercisesFragment = new ExercisesFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = historyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        createNavigationViewListener();

        fm.beginTransaction().add(R.id.main_container, historyFragment, "1").commit();
        fm.beginTransaction().add(R.id.main_container, exercisesFragment, "2")
                .hide(exercisesFragment).commit();
    }

    public void createNavigationViewListener() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.history_page) {
                fm.beginTransaction().hide(active).show(historyFragment).commit();
                active = historyFragment;
            } else if (id == R.id.exercises_page) {
                fm.beginTransaction().hide(active).show(exercisesFragment).commit();
                active = exercisesFragment;
            }
            return false;
        });
    }
}