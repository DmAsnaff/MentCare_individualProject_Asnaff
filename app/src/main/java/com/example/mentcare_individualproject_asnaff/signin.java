package com.example.mentcare_individualproject_asnaff;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mentcare_individualproject_asnaff.databinding.ActivitySigninBinding;


public class signin extends AppCompatActivity {

    ActivitySigninBinding binding;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        dbHandler = new DBHandler(this);

        getWindow().setStatusBarColor(Color.parseColor("#54434E"));
        setVariable();
    }

    private void setVariable() {

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= binding.registerName.getText().toString();
                String email = binding.registerEmail.getText().toString();
                String password = binding.registerPassword.getText().toString();
                String confirmPassword = binding.registerConpassword.getText().toString();
                if(email.equals("")||password.equals("")||confirmPassword.equals(""))
                    Toast.makeText(signin.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(confirmPassword)){
                        Boolean checkUserEmail = dbHandler.checkEmail(email);
                        if(checkUserEmail == false){
                            Boolean insert = dbHandler.insertData(name,email,password);
                            if(insert == true){
                                Toast.makeText(signin.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(signin.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(signin.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(signin.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signin.this, login.class);
                startActivity(intent);
            }
        });


    }
}