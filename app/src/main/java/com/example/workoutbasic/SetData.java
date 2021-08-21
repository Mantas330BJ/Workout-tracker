package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
@RequiresApi(api = Build.VERSION_CODES.O)

public class SetData extends Datas {

    private Int set; //TODO: think about removing this
    private Flt weight;
    private Flt reps;
    private Flt RIR;
    private Drt rest;
    private Str comment;

    SetData(Int set, Flt weight, Flt reps, Flt RIR, Drt rest, Str comment) {
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

    @Override
    public boolean emptyChildren() {
        return false;
    }

    @Override
    public LinearLayout getLayout(Context context) {
        return new Set(this, context, true).getLayout();
    }
}
