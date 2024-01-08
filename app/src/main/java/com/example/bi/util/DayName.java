package com.example.bi.util;

import java.util.*;

public enum DayName {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String value;

    DayName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getByNumber(int dayNumber) {
        switch (dayNumber) {
            case Calendar.MONDAY:
                return MONDAY.getValue();
            case Calendar.TUESDAY:
                return TUESDAY.getValue();
            case Calendar.WEDNESDAY:
                return WEDNESDAY.getValue();
            case Calendar.THURSDAY:
                return THURSDAY.getValue();
            case Calendar.FRIDAY:
                return FRIDAY.getValue();
            case Calendar.SATURDAY:
                return SATURDAY.getValue();
            case Calendar.SUNDAY:
                return SUNDAY.getValue();
            default:
                return MONDAY.getValue();
        }
    }
}