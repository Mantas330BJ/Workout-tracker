package DataEditFragments.Text;

import android.content.DialogInterface;

import androidx.annotation.NonNull;

import DataEditFragments.Text.StringFragment;
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
