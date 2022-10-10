package com.parth.design.fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.parth.design.R;
import com.parth.design.RegisterActivity;
import com.parth.design.apiController.ApiController;
import com.parth.design.model.RetrofitModel;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    public static final int CAMERA_REQUEST = 100;
    public static final int STORAGE_REQUEST = 101;
    Button editDetailsButton;
    ImageButton backButton;
    String[] cameraPermission;
    String[] storagePermission;
    ImageView profileImage;
    Uri resultUri;
    EditText name, password, confirmPassword, mobileNo, address1, address2, city, pincode, state;
    SharedPreferences preferences;
    String token;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        editDetailsButton = view.findViewById(R.id.edit_details_button);
        backButton = view.findViewById(R.id.back_profile_button);
        name = view.findViewById(R.id.userName_editText_profile_fragment);
        password = view.findViewById(R.id.password_editText_profile_fragment);
        confirmPassword = view.findViewById(R.id.confirm_password_editText_profile_fragment);
        mobileNo = view.findViewById(R.id.mobileNo_editText_profile_fragment);
        address1 = view.findViewById(R.id.address_line1_editText_profile_fragment);
        address2 = view.findViewById(R.id.address_line2_editText_profile_fragment);
        city = view.findViewById(R.id.city_editText_profile_fragment);
        pincode = view.findViewById(R.id.pinCode_editText_profile_fragment);
        state = view.findViewById(R.id.state_editText_profile_fragment);
        profileImage = view.findViewById(R.id.profile_image);

        saveButton();
        imagePick();

        // back profile button
        backButton.setOnClickListener(view1 -> getActivity().onBackPressed());

        return view;
    }

    //save button
    private void saveButton() {
        editDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("MY_DATA", " clicked ");

                String name1 = name.getText().toString();
                String psw = password.getText().toString();
                String cpsw = confirmPassword.getText().toString();
                String moNo = mobileNo.getText().toString();
                String add1 = address1.getText().toString();
                String add2 = address2.getText().toString();
                String cit = city.getText().toString();
                String pin = pincode.getText().toString();
                String stat = state.getText().toString();

                // getting token from shared pref
                preferences = requireContext().getSharedPreferences(RegisterActivity.PREF_NAME, 0);
                token = preferences.getString("user_token", "user_token");
                //Log.d("MY_DATA", "USER TOKEN - " + token);

                Call<RetrofitModel> call = ApiController.getInstance().getRetrofit().update(name1, "hello@gmail.com",
                        moNo, add1, "98.2110", "22.02", add2, "2", "2",
                        pin, "junction, rajkot","Bearer "+token);

                Log.d("MY_DATA", " call image - " + imageViewToByte(profileImage));

                call.enqueue(new Callback<RetrofitModel>() {
                    @Override
                    public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
                       // response.body().setAccess_token(token);
                        assert response.body() != null;
                        if (response.body().getResult()){
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("MY_DATA", " on response- " + response.body().getMessage());
                        }else {
                            Log.d("MY_DATA", " required - " + response.body().getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<RetrofitModel> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("MY_DATA", " on response - " + t.getMessage());
                    }
                });

                /*final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_new_password);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                //submit button in dialog
                Button submitButton = (Button) dialog.findViewById(R.id.submit_button_dialog);
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), DescriptionActivity.class);
                        startActivity(intent);
                    }
                });*/
            }
        });
    }

    // Image picker
    private void imagePick() {
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkCameraPermission()) {
                    requestCameraPermission();

                    Log.d("MY_DATA", " AVATAR 0");
                } else {
                    pickFromGallery();

                    Log.d("MY_DATA", " PICK FROM GALLERY ");
                }
            }
        });
    }

    //Here we will pick image from gallery or camera
    private void pickFromGallery() {
        ImagePicker.with(this)
                .crop()
                .start();
    }

    //request camera permission
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(cameraPermission, CAMERA_REQUEST);
    }

    //check camera permission
    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        boolean result2 = ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        return result && result2;
    }

    // convert image to byte
    private byte[] imageViewToByte(ImageView avatar) {
        Bitmap bitmap = ((BitmapDrawable) avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (grantResults.length > 0) {
                    boolean camera_accept = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storage_accept = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (camera_accept && storage_accept) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(getContext(), "Enable camera and storage permission", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case STORAGE_REQUEST:
                if (grantResults.length > 0) {
                    boolean storage_accept = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storage_accept) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(getContext(), "Please enable storage permission", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImagePicker.REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                assert data != null;
                resultUri = data.getData();
                profileImage.setImageURI(resultUri);

                Log.d("MY_DATA", "IMAGE PICKER result CODE - " + profileImage);
            }
        }
    }
}