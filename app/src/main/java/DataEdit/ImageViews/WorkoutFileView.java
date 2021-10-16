package DataEdit.ImageViews;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.workoutbasic.R;

import DataEdit.DataEditFragments.TextFragments;
import Interfaces.TextViewData;
import Pages.Dialogs.ChooseFileOptionsFragment;
import Pages.Sets.EditExerciseFragment;
import Utils.FragmentMethods;
import Variables.UriPasser;
import ViewModels.SharedViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)

public class WorkoutFileView extends WorkoutImageView {


    public WorkoutFileView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextClickListener() {

        EditExerciseFragment parentFragment = (EditExerciseFragment)FragmentMethods.getParentFragment(getContext(), 0);
        setOnClickListener(view -> parentFragment.showPermission((UriPasser) parentData));
    }
}
