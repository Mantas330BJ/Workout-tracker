package Datas;

import android.os.Build;

import androidx.annotation.RequiresApi;

import Variables.Drt;
import Variables.Flt;
import Variables.Int;
import Variables.Str;

@RequiresApi(api = Build.VERSION_CODES.O)

public class SetData {

    private Int set; //TODO: think about removing this
    private Flt weight;
    private Flt reps;
    private Flt RIR;
    private Drt rest;
    private Str comment;

    public SetData(Int set, Flt weight, Flt reps, Flt RIR, Drt rest, Str comment) {
        this.set = set;
        this.weight = weight;
        this.reps = reps;
        this.RIR = RIR;
        this.rest = rest;
        this.comment = comment;
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

    public Str getComment() {
        return comment;
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

    public void setComment(Str comment) {
        this.comment = comment;
    }
}
