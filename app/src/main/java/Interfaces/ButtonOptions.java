package Interfaces;

import android.view.View;

import androidx.fragment.app.DialogFragment;

public interface ButtonOptions {
    View.OnClickListener onCreateEmpty(DialogFragment fragment);
    View.OnClickListener onCreatePrevious(DialogFragment fragment);
}
