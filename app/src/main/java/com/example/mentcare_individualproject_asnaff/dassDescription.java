package com.example.mentcare_individualproject_asnaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dassDescription extends AppCompatActivity {
Button button4;
    Button questionbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dass_description);
        getWindow().setStatusBarColor(Color.parseColor("#54434E"));

        button4 = findViewById(R.id.button4);

        button4.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent= new Intent(dassDescription.this, quiz.class);
                                           startActivity(intent);
                                       }
                                   }
        );


    }
}