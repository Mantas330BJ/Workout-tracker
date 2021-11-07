package Datas;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.workoutbasic.BR;

public class SetData extends BaseObservable {

    private int set; //TODO: think about removing this
    private double weight;
    private double reps;
    private double RIR;
    private int rest;
    private String comment;
    private String filePath;

    public SetData(int set, double weight, double reps, double RIR, int rest, String comment, String filePath) {
        this.set = set;
        this.weight = weight;
        this.reps = reps;
        this.RIR = RIR;
        this.rest = rest;
        this.comment = comment;
        this.filePath = filePath;
    }

    @Bindable
    public int getSet() {
        return set;
    }

    @Bindable
    public double getWeight() {
        return weight;
    }

    @Bindable
    public double getReps() {
        return reps;
    }

    @Bindable
    public double getRIR() {
        return RIR;
    }

    @Bindable
    public int getRest() {
        return rest;
    }

    public String getComment() {
        return comment;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setSet(int set) {
        this.set = set;
        notifyPropertyChanged(BR.set);
    }

    public void setReps(double reps) {
        this.reps = reps;
        notifyPropertyChanged(BR.reps);
    }

    public void setWeight(double weight) {
        this.weight = weight;
        notifyPropertyChanged(BR.weight);
    }

    public void setRIR(double RIR) {
        this.RIR = RIR;
        notifyPropertyChanged(BR.rIR);
    }

    public void setRest(int rest) {
        this.rest = rest;
        notifyPropertyChanged(BR.rest);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
