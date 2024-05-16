package com.example.mentcare_individualproject_asnaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Home extends AppCompatActivity {

   // private String userEmail; // Assuming this is set somewhere in your activity

    DBHandler dbHandler;

    ImageButton   terribleImageView, badImageView, okayImageView,
            goodImageView, greatImageView, homebutton,
            locationbutton,historybutton,profilebutton,
            carebutton;

    Button questionbutton,buttonhelp;
    TextView subtitleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(Color.parseColor("#54434E"));



        terribleImageView = findViewById(R.id.terribleImageView);
        badImageView = findViewById(R.id.badImageView);
        okayImageView = findViewById(R.id.okayImageView);
        goodImageView = findViewById(R.id.goodImageView);
        greatImageView = findViewById(R.id.greatImageView);



        homebutton = findViewById(R.id.homebutton);
        carebutton = findViewById(R.id.carebutton);
        locationbutton = findViewById(R.id.locationbutton);
        historybutton = findViewById(R.id.historybutton);
        profilebutton = findViewById(R.id.profilebutton);
        questionbutton = findViewById(R.id.questionbutton);
        buttonhelp = findViewById(R.id.buttonhelp);
        subtitleTextView = findViewById(R.id.subtitleTextView);


        terribleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMoodToDatabase("Terrible");
            }
        });

        badImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMoodToDatabase("Bad");
            }
        });

        okayImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMoodToDatabase("Okay");
            }
        });

        goodImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMoodToDatabase("Good");
            }
        });

        greatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMoodToDatabase("Great");
            }
        });



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
                Intent intent = new Intent(Home.this, selfCare.class);
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

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("USER_EMAIL");


        DBHandler dbHandler = new DBHandler(this);
        String userName = dbHandler.getUserNameByEmail(userEmail);

        subtitleTextView.setText(userName);

    }

    private void saveMoodToDatabase(String mood) {
        // Get the current date

        String dateSelected = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("USER_EMAIL");
        // Save the selected mood to the database
        boolean isInserted = dbHandler.insertMood(userEmail, mood, dateSelected);
        if (isInserted) {
            Toast.makeText(Home.this, "Mood Updated Successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Home.this, "Mood Update Failed!", Toast.LENGTH_SHORT).show();
        }
    }




}