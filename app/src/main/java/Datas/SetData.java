package Datas;

import android.os.Build;

import androidx.annotation.RequiresApi;

import Variables.Drt;
import Variables.Flt;
import Variables.Int;
import Variables.Str;
import Variables.commentStr;
import Variables.wUri;

@RequiresApi(api = Build.VERSION_CODES.O)

public class SetData {

    private Int set; //TODO: think about removing this
    private Flt weight;
    private Flt reps;
    private Flt RIR;
    private Drt rest;
    private commentStr comment;
    private wUri filePath;

    public SetData(Int set, Flt weight, Flt reps, Flt RIR, Drt rest, commentStr comment, wUri filePath) {
        this.set = set;
        this.weight = weight;
        this.reps = reps;
        this.RIR = RIR;
        this.rest = rest;
        this.comment = comment;
        this.filePath = filePath;
    }


    public Int getSet() {
        return set;
    }

    public Flt getWeight() {
        return weight;
    }

    public Flt getReps() {
        return reps;
    }

    public Flt getRIR() {
        return RIR;
    }

    public Drt getRest() {
        return rest;
    }

    public commentStr getComment() {
        return comment;
    }

    public wUri getFilePath() {
        return filePath;
    }

    public void setSet(Int set) {
        this.set = set;
    }

    public void setWeight(Flt weight) {
        this.weight = weight;
    }

    public void setRIR(Flt RIR) {
        this.RIR = RIR;
    }

    public void setRest(Drt rest) {
        this.rest = rest;
    }

    public void setComment(commentStr comment) {
        this.comment = comment;
    }

    public void setFilePath(wUri filePath) {
        this.filePath = filePath;
    }
}
