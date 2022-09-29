package com.parth.design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {
    TextView loginTextview;
    Button signUpButton;
    AppCompatRadioButton userRadioButton, professionalRadiobutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginTextview = findViewById(R.id.logIn_textView_register);
        signUpButton = findViewById(R.id.signUp_button_register);
        userRadioButton = findViewById(R.id.user_radio_button_register);
        professionalRadiobutton = findViewById(R.id.professional_radio_button_register);

        // LOG IN textView
        loginTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        //SignUp button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //user and professionals radio buttons
    public void onRadioButtonClicked(View view) {
        boolean isSelected = ((AppCompatRadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.user_radio_button_register:
                if (isSelected){
                    userRadioButton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                    professionalRadiobutton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_100));
                }
                break;
            case R.id.professional_radio_button_register:
                if (isSelected){
                    professionalRadiobutton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                    userRadioButton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_100));
                }
                break;
        }
    }
}