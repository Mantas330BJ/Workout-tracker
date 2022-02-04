package com.example.workoutbasic.utils;

public class Parser {

    private Parser() {
    }

    public static double parseDouble(String str) {
        return str.isEmpty() ? 0 : Double.parseDouble(str);
    }

    public static int parseInt(String str) {
        return str.isEmpty() ? 0 : Integer.parseInt(str);
    }
}
