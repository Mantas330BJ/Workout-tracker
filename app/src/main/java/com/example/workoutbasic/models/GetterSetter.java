package com.example.workoutbasic.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GetterSetter<T> {
    private final Supplier<T> getter;
    private final Consumer<T> setter;

    public GetterSetter(Supplier<T> getter, Consumer<T> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public T get() {
        return getter.get();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void set(T t) {
        setter.accept(t);
    }
}
