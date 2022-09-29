package com.parth.design;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parth.design.adapter.ProfessionalCardAdapter;
import com.parth.design.model.ProfessionalCardModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProfesionalsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AppCompatRadioButton radiusRadioButton, ratingRadioButton, rrb5, rrb4, rrb3, rrb2, rrb1;
    ImageButton backButton;
    List<ProfessionalCardModel> pCardList;
    ProfessionalCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesionals);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.light_pink));
        }

        recyclerView = findViewById(R.id.recyclerView_professional);
        radiusRadioButton = findViewById(R.id.select_radius_radio_button_professional);
        ratingRadioButton = findViewById(R.id.select_rating_radio_button_professional);
        backButton = findViewById(R.id.back_professional_button);

        rrb5 = findViewById(R.id.rating_star5_button_professional);
        rrb4 = findViewById(R.id.rating_star4_button_professional);
        rrb3 = findViewById(R.id.rating_star3_button_professional);
        rrb2 = findViewById(R.id.rating_star2_button_professional);
        rrb1 = findViewById(R.id.rating_star1_button_professional);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        String km = " km";
        pCardList = new ArrayList<>();
        pCardList.add(new ProfessionalCardModel(R.drawable.handmyy, "Hande Ercel", "4.5", "3" + km));
        pCardList.add(new ProfessionalCardModel(R.drawable.rdj, "Robert Downey Jr ", "5", "0.5" + km));
        pCardList.add(new ProfessionalCardModel(R.drawable.tome_holland, "Tom Holland", "4.5", "10" + km));
        pCardList.add(new ProfessionalCardModel(R.drawable.emma_watson, "Emma watson", "5", "2" + km));
        pCardList.add(new ProfessionalCardModel(R.drawable.elon_musk, "Elon Musk", "5", "0" + km));
        pCardList.add(new ProfessionalCardModel(R.drawable.mark_zuckerberg, "Mark Zuckerberg", "4.5", "22" + km));
        pCardList.add(new ProfessionalCardModel(R.drawable.jennifer, "Jennifer", "4.5", "4.5" + km));
        pCardList.add(new ProfessionalCardModel(R.drawable.lucifer, "Lucifer Morningstar", "5", "1.5" + km));
        pCardList.add(new ProfessionalCardModel(R.drawable.tome_cruse, "Tom Cruse", "3.5", "6" + km));
        pCardList.add(new ProfessionalCardModel(R.drawable.black_widow, "Scarlet", "4", "9" + km));
        pCardList.add(new ProfessionalCardModel(R.drawable.jeff_bezos, "Jeff Bezos", "5", "3" + km));

        adapter = new ProfessionalCardAdapter(pCardList);
        recyclerView.setAdapter(adapter);

        //back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(ProfesionalsActivity.this, MainActivity.class));
                onBackPressed();
            }
        });
    }

    // two radio button
    public void onRadioButtonClicked(View view) {
        boolean isSelected = ((AppCompatRadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.select_radius_radio_button_professional:
                if (isSelected) {
                    radiusRadioButton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                    ratingRadioButton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.partial_black));
                }
                break;
            case R.id.select_rating_radio_button_professional:
                if (isSelected) {
                    ratingRadioButton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                    radiusRadioButton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.partial_black));
                }
                break;
        }
    }

    //5 rating button
    public void onRatingRadioButtonClicked(View view) {
        boolean isSelected = ((AppCompatRadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rating_star5_button_professional:
                if (isSelected) {
                    Toast.makeText(ProfesionalsActivity.this, "Selected 5 star", Toast.LENGTH_SHORT).show();
                    rrb5.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));

                    rrb4.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb3.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb2.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb1.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                }
                break;
            case R.id.rating_star4_button_professional:
                if (isSelected) {
                    Toast.makeText(ProfesionalsActivity.this, "Selected 4 star", Toast.LENGTH_SHORT).show();
                    rrb4.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));

                    rrb5.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb3.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb2.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb1.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                }
                break;
            case R.id.rating_star3_button_professional:
                if (isSelected) {
                    Toast.makeText(ProfesionalsActivity.this, "Selected 3 star", Toast.LENGTH_SHORT).show();
                    rrb3.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));

                    rrb4.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb5.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb2.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb1.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                }
                break;
            case R.id.rating_star2_button_professional:
                if (isSelected) {
                    Toast.makeText(ProfesionalsActivity.this, "Selected 2 star", Toast.LENGTH_SHORT).show();
                    rrb2.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));

                    rrb4.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb3.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb5.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb1.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                }
                break;
            case R.id.rating_star1_button_professional:
                if (isSelected) {
                    Toast.makeText(ProfesionalsActivity.this, "Selected 1 star", Toast.LENGTH_SHORT).show();
                    rrb1.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));

                    rrb4.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb3.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb2.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                    rrb5.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.gray_50));
                }
                break;
        }
    }
}