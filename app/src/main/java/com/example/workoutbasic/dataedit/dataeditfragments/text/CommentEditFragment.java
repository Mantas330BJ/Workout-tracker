package com.example.workoutbasic.dataedit.dataeditfragments.text;

import android.content.DialogInterface;

import androidx.annotation.NonNull;

public class CommentEditFragment extends TextFragment { //TODO: fishy

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        setNewData();
        dismissWithoutSettingText(dialog);
    }
}
