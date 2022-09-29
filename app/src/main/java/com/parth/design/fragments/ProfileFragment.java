package com.parth.design.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.parth.design.CategoriesActivity;
import com.parth.design.DescriptionActivity;
import com.parth.design.LogIn;
import com.parth.design.MainActivity;
import com.parth.design.OtpActivity;
import com.parth.design.R;
import com.parth.design.databinding.ActivityMainBinding;

public class ProfileFragment extends Fragment {
    Button editDetailsButton;
    ImageButton backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        editDetailsButton = view.findViewById(R.id.edit_details_button);
        backButton = view.findViewById(R.id.back_profile_button);

        //edit button
        editDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
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
                });
            }
        });

        // back profile button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}