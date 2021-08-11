package com.example.workoutbasic;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.Duration;

public class SetData extends Datas {

    private Int set; //TODO: think about removing this
    private Flt weight;
    private Flt RIR;
    private Drt rest;
    private Str comment;

    SetData(Int set, Flt weight, Flt RIR, Drt rest, Str comment) {
        this.set = set;
        this.weight = weight;
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
