package com.parth.design;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.parth.design.apiController.ApiController;
import com.parth.design.model.RetrofitModel;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {
    ImageButton backButton;
    String number, name, password, confirmPsw, address1, address2, pincode;
    String verificationId;
    PinView pinView;
    Button verifyButton;

    private FirebaseAuth mAuth;
    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String id, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(id, forceResendingToken);
                    verificationId = id;
                    Toast.makeText(OtpActivity.this, "OTP is successfully send.", Toast.LENGTH_SHORT).show();
                    Log.d("MY_OTP ", "codeBySystem" + verificationId);
                }

                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        pinView.setText(code);
                        Log.d("MY_OTP", "code - " + code);
                        verifyCode(code);
                    }
                }

                @Override
                public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                    super.onCodeAutoRetrievalTimeOut(s);
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(OtpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("MY_OTP ", "codeBySystem - " + e.getMessage());
                }
            };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        getWindow().setStatusBarColor(Color.WHITE);

        mAuth = FirebaseAuth.getInstance();

        number = getIntent().getStringExtra("MoNumber");
        name = getIntent().getStringExtra("userName");
        password = getIntent().getStringExtra("password");
        confirmPsw = getIntent().getStringExtra("confirmPassword");
        address1 = getIntent().getStringExtra("address1");
        address2 = getIntent().getStringExtra("address2");
        pincode = getIntent().getStringExtra("pincode");

        sendVerificationCodeToUser("+91" + number);

        backButton = findViewById(R.id.back_button_otp);
        pinView = findViewById(R.id.otpview_otp);
        verifyButton = findViewById(R.id.verify_button_otp);

        //back button
        backButton.setOnClickListener(view -> onBackPressed());

        onverifyButtonclicked();

        /*((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                .showSoftInput(pinView, InputMethodManager.SHOW_FORCED);*/

    }

    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
        //startActivity(new Intent(OtpActivity.this, MainActivity.class));

        Log.d("MY_OTP", "credential" + credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        registerApiCall();
                        Log.d("MY_OTP", "Verification Completed");
                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(OtpActivity.this, "Verification not Completed", Toast.LENGTH_LONG).show();
                            Log.d("MY_OTP", "Verification not Completed");
                        }
                    }
                });
    }

    // register api call
    private void registerApiCall() {
        Call<RetrofitModel> call = ApiController.getInstance().getRetrofit()
                .userData(name, "", number, address1, "", "", password,
                        confirmPsw, "", "2", address2, "", "dggdhgtd66355", pincode);

        Log.d("MyAPI", "My Names fields - " + name + " number- " + number + " add1- " + address1
                + " add2- " + address2 + " psw- " + password + " cpsw- " + confirmPsw + " pin- " + pincode);

        call.enqueue(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(@NonNull Call<RetrofitModel> call, @NonNull Response<RetrofitModel> response) {
                assert response.body() != null;
                if (response.body().getResult()) {
                    String userToken = response.body().getAccess_token();

                    SharedPreferences preferences = getSharedPreferences(RegisterActivity.PREF_NAME, 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("hasRegistered", true);
                    editor.putString("user_token", userToken);
                    editor.apply();

                    startActivity(new Intent(OtpActivity.this, MainActivity.class));
                    Toast.makeText(OtpActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    Toast.makeText(OtpActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RetrofitModel> call, @NonNull Throwable t) {
                Toast.makeText(OtpActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                Log.d("MyAPI", "MY ERROR - " + t.getMessage());
            }
        });
    }

    //verify button
    private void onverifyButtonclicked() {
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = pinView.getText().toString();
                if (!code.isEmpty()) {
                    verifyCode(code);
                }
            }
        });
    }
}