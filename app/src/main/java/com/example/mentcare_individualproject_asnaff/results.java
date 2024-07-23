package com.example.mentcare_individualproject_asnaff;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getWindow().setStatusBarColor(Color.parseColor("#54434E"));

        // Retrieve results from the intent
        Intent intent = getIntent();
        String depressionResult = intent.getStringExtra("Depression");
        String anxietyResult = intent.getStringExtra("Anxiety");
        String stressResult = intent.getStringExtra("Stress");

        // Find TextViews
        TextView depressionTextView = findViewById(R.id.depressionResult);
        TextView anxietyTextView = findViewById(R.id.anxietyResult);
        TextView stressTextView = findViewById(R.id.stressResult);

        // Display the results
        depressionTextView.setText(depressionResult);
        anxietyTextView.setText(anxietyResult);
        stressTextView.setText(stressResult);
    }
}
