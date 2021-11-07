package DataEdit.DataEditFragments.Time;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutbasic.R;

import java.util.Calendar;

import DataEdit.DataEditFragments.SetFragments;
import Datas.WorkoutData;
import ViewModels.WorkoutDataViewModel;

public class DatePickFragment extends SetFragments {
    private DatePicker datePicker;
    private WorkoutData workoutData;

    @Override
    public void createView(View view) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WorkoutDataViewModel viewModel = new ViewModelProvider(requireActivity()).get(WorkoutDataViewModel.class);
        viewModel.getSelected().observe(requireActivity(), data -> {
            workoutData = data;
            datePicker = view.findViewById(R.id.date_picker);

            Calendar cal = Calendar.getInstance();
            cal.setTime(workoutData.getDate());
            int year = cal.get(Calendar.YEAR), month = cal.get(Calendar.MONTH), day = cal.get(Calendar.DATE);
            datePicker.updateDate(year, month, day);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.date_pick_fragment, container, false);
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        Calendar cal = Calendar.getInstance();
        int year = datePicker.getYear(), month = datePicker.getMonth(), day = datePicker.getDayOfMonth();
        cal.set(year, month, day);

        workoutData.setDate(cal.getTime());
        super.onDismiss(dialog);
    }
}
