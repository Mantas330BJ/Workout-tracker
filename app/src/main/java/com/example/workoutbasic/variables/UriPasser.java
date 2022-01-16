package com.example.workoutbasic.variables;

import com.example.workoutbasic.interfaces.variables.InputDatas;

public class UriPasser implements InputDatas {
    private String uri;

    public UriPasser(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
