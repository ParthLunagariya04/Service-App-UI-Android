package com.parth.design;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {
    ImageButton backButton;
    String number, name, password, confirmPsw, address1, address2, pincode;
    String verificationId;
    PinView pinView;
    Button verifyButton;
    TextView resentOtp;

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
                        Log.d("MY_OTP", "code" + code);
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
                    Log.d("MY_OTP ", "codeBySystem" + e.getMessage());
                }
            };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        mAuth = FirebaseAuth.getInstance();
        getWindow().setStatusBarColor(Color.WHITE);
        number = getIntent().getStringExtra("MoNumber");
        name = getIntent().getStringExtra("");
        password = getIntent().getStringExtra("");
        confirmPsw = getIntent().getStringExtra("");
        address1 = getIntent().getStringExtra("");
        address2 = getIntent().getStringExtra("");
        pincode = getIntent().getStringExtra("");

        sendVerificationCodeToUser("+91"+number);

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
        startActivity(new Intent(OtpActivity.this, MainActivity.class));

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