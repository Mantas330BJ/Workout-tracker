package Datas;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.workoutbasic.BR;

import java.util.ArrayList;
import java.util.Date;


public class WorkoutData extends BaseObservable {
    private Date date;
    private ArrayList<ExerciseData> exercises;


    public WorkoutData(Date date, ArrayList<ExerciseData> exercises) {
        this.date = date;
        this.exercises = exercises;
    }

    @Bindable
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    public void setExercises(ArrayList<ExerciseData> exercises) {
        this.exercises = exercises;
    }

    public ArrayList<ExerciseData> getExercises() {
        return exercises;
    }
}
