package com.example.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//TODO 3 : Define room database class
@Database(entities = {Course.class}, version = 1, exportSchema = false)
public abstract class CourseDatabase extends RoomDatabase {
    public abstract CourseDao courseDao();

    private static volatile CourseDatabase instance;

    public static CourseDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (CourseDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), CourseDatabase.class, "courses.db")
                            .build();
                }
            }
        }
        return instance;
    }
}