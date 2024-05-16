package com.example.mentcare_individualproject_asnaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mentcare_individualproject_asnaff.databinding.ActivityLoginBinding;

public class login extends AppCompatActivity {
    ActivityLoginBinding binding;
    DBHandler dbHandler;
    EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        dbHandler = new DBHandler(this);

        getWindow().setStatusBarColor(Color.parseColor("#54434E"));
        setVariable();
    }

    private void setVariable() {

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();
                if(email.equals("")||password.equals(""))
                    Toast.makeText(login.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkCredentials = dbHandler.checkEmailPassword(email, password);
                    if(checkCredentials == true){
                        Toast.makeText(login.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Home.class);
                        intent.putExtra("USER_EMAIL", email);

                        startActivity(intent);
                    }else{
                        Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        binding.signupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signin.class);
                startActivity(intent);
            }
        });


    }

}