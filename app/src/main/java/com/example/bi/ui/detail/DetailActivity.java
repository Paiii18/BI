package com.example.bi.ui.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.bi.R;
import com.example.data.Course;
import com.example.bi.util.DayName;

public class DetailActivity extends AppCompatActivity {

    public static final String COURSE_ID = "courseId";
    private DetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int courseId = getIntent().getIntExtra(COURSE_ID, 0);
        DetailViewModelFactory factory = DetailViewModelFactory.createFactory(this, courseId);

        viewModel = new ViewModelProvider(this, factory).get(DetailViewModel.class);

        viewModel.getCourse().observe(this, course -> showCourseDetail(course));
    }

    private void showCourseDetail(Course course) {
        if (course != null) {
            String timeString = getString(R.string.time_format);
            DayName dayName = DayName.valueOf(course.getDay());
            String timeFormat = String.format(timeString, dayName, course.getStartTime(), course.getEndTime());

            TextView tvCourseName = findViewById(R.id.tv_course_name);
            tvCourseName.setText(course.getCourseName());

            TextView tvTime = findViewById(R.id.tv_time);
            tvTime.setText(timeFormat);

            TextView tvLecturer = findViewById(R.id.tv_lecturer);
            tvLecturer.setText(course.getLecturer());

            TextView tvNote = findViewById(R.id.tv_note);
            tvNote.setText(course.getNote());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }
}