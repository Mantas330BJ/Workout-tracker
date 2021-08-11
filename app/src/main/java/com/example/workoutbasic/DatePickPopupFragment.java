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
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DatePickPopupFragment extends DialogFragment {
    private DatePicker datePicker;
    private OnInputListener onInputListener;
    private TextViewData parentData;

    public void setArguments(TextViewData parentData) {
        this.parentData = parentData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_pick_popup, container, false);

        datePicker = new DatePicker(getContext()); //TODO add initialization methods to extended classes
        datePicker.init(2015, 11, 10, null);
        LinearLayout linearLayout = view.findViewById(R.id.linear_layout);
        linearLayout.addView(datePicker);

        return view;
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        Calendar cal = Calendar.getInstance();
        int year = datePicker.getYear(), month = datePicker.getMonth(), day = datePicker.getDayOfMonth();
        cal.set(year, month, day);
        System.out.println(year + " " + month + " " + day);
        Date date = cal.getTime();
        String input = new Dte(date).toString();
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
