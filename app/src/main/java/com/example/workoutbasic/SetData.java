package com.example.workoutbasic;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.Duration;

public class SetData {

    private int set; //TODO: think about removing this
    private float weight;
    private float RIR;
    private Duration rest;
    private String comment;

    SetData(int set, float weight, float RIR, Duration rest, String comment) {
        this.set = set;
        this.weight = weight;
        this.RIR = RIR;
        this.rest = rest;
        this.comment = comment;
    }


    public int getSet() {
        return set;
    }

    public float getWeight() {
        return weight;
    }

    public float getRIR() {
        return RIR;
    }

    public Duration getRest() {
        return rest;
    }

    public String getComment() {
        return comment;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setRIR(float RIR) {
        this.RIR = RIR;
    }

    public void setRest(Duration rest) {
        this.rest = rest;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
