package com.example.sava.gotill.engine;

import android.content.Context;

import java.util.ArrayList;


public class Data {

    public static ArrayList<Information> getData(Context context) {
        ArrayList<Information> data = new ArrayList<>();
        int len = 20;
        for (int i = 0; i < len; i++) {
            Information current = new Information();
            current.medicineName = "name";
            current.alarmTime = "time" + i;
            data.add(current);
        }
        return data;
    }

}
