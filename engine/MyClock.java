package com.example.sava.gotill.engine;

public class MyClock {
    public Integer hours, minutes;

    public MyClock(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }
    public MyClock(int minutes) {
        this.hours = minutes / 60;
        this.minutes = minutes % 60;
    }

    private String form2(Integer n) {
        String str = n.toString();
        if (str.length() == 2) {
            return str;
        }
        return "0" + str;
    }

    public String getTime() {
        return form2(hours) + ":" + form2(minutes);
    }

    public int toMillis() {
        return 60 * hours + minutes;
    }
}
