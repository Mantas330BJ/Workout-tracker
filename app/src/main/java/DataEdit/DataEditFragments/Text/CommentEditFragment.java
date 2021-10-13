package DataEdit.DataEditFragments.Text;

import android.content.DialogInterface;

import androidx.annotation.NonNull;

import Variables.StringPasser;

public class CommentEditFragment extends StringFragment {

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        setNewData();
        dismissWithoutSettingText(dialog);
    }
}
