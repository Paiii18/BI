package com.example.bi.util;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

    public static String timeDifference(int day, String targetTime) {
        String[] splitTime = targetTime.split(":");

        Calendar start = Calendar.getInstance();
        start.set(Calendar.DAY_OF_WEEK, day);
        start.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitTime[0]));
        start.set(Calendar.MINUTE, Integer.parseInt(splitTime[1]));
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        Calendar currentTime = Calendar.getInstance();

        long startDayNumber = start.getTimeInMillis();
        long currentDayNumber = currentTime.getTimeInMillis();
        if (startDayNumber < currentDayNumber) {
            start.set(Calendar.WEEK_OF_YEAR, start.get(Calendar.WEEK_OF_YEAR) + 1);
        }

        String remainingTime;
        if (currentTime.getTimeInMillis() < start.getTimeInMillis()) {
            remainingTime = DateUtils.getRelativeTimeSpanString(
                    start.getTimeInMillis(),
                    currentTime.getTimeInMillis(),
                    DateUtils.SECOND_IN_MILLIS
            ).toString();
        } else {
            remainingTime = DateUtils.getRelativeTimeSpanString(
                    currentTime.getTimeInMillis(),
                    start.getTimeInMillis(),
                    DateUtils.DAY_IN_MILLIS
            ).toString();
        }

        return "(" + remainingTime + ")";
    }
}