package com.example.mentcare_individualproject_asnaff;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class quiz extends AppCompatActivity {

    private int totalDepressionScore = 0;
    private int totalAnxietyScore = 0;
    private int totalStressScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getWindow().setStatusBarColor(Color.parseColor("#54434E"));

        // Add listeners to each RadioGroup
        addListenerToRadioGroup(R.id.answerGroup1, "stress");
        addListenerToRadioGroup(R.id.answerGroup2, "anxiety");
        addListenerToRadioGroup(R.id.answerGroup3, "depression");
        addListenerToRadioGroup(R.id.answerGroup4, "anxiety");
        addListenerToRadioGroup(R.id.answerGroup5, "depression");
        addListenerToRadioGroup(R.id.answerGroup6, "stress");
        addListenerToRadioGroup(R.id.answerGroup7, "anxiety");
        addListenerToRadioGroup(R.id.answerGroup8, "stress");
        addListenerToRadioGroup(R.id.answerGroup9, "anxiety");
        addListenerToRadioGroup(R.id.answerGroup10, "depression");
        addListenerToRadioGroup(R.id.answerGroup11, "stress");
        addListenerToRadioGroup(R.id.answerGroup12, "stress");
        addListenerToRadioGroup(R.id.answerGroup13, "depression");
        addListenerToRadioGroup(R.id.answerGroup14, "stress");
        addListenerToRadioGroup(R.id.answerGroup15, "anxiety");
        addListenerToRadioGroup(R.id.answerGroup16, "depression");
        addListenerToRadioGroup(R.id.answerGroup17, "depression");
        addListenerToRadioGroup(R.id.answerGroup18, "stress");
        addListenerToRadioGroup(R.id.answerGroup19, "anxiety");
        addListenerToRadioGroup(R.id.answerGroup20, "anxiety");
        addListenerToRadioGroup(R.id.answerGroup21, "depression");

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResults();
            }
        });
    }

    private void addListenerToRadioGroup(int radioGroupId, String category) {
        RadioGroup radioGroup = findViewById(radioGroupId);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                int score = Integer.parseInt(selectedRadioButton.getText().toString());

                switch (category) {
                    case "depression":
                        totalDepressionScore += score;
                        break;
                    case "anxiety":
                        totalAnxietyScore += score;
                        break;
                    case "stress":
                        totalStressScore += score;
                        break;
                }
            }
        });
    }

    private void calculateResults() {

        // Multiply each total score by 2
        totalDepressionScore *= 2;
        totalAnxietyScore *= 2;
        totalStressScore *= 2;

        String depressionResult = getResultText(totalDepressionScore, "Depression");
        String anxietyResult = getResultText(totalAnxietyScore, "Anxiety");
        String stressResult = getResultText(totalStressScore, "Stress");

        Intent intent = new Intent(this, results.class);
        intent.putExtra("Depression", depressionResult);
        intent.putExtra("Anxiety", anxietyResult);
        intent.putExtra("Stress", stressResult);
        startActivity(intent);
    }

    private String getResultText(int score, String category) {
        String result = category + ": ";
        if (category.equals("Depression")) {
            if (score <= 9) result += "Normal";
            else if (score <= 13) result += "Mild";
            else if (score <= 20) result += "Moderate";
            else if (score <= 27) result += "Severe";
            else result += "Extremely Severe";
        } else if (category.equals("Anxiety")) {
            if (score <= 7) result += "Normal";
            else if (score <= 9) result += "Mild";
            else if (score <= 14) result += "Moderate";
            else if (score <= 19) result += "Severe";
            else result += "Extremely Severe";
        } else if (category.equals("Stress")) {
            if (score <= 14) result += "Normal";
            else if (score <= 18) result += "Mild";
            else if (score <= 25) result += "Moderate";
            else if (score <= 33) result += "Severe";
            else result += "Extremely Severe";
        }
        return result;
    }
}
