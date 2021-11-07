package Datas;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.workoutbasic.BR;

import java.util.ArrayList;

public class ExerciseData extends BaseObservable {
    private String exercise;
    private final ArrayList<SetData> sets;

    public ExerciseData(String exercise, ArrayList<SetData> sets) {
        this.exercise = exercise;
        this.sets = sets;
    }

    @Bindable
    public String getExercise() {
        return exercise;
    }

    public ArrayList<SetData> getSets() {
        return sets;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
        notifyPropertyChanged(BR.exercise);
    }
}
