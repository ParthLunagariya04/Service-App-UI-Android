package com.parth.design;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parth.design.apiController.ApiController;
import com.parth.design.fragments.HomeFragment;
import com.parth.design.model.RetrofitModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {
    TextView registerTextView, forgotPassword;
    Button loginButton;
    AppCompatRadioButton userButton, professionalButton;
    EditText mobileNO, password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        registerTextView = findViewById(R.id.register_textView_register);
        loginButton = findViewById(R.id.logIn_button_logIn);
        userButton = findViewById(R.id.user_radio_button_logIn);
        professionalButton = findViewById(R.id.professional_radio_button_logIn);
        forgotPassword = findViewById(R.id.forgot_password_textView_lonIn);
        mobileNO = findViewById(R.id.mobile_logIn);
        password = findViewById(R.id.password_login);

        //register text view
        registerTextView.setOnClickListener(view -> {
            Intent intent = new Intent(LogIn.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        //forgot password textView dialog show
        forgotPassword.setOnClickListener(view -> {
            Dialog dialog = new Dialog(LogIn.this);
            dialog.setContentView(R.layout.dialog_mobile_no);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            Button nextButton = dialog.findViewById(R.id.next_button_dialog);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LogIn.this, OtpActivity.class);
                    startActivity(intent);
                }
            });
        });

        //Login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String moNo = mobileNO.getText().toString();
                String psw = password.getText().toString();

                Call<RetrofitModel> call = ApiController.getInstance().getRetrofit()
                        .logIn(moNo,psw,"2");

                call.enqueue(new Callback<RetrofitModel>() {
                    @Override
                    public void onResponse(@NonNull Call<RetrofitModel> call, @NonNull Response<RetrofitModel> response) {
                        assert response.body() != null;
                        if (response.body().getResult()){
                            String accessToken = response.body().getAccess_token();

                            SharedPreferences preferences = getSharedPreferences(RegisterActivity.PREF_NAME, 0);
                            SharedPreferences.Editor editor =preferences.edit();
                            editor.putBoolean("hasLoggedIn", true);
                            editor.putString("user_token", accessToken);
                            //Log.d("MyLogData", "MY tokan - " + response.body().getAccess_token() + "    "  + response.body().getMessage() + "  " + response.body().getResult());
                            editor.apply();

                            startActivity(new Intent(LogIn.this, MainActivity.class));
                            Toast.makeText(LogIn.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(LogIn.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<RetrofitModel> call, @NonNull Throwable t) {
                        Toast.makeText(LogIn.this, "something went wrong please try again!..", Toast.LENGTH_LONG).show();
                        Log.d("MyLogData", "MY ERROR - " + t);
                    }
                });
            }
        });
    }

    // user and professional radio buttons
    @SuppressLint("NonConstantResourceId")
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



