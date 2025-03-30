package com.example.ayurvedicapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.btnLogin).setOnClickListener(v -> loginUser());
        findViewById(R.id.btnSignUp).setOnClickListener(v -> showSignUp());
        findViewById(R.id.tvForgotPassword).setOnClickListener(v -> showForgotPassword());
        findViewById(R.id.btnTreatment).setOnClickListener(v -> showTreatments());
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, TreatmentActivity.class));
            finish();
        }
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            return;
        }

        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, TreatmentActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Authentication failed: " + 
                            task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void showSignUp() {
        // Will implement sign up functionality later
        Toast.makeText(this, "Sign up feature coming soon", Toast.LENGTH_SHORT).show();
    }

    private void showForgotPassword() {
        // Will implement password reset later
        Toast.makeText(this, "Password reset feature coming soon", Toast.LENGTH_SHORT).show();
    }

    private void showTreatments() {
        startActivity(new Intent(this, TreatmentActivity.class));
    }
}