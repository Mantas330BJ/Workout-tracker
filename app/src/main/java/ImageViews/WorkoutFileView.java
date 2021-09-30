package ImageViews;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import Fragments.ChooseFileOptionsFragment;
import Variables.wUri;

public class WorkoutFileView extends WorkoutImageView {
    public static final int REQUEST_CODE = 79;
    public static final String permissionString = Manifest.permission.READ_EXTERNAL_STORAGE;

    public WorkoutFileView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextClickListener() {
        setOnClickListener(view -> {
            checkPermissions();
        });
    }

    void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(getContext(), permissionString)
            == PackageManager.PERMISSION_GRANTED) {
            ChooseFileOptionsFragment chooseFileOptionsFragment = new ChooseFileOptionsFragment((wUri)parentData);
            chooseFileOptionsFragment.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "ChooseFileOptionsFragment");
        } else {
            ActivityCompat.requestPermissions((Activity) getContext(), new String[] {permissionString}, REQUEST_CODE);
        }
    }

}
