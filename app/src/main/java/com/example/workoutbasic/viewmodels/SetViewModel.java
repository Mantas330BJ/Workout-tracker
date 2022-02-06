package com.example.workoutbasic.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workoutbasic.models.GetterSetter;

import java.time.LocalDate;

public class SetViewModel extends ViewModel {
    private final MutableLiveData<GetterSetter<Integer>> mutableInteger = new MutableLiveData<>();
    private final MutableLiveData<GetterSetter<Double>> mutableDouble = new MutableLiveData<>();
    private final MutableLiveData<GetterSetter<String>> mutableString = new MutableLiveData<>();
    private final MutableLiveData<GetterSetter<LocalDate>> mutableLocalDate = new MutableLiveData<>();

    public void selectInteger(GetterSetter<Integer> integer) {
        mutableInteger.setValue(integer);
    }

    public LiveData<GetterSetter<Integer>> getSelectedInteger() {
        return mutableInteger;
    }

    public void selectDouble(GetterSetter<Double> dbl) {
        mutableDouble.setValue(dbl);
    }

    public LiveData<GetterSetter<Double>> getSelectedDouble() {
        return mutableDouble;
    }

    public void selectString(GetterSetter<String> str) {
        mutableString.setValue(str);
    }

    public LiveData<GetterSetter<String>> getSelectedString() {
        return mutableString;
    }

    public void selectLocalDate(GetterSetter<LocalDate> localDate) {
        mutableLocalDate.setValue(localDate);
    }

    public LiveData<GetterSetter<LocalDate>> getSelectedLocalDate() {
        return mutableLocalDate;
    }
}
