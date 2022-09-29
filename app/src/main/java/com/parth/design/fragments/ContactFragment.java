package com.parth.design.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.constraintlayout.motion.widget.OnSwipe;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.parth.design.R;

public class ContactFragment extends Fragment {
    FrameLayout frameLayout;
    ImageButton backImageButton;

    public ContactFragment() {

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        backImageButton = view.findViewById(R.id.back_contact_button);

        //back button
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}