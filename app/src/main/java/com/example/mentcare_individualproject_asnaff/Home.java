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
import java.util.Locale;

public class Home extends AppCompatActivity {

   // private String userEmail; // Assuming this is set somewhere in your activity

    DBHandler dbHandler;
    private String user_email;

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

        dbHandler = new DBHandler(this);


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
                Intent intent = new Intent(Home.this, Home.class);
                startActivity(intent);
            }

        });

//        profilebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = getIntent();
//                String user_email = intent.getStringExtra("USER_EMAIL");
//                Intent intentProfile = new Intent(Home.this, profile.class);
//                intent.putExtra("USER_EMAIL", user_email);
//                startActivity(intentProfile);
//            }
//
//        });
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When transitioning to the Profile activity
                String userEmail = getIntent().getStringExtra("USER_EMAIL");
                Intent profileIntent = new Intent(Home.this, profile.class);
                profileIntent.putExtra("USER_EMAIL", userEmail);
                startActivity(profileIntent);
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
        String user_email = intent.getStringExtra("USER_EMAIL");


//        DBHandler dbHandler = new DBHandler(this);
        String userName = dbHandler.getUserNameByEmail(user_email);

        subtitleTextView.setText(userName);

    }

//    private void saveMoodToDatabase(String mood) {
//        Intent intent = getIntent();
//        String userEmail = intent.getStringExtra("USER_EMAIL");
////        String dateSelected = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        String dateSelected = "2000-01-01";
//        if (userEmail != null) {
//            boolean isInserted = dbHandler.insertMood(userEmail, mood, dateSelected);
//            if (isInserted) {
//                Toast.makeText(Home.this, "Mood Updated Successfully!", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(Home.this, "Mood Update Failed!", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(Home.this, "User email not available!", Toast.LENGTH_SHORT).show();
//        }
//    }
private void saveMoodToDatabase(String mood) {
    if (dbHandler == null) {
        dbHandler = new DBHandler(this);
    }

    // Get the current date
    String dateSelected = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

    Intent intent = getIntent();
    String userEmail = intent.getStringExtra("USER_EMAIL");

    if (userEmail == null || userEmail.isEmpty()) {
        Toast.makeText(Home.this, "User email not found!", Toast.LENGTH_SHORT).show();
        return;
    }

    // Save the selected mood to the database
    boolean isInserted = dbHandler.insertMood(userEmail, mood, dateSelected);
    if (isInserted) {
        Toast.makeText(Home.this, "Mood Updated Successfully!", Toast.LENGTH_SHORT).show();
    } else {
        Toast.makeText(Home.this, "Mood Update Failed!", Toast.LENGTH_SHORT).show();
    }
}
}