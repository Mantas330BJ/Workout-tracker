package Datas;

import android.os.Build;

import androidx.annotation.RequiresApi;

import Variables.DurationPasser;
import Variables.DoublePasser;
import Variables.IntPasser;
import Variables.StringPasser;
import Variables.UriPasser;

@RequiresApi(api = Build.VERSION_CODES.O)

public class SetData {

    private IntPasser set; //TODO: think about removing this
    private DoublePasser weight;
    private DoublePasser reps;
    private DoublePasser RIR;
    private DurationPasser rest;
    private StringPasser comment;
    private UriPasser filePath;

    public SetData(IntPasser set, DoublePasser weight, DoublePasser reps, DoublePasser RIR, DurationPasser rest, StringPasser comment, UriPasser filePath) {
        this.set = set;
        this.weight = weight;
        this.reps = reps;
        this.RIR = RIR;
        this.rest = rest;
        this.comment = comment;
        this.filePath = filePath;
    }


    public IntPasser getSet() {
        return set;
    }

    public DoublePasser getWeight() {
        return weight;
    }

    public DoublePasser getReps() {
        return reps;
    }

    public DoublePasser getRIR() {
        return RIR;
    }

    public DurationPasser getRest() {
        return rest;
    }

    public StringPasser getComment() {
        return comment;
    }

    public UriPasser getFilePath() {
        return filePath;
    }

    public void setSet(IntPasser set) {
        this.set = set;
    }

    public void setReps(DoublePasser reps) {
        this.reps = reps;
    }

    public void setWeight(DoublePasser weight) {
        this.weight = weight;
    }

    public void setRIR(DoublePasser RIR) {
        this.RIR = RIR;
    }

    public void setRest(DurationPasser rest) {
        this.rest = rest;
    }

    public void setComment(StringPasser comment) {
        this.comment = comment;
    }

    public void setFilePath(UriPasser filePath) {
        this.filePath = filePath;
    }
}
