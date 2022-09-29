package com.parth.design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.parth.design.fragments.HomeFragment;

public class DescriptionActivity extends AppCompatActivity {
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        getWindow().setStatusBarColor(Color.WHITE);

        backButton = findViewById(R.id.back_button_description);

        // back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(DescriptionActivity.this, HomeFragment.class).putExtra("load","profile"));
                /*omeFragment homeFragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("data","from description activity");

                homeFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.linear_layout_description, homeFragment).commit();*/

                onBackPressed();
            }
        });
    }
}