package com.example.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//TODO 1 : Define a local database table using the schema in app/schema/course.json
@Entity(tableName = DataCourseName.TABLE_NAME)
public class Course {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DataCourseName.COL_ID)
    private int id;
    @ColumnInfo(name = DataCourseName.COL_COURSE_NAME)
    private String courseName;
    @ColumnInfo(name = DataCourseName.COL_DAY)
    private int day;
    @ColumnInfo(name = DataCourseName.COL_START_TIME)
    private String startTime;
    @ColumnInfo(name = DataCourseName.COL_END_TIME)
    private String endTime;
    @ColumnInfo(name = DataCourseName.COL_LECTURER)
    private String lecturer;
    @ColumnInfo(name = DataCourseName.COL_NOTE)
    private String note;

    public Course(String courseName, int day, String startTime, String endTime, String lecturer, String note) {
        this.courseName = courseName;
        this.day = Integer.parseInt(String.valueOf(day));
        this.startTime = startTime;
        this.endTime = endTime;
        this.lecturer = lecturer;
        this.note = note;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = Integer.parseInt(day);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}