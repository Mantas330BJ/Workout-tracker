package DataEditFragments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.workoutbasic.OnInputListener;

public abstract class TextFragments extends DialogFragment {
    private OnInputListener onInputListener; //Listener to pass data back to activity.

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onInputListener = (OnInputListener) getActivity();
    }

    public OnInputListener getOnInputListener() {
        return onInputListener;
    }
}
