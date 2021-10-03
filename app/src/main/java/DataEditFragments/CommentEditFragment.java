package DataEditFragments;

import android.content.DialogInterface;

import androidx.annotation.NonNull;

import Variables.StringPasser;

public class CommentEditFragment extends StringFragment {

    public CommentEditFragment(StringPasser parentData) {
        super(parentData);
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.dismissWithoutSettingText(dialog);
    }
}
