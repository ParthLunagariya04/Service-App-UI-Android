package com.parth.design;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.content.ContextCompat;

import com.parth.design.apiController.ApiController;
import com.parth.design.model.RetrofitModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    public static String PREF_NAME = "my_pref";
    TextView loginTextview;
    Button signUpButton;
    AppCompatRadioButton userRadioButton, professionalRadiobutton;
    EditText userName, password, confirmPassword, mobileNo, addressLine1, addressLine2, city, pincode, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginTextview = findViewById(R.id.logIn_textView_register);
        signUpButton = findViewById(R.id.signUp_button_register);
        userRadioButton = findViewById(R.id.user_radio_button_register);
        professionalRadiobutton = findViewById(R.id.professional_radio_button_register);

        userName = findViewById(R.id.userName_editText_register);
        password = findViewById(R.id.password_editText_register);
        confirmPassword = findViewById(R.id.confirm_password_editText_register);
        mobileNo = findViewById(R.id.mobileNo_editText_register);
        addressLine1 = findViewById(R.id.address_line1_editText_register);
        addressLine2 = findViewById(R.id.address_line2_editText_register);
        city = findViewById(R.id.city_editText_register);
        pincode = findViewById(R.id.pincode_editText_register);
        state = findViewById(R.id.state_editText_register);

        // LOG IN textView
        loginTextview.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, LogIn.class));
        });

        signUpButtonClicked();
    }

    //SignUp button
    private void signUpButtonClicked() {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un = userName.getText().toString();
                String psw = password.getText().toString();
                String cpsw = confirmPassword.getText().toString();
                String moNo = mobileNo.getText().toString();
                String add1 = addressLine1.getText().toString();
                String add2 = addressLine2.getText().toString();
                String cit = city.getText().toString();
                String pin = pincode.getText().toString();
                String sta = state.getText().toString();

                if (un.isEmpty() || psw.isEmpty() || cpsw.isEmpty() || moNo.isEmpty() || add1.isEmpty() || add2.isEmpty() || pin.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "please enter all fields", Toast.LENGTH_SHORT).show();

                } else if (!psw.equals(cpsw)) {
                    Toast.makeText(RegisterActivity.this, "password and confirm password must be same", Toast.LENGTH_SHORT).show();

                } else if (moNo.length() != 10) {
                    Toast.makeText(RegisterActivity.this, "mobile number must be 10 digits", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                    intent.putExtra("userName", un);
                    intent.putExtra("password", psw);
                    intent.putExtra("confirmPassword", cpsw);
                    intent.putExtra("MoNumber", moNo);
                    intent.putExtra("address1", add1);
                    intent.putExtra("address2", add2);
                    intent.putExtra("pincode", pin);
                    startActivity(intent);
                }
            }
        });
    }

    //user and professionals radio buttons
    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        boolean isSelected = ((AppCompatRadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.user_radio_button_register:
                if (isSelected) {
                    userRadioButton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                    professionalRadiobutton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_100));
                }
                break;
            case R.id.professional_radio_button_register:
                if (isSelected) {
                    professionalRadiobutton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                    userRadioButton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_100));
                }
                break;
        }
    }

    private void registerUser() {

        String un = userName.getText().toString();
        String psw = password.getText().toString();
        String cpsw = confirmPassword.getText().toString();
        String moNo = mobileNo.getText().toString();
        String add1 = addressLine1.getText().toString();
        String add2 = addressLine2.getText().toString();
        String cit = city.getText().toString();
        String pin = pincode.getText().toString();
        String sta = state.getText().toString();

        // Api call
        Call<RetrofitModel> call = ApiController.getInstance().getRetrofit()
                .userData(un, "", moNo, add1, "", "", psw,
                        cpsw, "", "2", add2, "", "dggdhgtd66355", pin);

        call.enqueue(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(@NonNull Call<RetrofitModel> call, @NonNull Response<RetrofitModel> response) {
                assert response.body() != null;
                if (response.body().getResult()) {
                    if (moNo.length() != 10) {
                        Toast.makeText(RegisterActivity.this, "Enter valid mobile number", Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences preferences = getSharedPreferences(RegisterActivity.PREF_NAME, 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("hasRegistered", true);
                        editor.apply();

                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RetrofitModel> call, @NonNull Throwable t) {
                Toast.makeText(RegisterActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                Log.d("MyLogData", "MY ERROR - " + t.getMessage());
            }
        });
    }
}