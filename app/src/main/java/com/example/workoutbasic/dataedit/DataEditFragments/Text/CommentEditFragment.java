package com.example.workoutbasic.dataedit.DataEditFragments.Text;

import android.content.DialogInterface;

import androidx.annotation.NonNull;

public class CommentEditFragment extends StringFragment {

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        setNewData();
        dismissWithoutSettingText(dialog);
    }
}
