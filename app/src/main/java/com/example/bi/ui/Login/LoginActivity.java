package com.example.bi.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bi.R;
import com.example.bi.ui.Main.MainActivity;
import com.example.bi.ui.Register.RegisterActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etNPM, etPassword;
    private Button btnLogin;
    private TextView tvRegisterHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi views
        etNPM = findViewById(R.id.ti_npm);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegisterHere = findViewById(R.id.tv_regisiter_here);

        btnLogin.setOnClickListener(view -> loginUser());
        tvRegisterHere.setOnClickListener(view -> openRegisterActivity());
    }

    private void loginUser() {
        String npm = etNPM.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (npm.isEmpty() || password.isEmpty()) {
            // Jika salah satu atau kedua kolom kosong, tampilkan pesan kesalahan
            Toast.makeText(this, "NPM dan password harus diisi", Toast.LENGTH_SHORT).show();
        } else {
            // Logika autentikasi bisa dimasukkan di sini
            // Contoh: Anda bisa memeriksa kecocokan NPM dan password dengan data yang ada

            // Jika autentikasi berhasil, arahkan pengguna ke MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Menutup LoginActivity agar tidak kembali ke sini setelah login berhasil
        }
    }

    private void openRegisterActivity() {
        // Buka RegisterActivity saat teks "Register Here" diklik
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
