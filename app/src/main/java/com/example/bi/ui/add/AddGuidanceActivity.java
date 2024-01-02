package com.example.bi.ui.add;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import com.example.bi.R;
import com.example.bi.ui.Main.MainActivity;



public class AddGuidanceActivity extends AppCompatActivity {

    private EditText edCourseName, edLecturer, edNote;
    private Spinner spinnerDay;
    private TextView tvStartTime, tvEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guidance);

        edCourseName = findViewById(R.id.ed_course_name);
        edLecturer = findViewById(R.id.ed_lecturer);
        edNote = findViewById(R.id.ed_note);
        spinnerDay = findViewById(R.id.spinner_day);
        tvStartTime = findViewById(R.id.tv_start_time);
        tvEndTime = findViewById(R.id.tv_end_time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_insert) {
            if (validateInputs()) {
                saveToDatabaseAndNavigate();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveToDatabaseAndNavigate() {
        String courseName = edCourseName.getText().toString().trim();
        String lecturer = edLecturer.getText().toString().trim();
        String note = edNote.getText().toString().trim();
        String selectedDay = spinnerDay.getSelectedItem().toString();
        String startTime = tvStartTime.getText().toString().trim();
        String endTime = tvEndTime.getText().toString().trim();

        // TO-DO: Simpan data ke database
        // Guidance guidance = new Guidance(courseName, lecturer, note, selectedDay, startTime, endTime);
        // long insertedRowId = DatabaseHelper.getInstance(getApplicationContext()).insertGuidance(guidance);

        // TO-DO: Tindakan setelah penyimpanan berhasil atau gagal
        // Misalnya, tampilkan pesan dan navigasi ke halaman utama
        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddGuidanceActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Sebagai contoh, menutup Activity setelah berpindah ke MainActivity
    }



    private boolean validateInputs() {
        if (TextUtils.isEmpty(edCourseName.getText().toString().trim())) {
            edCourseName.setError("Course name is required");
            return false;
        }
        // Lakukan validasi lainnya di sini sesuai kebutuhan

        return true;
    }
}

