package com.example.sava.gotill.engine;

public class MyClock {
    public int hours, minutes;

    public MyClock(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int toMillis() {
        return 60 * hours + minutes;
    }
}
