package com.example.sava.gotill;

public class MyClock {
    public int hours, minutes;

    MyClock(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    int toMillis() {
        return 60 * hours + minutes;
    }
}
