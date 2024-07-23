package com.example.mentcare_individualproject_asnaff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Import Log class for logging
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {

    private static final String TAG = "ProfileActivity"; // Tag for logging
    private EditText nameEditText;
    private EditText emailEditText;
    private Button saveButton;
    private DBHandler dbHandler;
    private String userEmail; // Email of the user whose profile is being viewed/updated

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize the DBHandler
        dbHandler = new DBHandler(this);

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        saveButton = findViewById(R.id.saveButton);

        // Get user email from Intent
        userEmail = getIntent().getStringExtra("USER_EMAIL");

//        // Log the received email
//        Log.d(TAG, "Received email from Intent: " + userEmail);
//
//        // Display the email in a Toast (optional)
//        Toast.makeText(this, "Received email: " + userEmail, Toast.LENGTH_LONG).show();

        // Load user details
        loadUserDetails();

        // Set up the save button click listener
        saveButton.setOnClickListener(v -> updateUserDetails());
    }

    private void loadUserDetails() {
        User user = dbHandler.getUserByEmail(userEmail);
        if (user != null) {
            nameEditText.setText(user.getName());
            emailEditText.setText(user.getEmail());
        } else {
            Toast.makeText(this, "Failed to load user details.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUserDetails() {
        String newName = nameEditText.getText().toString().trim();
        String newEmail = emailEditText.getText().toString().trim();

        if (newName.isEmpty() || newEmail.isEmpty()) {
            Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isUpdated = dbHandler.updateUser(newName, newEmail, userEmail);
        if (isUpdated) {
            Toast.makeText(this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
            userEmail = newEmail;
            Intent homeIntent = new Intent(profile.this, Home.class);
            homeIntent.putExtra("USER_EMAIL", newEmail);
            startActivity(homeIntent);
            finish();
        } else {
            Toast.makeText(this, "Failed to update profile.", Toast.LENGTH_SHORT).show();
        }
    }
}
