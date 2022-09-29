package com.parth.design.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.parth.design.CategoriesActivity;
import com.parth.design.LogIn;
import com.parth.design.ProfesionalsActivity;
import com.parth.design.R;

public class FeedBackFragment extends Fragment {
    Button submitButton, skipButton;
    ImageButton backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_back, container, false);

        submitButton = view.findViewById(R.id.submit_feedback_button);
        skipButton = view.findViewById(R.id.skip_feedback_button);
        backButton = view.findViewById(R.id.back_feedBack_button);

        //submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ProfesionalsActivity.class));
            }
        });

        //skip button
        /*skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CategoriesActivity.class));
            }
        });*/

        // back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}