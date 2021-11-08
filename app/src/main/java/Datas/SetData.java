package Datas;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.workoutbasic.BR;
import com.google.gson.annotations.Expose;

import Interfaces.Variables.Setters;
import Utils.Formatter;
import Utils.NumberSetter;

public class SetData extends BaseObservable {

    private int set; //TODO: think about removing this
    private double weight;
    private double reps;
    private double RIR;
    private int rest;
    private String comment;
    private String filePath;
    transient private Object[] getters;
    transient private Setters[] setters;

    public SetData(int set, double weight, double reps, double RIR, int rest, String comment, String filePath) {
        this.set = set;
        this.weight = weight;
        this.reps = reps;
        this.RIR = RIR;
        this.rest = rest;
        this.comment = comment;
        this.filePath = filePath;
    }

    public Object getGetter(int index) {
        if (getters == null) {
            getters = new Object[] {
                    Formatter.formatInteger(getSet()),
                    Formatter.formatDouble(getWeight()),
                    Formatter.formatDouble(getReps()),
                    Formatter.formatDouble(getRIR()),
                    getRest(),
                    getComment(),
                    getFilePath(),
            };
        }
        return getters[index];
    }

    public void getSetter(Object o, int index) {
        if (setters == null) {
            setters = new Setters[] {
                    (d) -> setSet(NumberSetter.getIntegerFromString((String) d)),
                    (d) -> setWeight(NumberSetter.getDoubleFromString((String) d)),
                    (d) -> setReps(NumberSetter.getDoubleFromString((String) d)),
                    (d) -> setRIR(NumberSetter.getDoubleFromString((String) d)),
                    (d) -> setRest((Integer) d),
                    (d) -> setComment((String) d),
                    (d) -> setFilePath((String) d)
            };
        }
        setters[index].set(o);
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
