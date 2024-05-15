package com.example.mentcare_individualproject_asnaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    ImageButton homebutton,locationbutton,historybutton,profilebutton, carebutton;
    Button questionbutton,buttonhelp;
    TextView subtitleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(Color.parseColor("#54434E"));

        homebutton = findViewById(R.id.homebutton);
        carebutton = findViewById(R.id.carebutton);
        locationbutton = findViewById(R.id.locationbutton);
        historybutton = findViewById(R.id.historybutton);
        profilebutton = findViewById(R.id.profilebutton);
        questionbutton = findViewById(R.id.questionbutton);
        buttonhelp = findViewById(R.id.buttonhelp);
        subtitleTextView = findViewById(R.id.subtitleTextView);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }

        });

        carebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }

        });


        locationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MapsActivity.class);
                startActivity(intent);
            }

        });


        historybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }

        });

        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }

        });



        questionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, dassDescription.class);
                startActivity(intent);
            }

        });

        buttonhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:1926"));
                startActivity(dialIntent);
            }
        });

        DBHandler dbHandler = new DBHandler(this);
        String userName = dbHandler.getUserName();

        subtitleTextView.setText(userName);

    }
}