package DataEditFragments;

import android.content.DialogInterface;

import androidx.annotation.NonNull;

import Variables.Str;

public class CommentEditFragment extends StringFragment {

    public CommentEditFragment(Str parentData) {
        super(parentData);
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.dismissWithoutSettingText(dialog);
    }
}
