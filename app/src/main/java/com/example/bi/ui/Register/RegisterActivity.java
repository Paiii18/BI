package com.example.bi.ui.Register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bi.R;
import com.example.bi.ui.Login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.auth.User;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etName, etNPM, etPassword;
    private Button btnRegister;
    // private DatabaseReference databaseReference; // Komentari baris ini untuk Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // databaseReference = FirebaseDatabase.getInstance().getReference("users"); // Komentari baris ini untuk Firebase

        etName = findViewById(R.id.register_name_te);
        etNPM = findViewById(R.id.register_npm_te);
        etPassword = findViewById(R.id.passwordEditText);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(view -> registerUser());

        TextView tvlogin = findViewById(R.id.tv_login);
        tvlogin.setOnClickListener(view -> openLoginActivity());
    }

    private void registerUser() {
        String name = etName.getText().toString().trim();
        String npm = etNPM.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (name.isEmpty() || npm.isEmpty() || password.isEmpty()) {
            // Menampilkan pesan kesalahan jika ada kolom yang kosong
            Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
        } else {
            // Simpan informasi pengguna ke Firebase Database
            // User user = new User(name, npm); // Anda perlu membuat kelas User sesuai kebutuhan Anda
            // databaseReference.child(npm).setValue(user) // Komentari baris ini untuk Firebase

            // Navigasi ke halaman login setelah pendaftaran berhasil
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Menutup RegisterActivity
        }
    }

    private void openLoginActivity() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}



