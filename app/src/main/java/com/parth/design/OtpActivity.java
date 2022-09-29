package com.parth.design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.parth.design.R;

public class OtpActivity extends AppCompatActivity {
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        getWindow().setStatusBarColor(Color.WHITE);

        backButton = findViewById(R.id.back_button_otp);

        //back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(OtpActivity.this, LogIn.class));
                onBackPressed();
            }
        });

    }
}