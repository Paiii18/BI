package com.example.bi.ui.add;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import com.example.bi.R;
import com.example.bi.util.TimePickerFragment;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddGuidanceActivity extends AppCompatActivity implements TimePickerFragment.DialogTimeListener {
    private String startTime = "";
    private String endTime = "";
    private AddCourseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guidance);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        AddCourseViewModelFactory factory = AddCourseViewModelFactory.createFactory(this);
        viewModel = new ViewModelProvider(this, factory).get(AddCourseViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true; // Mengembalikan true untuk menunjukkan bahwa menu telah berhasil dibuat
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_insert) {
            TextInputEditText edCourseName = findViewById(R.id.ed_course_name);
            Spinner spinnerDay = findViewById(R.id.spinner_day);
            TextInputEditText edLecturer = findViewById(R.id.ed_lecturer);
            TextInputEditText edNote = findViewById(R.id.ed_note);
            String courseName = edCourseName.getText().toString();
            String day = spinnerDay.getSelectedItem().toString();
            int dayNumber = getDayNumberByDayName(day);
            String lecturer = edLecturer.getText().toString();
            String note = edNote.getText().toString();
            if (courseName.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || dayNumber == -1 || lecturer.isEmpty() || note.isEmpty()) {
                Log.d("AddGuidanceActivity", "Data tidak lengkap");
                return false;
            } else {
                viewModel.insertCourse(courseName, dayNumber, startTime, endTime, lecturer, note);
                finish();
                Log.d("AddGuidanceActivity", "Data berhasil disimpan");
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void showStartTimePicker(View view) {
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getSupportFragmentManager(), "startPicker");
    }

    public void showEndTimePicker(View view) {
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getSupportFragmentManager(), "endPicker");
    }

    @Override
    public void onDialogTimeSet(String tag, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        switch (tag) {
            case "startPicker":
                TextView tvStartTime = findViewById(R.id.tv_start_time);
                tvStartTime.setText(timeFormat.format(calendar.getTime()));
                startTime = timeFormat.format(calendar.getTime());
                break;
            case "endPicker":
                TextView tvEndTime = findViewById(R.id.tv_end_time);
                tvEndTime.setText(timeFormat.format(calendar.getTime()));
                endTime = timeFormat.format(calendar.getTime());
                break;
        }
    }

    private int getDayNumberByDayName(String dayName) {
        String[] days = getResources().getStringArray(R.array.day);
        for (int i = 0; i < days.length; i++) {
            if (days[i].equals(dayName)) {
                return i;
            }
        }
        return -1;
    }
}