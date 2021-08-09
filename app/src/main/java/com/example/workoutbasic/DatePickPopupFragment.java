package com.example.workoutbasic;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DatePickPopupFragment extends DialogFragment {
    private Date date;
    private DatePicker datePicker;
    public static final String EXTRA_DATE = "extradate";
    public OnInputListener onInputListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_pick_popup, container, false);

        datePicker = view.findViewById(R.id.date_picker);
        String extraString = getArguments().getString(EXTRA_DATE); //TODO: pass date



        return view;
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        Calendar cal = Calendar.getInstance();
        int year = datePicker.getYear(), month = datePicker.getMonth(), day = datePicker.getDayOfMonth();
        cal.set(year, month, day);
        System.out.println(year + " " + month + " " + day);
        date = cal.getTime();
        String input = Data.getStringDate(date);
        onInputListener.sendInput(input);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onInputListener = (OnInputListener) getActivity();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

}
