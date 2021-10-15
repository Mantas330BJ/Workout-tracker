package Activities;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Build;
import android.os.Bundle;

import com.example.workoutbasic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import NavigationViewFragments.ExercisesFragment;
import NavigationViewFragments.HistoryFragment;

@RequiresApi(api = Build.VERSION_CODES.O)


public class NavigationActivity extends DatabaseActivity {

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
}