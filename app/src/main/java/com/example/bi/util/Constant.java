package com.example.bi.util;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Constant {

    private static final String NOTIFICATION_CHANNEL_NAME = "Course Channel";
    private static final String NOTIFICATION_CHANNEL_ID = "notify-schedule";
    private static final int NOTIFICATION_ID = 32;
    private static final int ID_REPEATING = 101;

    private static final ExecutorService SINGLE_EXECUTOR = Executors.newSingleThreadExecutor();

    public static void executeThread(Runnable f) {
        SINGLE_EXECUTOR.execute(f);
    }
}