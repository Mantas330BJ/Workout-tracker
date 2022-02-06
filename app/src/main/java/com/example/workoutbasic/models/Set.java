package com.example.workoutbasic.models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.workoutbasic.BR;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Set extends BaseObservable {
    private int setIdx = 1; //TODO: think if needed field
    private double weight = 0;
    private double reps = 0;
    private double RIR = 0;
    private int restSeconds = 0;
    private String comment = "";
    private String filePath = ""; //TODO: was null before

    public Set() {
    }

    public Set(int setIdx, double weight, double reps, double RIR, int restSeconds, String comment, String filePath) {
        this.setIdx = setIdx;
        this.weight = weight;
        this.reps = reps;
        this.RIR = RIR;
        this.restSeconds = restSeconds;
        this.comment = comment;
        this.filePath = filePath;
    }

    public Set(Set original) {
        this.setIdx = original.getSetIdx();
        this.weight = original.getWeight();
        this.reps = original.getReps();
        this.RIR = original.getRIR();
        this.restSeconds = original.getRestSeconds();
        this.comment = "";
        this.filePath = "";
    }

    @Bindable
    public int getSetIdx() {
        return setIdx;
    }

    public void setSetIdx(int setIdx) {
        this.setIdx = setIdx;
        notifyPropertyChanged(BR.setIdx);
    }

    @Bindable
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        notifyPropertyChanged(BR.weight);
    }

    @Bindable
    public double getReps() {
        return reps;
    }

    public void setReps(double reps) {
        this.reps = reps;
        notifyPropertyChanged(BR.reps);
    }

    @Bindable
    public double getRIR() {
        return RIR;
    }

    public void setRIR(double RIR) {
        this.RIR = RIR;
        notifyPropertyChanged(BR.rIR);
    }

    @Bindable
    public int getRestSeconds() {
        return restSeconds;
    }

    public void setRestSeconds(int restSeconds) {
        this.restSeconds = restSeconds;
        notifyPropertyChanged(BR.restSeconds);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
