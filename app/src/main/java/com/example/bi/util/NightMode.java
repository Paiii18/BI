package com.example.bi.util;

import androidx.appcompat.app.AppCompatDelegate;

public enum NightMode {
    AUTO(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    ON(AppCompatDelegate.MODE_NIGHT_YES),
    OFF(AppCompatDelegate.MODE_NIGHT_NO);

    private final int value;

    NightMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}