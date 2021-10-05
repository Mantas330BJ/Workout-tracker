package ImageViews;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import Activities.EditExerciseActivity;
import Fragments.ChooseFileOptionsFragment;
import Variables.UriPasser;
@RequiresApi(api = Build.VERSION_CODES.O)
public class WorkoutFileView extends WorkoutImageView {
    public static final int REQUEST_CODE = 79;
    public static final String permissionString = Manifest.permission.READ_EXTERNAL_STORAGE;

    public WorkoutFileView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextClickListener() {
        setOnClickListener(view -> checkPermissions());
    }

    void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(getContext(), permissionString)
            == PackageManager.PERMISSION_GRANTED) {
            showFileOptions(getContext(), (UriPasser)parentData);
        } else {
            ((EditExerciseActivity)getContext()).requestPermissions(new String[] {permissionString}, REQUEST_CODE, (UriPasser)parentData);
        }
    }

    public static void showFileOptions(Context context, UriPasser parentData) {
        ChooseFileOptionsFragment chooseFileOptionsFragment = new ChooseFileOptionsFragment(parentData);
        chooseFileOptionsFragment.show(((FragmentActivity)context).getSupportFragmentManager(), "ChooseFileOptionsFragment");
    }

}
