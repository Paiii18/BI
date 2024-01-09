package com.example.bi.util;

import java.util.HashMap;
import java.util.Map;

public enum DayName {
    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday");

    private final String value;
    private static final Map<Integer, DayName> map = new HashMap<>();

    static {
        for (DayName day : DayName.values()) {
            map.put(day.ordinal(), day);
        }
    }

    DayName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DayName valueOf(int ordinal) {
        return map.get(ordinal);
    }
}
