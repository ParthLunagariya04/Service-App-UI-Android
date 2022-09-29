package com.parth.design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parth.design.fragments.HomeFragment;

public class LogIn extends AppCompatActivity {
    TextView registerTextView, forgotPassword;
    Button loginButton;
    AppCompatRadioButton userButton, professionalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        /*materialButtonToggleGroup = findViewById(R.id.toggle_button_group);
        userButton = findViewById(R.id.user_button_logIn);
        professioner = findViewById(R.id.professioner_button_logIn);

        try {
            materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                @Override
                public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                    if (isChecked) {
                        if (checkedId == R.id.user_button_logIn){
                            userButton.setBackgroundColor(Color.GRAY);
                        }else if(checkedId == R.id.professioner_button_logIn){
                            professioner.setBackgroundColor(Color.GRAY);
                        }
                    }else {
                        userButton.setBackgroundColor(Color.BLUE);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("parth", "THIS IS LOG " + e);
        }*/

        registerTextView = findViewById(R.id.register_textView_register);
        loginButton = findViewById(R.id.logIn_button_logIn);
        userButton = findViewById(R.id.user_radio_button_logIn);
        professionalButton = findViewById(R.id.professional_radio_button_logIn);
        forgotPassword = findViewById(R.id.forgot_password_textView_lonIn);

        //register text view
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //forgot password textView dialog show
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(LogIn.this);
                dialog.setContentView(R.layout.dialog_mobile_no);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                Button nextButton = (Button) dialog.findViewById(R.id.next_button_dialog);
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(LogIn.this, OtpActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        //Login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this, MainActivity.class));
            }
        });
    }

    // user and professional radio buttons
    public void onRadioButtonClicked(View view) {
        boolean isSelected = ((AppCompatRadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.user_radio_button_logIn:
                if (isSelected) {
                    userButton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                    professionalButton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_100));
                }
                break;
            case R.id.professional_radio_button_logIn:
                if (isSelected) {
                    professionalButton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                    userButton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_100));
                }
                break;
        }
    }
}



