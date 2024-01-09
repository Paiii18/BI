package com.example.bi.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bi.R;
import com.example.data.Course;
import com.example.bi.util.DayName;

public class CourseViewHolder extends RecyclerView.ViewHolder {

    private Course course;
    private final String timeString;

    public CourseViewHolder(View view) {
        super(view);
        timeString = itemView.getContext().getResources().getString(R.string.time_format);
    }

    // TODO 7 : Complete ViewHolder to show item
    public void bind(Course course, OnItemClickListener clickListener) {
        this.course = course;

        DayName dayName = DayName.valueOf(course.getDay());
        TextView tvCourse = itemView.findViewById(R.id.tv_course);
        String timeFormat = String.format(timeString, dayName, course.getStartTime(), course.getEndTime());
        TextView tvLecturer = itemView.findViewById(R.id.tv_lecturer);
        TextView tvTime = itemView.findViewById(R.id.tv_time);
        tvCourse.setText(course.getCourseName());
        tvTime.setText(timeFormat);
        tvLecturer.setText(course.getLecturer());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(course);
            }
        });
    }

    public Course getCourse() {
        return course;
    }

    public interface OnItemClickListener {
        void onItemClick(Course course);
    }
}