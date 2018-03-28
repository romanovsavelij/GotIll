package com.example.sava.gotill.engine;

public class MyClock {
    public Integer hours, minutes;

    private int getNumber(char c) {
        return c - '0';
    }

    public MyClock(String time) {
        this.hours = getNumber(time.charAt(0)) * 10 + getNumber(time.charAt(1));
        this.minutes = getNumber(time.charAt(3)) * 10 + getNumber(time.charAt(4));
    }

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
