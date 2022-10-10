package com.parth.design.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parth.design.CategoriesActivity;
import com.parth.design.R;
import com.parth.design.adapter.ConstructionCardsAdaptor;
import com.parth.design.adapter.GeneralCardViewPager;
import com.parth.design.apiController.ApiController;
import com.parth.design.model.ConstructionCardsModel;
import com.parth.design.model.RetrofitModelWithList;
import com.parth.design.model.RetrofitModel;
import com.parth.design.model.RetrofitModelWithList;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    final long DELAY_MS = 400;
    final long PERIOD_MS = 2500;
    CircleIndicator circleIndicator;
    ViewPager viewPager;
    GeneralCardViewPager generalCardViewPager;
    LinearLayout cl, el, dl;
    TextView constructionTextView, electricTextView, driverTextView;
    int currentPage = 0;
    Timer timer;
    private RecyclerView constructionCardRecyclerView, electricCardRecyclerView, driverCardRecyclerView;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        circleIndicator = view.findViewById(R.id.circle_indicator);
        viewPager = view.findViewById(R.id.general_cards_viewpager);

        headerList();

        //construction, electric, driver Text views data passing to Category Activity
        constructionTextView = view.findViewById(R.id.construction_textView);
        electricTextView = view.findViewById(R.id.electric_textView);
        driverTextView = view.findViewById(R.id.driver_textView);

        // construction Recyclerview
        constructionCardRecyclerView = view.findViewById(R.id.construction_cards_recyclerView);
        constructionCardRecyclerView.setHasFixedSize(true);
        constructionCardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<ConstructionCardsModel> constructionCardsList = new ArrayList<>();
        constructionCardsList.add(new ConstructionCardsModel(R.drawable.image2, "Category 1"));
        constructionCardsList.add(new ConstructionCardsModel(R.drawable.image4, "Category 2"));
        constructionCardsList.add(new ConstructionCardsModel(R.drawable.image5, "Category 3"));
        constructionCardsList.add(new ConstructionCardsModel(R.drawable.image6, "Category 4"));
        constructionCardsList.add(new ConstructionCardsModel(R.drawable.image7, "Category 5"));

        ConstructionCardsAdaptor constructionCardsAdaptor = new ConstructionCardsAdaptor(constructionCardsList);
        constructionCardRecyclerView.setAdapter(constructionCardsAdaptor);

        // electric Recyclerview
        electricCardRecyclerView = view.findViewById(R.id.electric_cards_recyclerview);
        electricCardRecyclerView.setHasFixedSize(true);
        electricCardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<ConstructionCardsModel> electricCardList = new ArrayList<>();
        electricCardList.add(new ConstructionCardsModel(R.drawable.image8, "Category 1"));
        electricCardList.add(new ConstructionCardsModel(R.drawable.image9, "Category 2"));
        electricCardList.add(new ConstructionCardsModel(R.drawable.image10, "Category 3"));
        electricCardList.add(new ConstructionCardsModel(R.drawable.image6, "Category 4"));
        electricCardList.add(new ConstructionCardsModel(R.drawable.image7, "Category 5"));

        ConstructionCardsAdaptor electricCardsAdaptor = new ConstructionCardsAdaptor(electricCardList);
        electricCardRecyclerView.setAdapter(electricCardsAdaptor);


        // driver Recyclerview
        driverCardRecyclerView = view.findViewById(R.id.driver_cards_recyclerview);
        driverCardRecyclerView.setHasFixedSize(true);
        driverCardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<ConstructionCardsModel> driverCardsList = new ArrayList<>();
        driverCardsList.add(new ConstructionCardsModel(R.drawable.image11, "Category 1"));
        driverCardsList.add(new ConstructionCardsModel(R.drawable.image12, "Category 2"));
        driverCardsList.add(new ConstructionCardsModel(R.drawable.image10, "Category 3"));
        driverCardsList.add(new ConstructionCardsModel(R.drawable.image9, "Category 4"));
        driverCardsList.add(new ConstructionCardsModel(R.drawable.image8, "Category 5"));

        ConstructionCardsAdaptor driverCardAdapter = new ConstructionCardsAdaptor(driverCardsList);
        driverCardRecyclerView.setAdapter(driverCardAdapter);

        cl = view.findViewById(R.id.construction_viewAll_linearLayout_home);
        el = view.findViewById(R.id.electric_viewAll_linearLayout_home);
        dl = view.findViewById(R.id.driver_viewAll_linearLayout_home);

        //construction, electric & electronic, driver VIEW ALL clicking
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategoriesActivity.class);
                intent.putExtra("key", constructionTextView.getText().toString());
                startActivity(intent);
            }
        });

        el.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategoriesActivity.class);
                intent.putExtra("key", electricTextView.getText().toString());
                startActivity(intent);
            }
        });

        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategoriesActivity.class);
                intent.putExtra("key", driverTextView.getText().toString());
                startActivity(intent);
            }
        });

        return view;
    }

    //list header View pager automatic swipe
    private void headerList() {
        //api calling
        try {
            Call<RetrofitModelWithList> call = ApiController.getInstance().getRetrofit().getViewPagerData("0");
            call.enqueue(new Callback<RetrofitModelWithList>() {
                @Override
                public void onResponse(@NonNull Call<RetrofitModelWithList> call, @NonNull Response<RetrofitModelWithList> response) {

                    if (response.isSuccessful()) {
                        //viewPager
                        generalCardViewPager = new GeneralCardViewPager(getContext(), response.body());
                        viewPager.setAdapter(generalCardViewPager);
                        viewPager.setPadding(40, 40, 150, 40);
                        viewPager.setPageMargin(50);
                        circleIndicator.setViewPager(viewPager);

                        //automatic change view pager
                        final Handler handler = new Handler();
                        final Runnable Update = new Runnable() {
                            @Override
                            public void run() {
                                if (currentPage == generalCardViewPager.getCount()) {
                                    currentPage = 0;
                                }
                                viewPager.setCurrentItem(currentPage++, true);
                            }
                        };
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.post(Update);
                            }
                        }, DELAY_MS, PERIOD_MS);

                    } else {
                        Log.d("MyLogData", "no image - " + response.message());
                        Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RetrofitModelWithList> call, Throwable t) {
                    Log.d("MyLogData", "error - " + t.getMessage());
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("parth", " MY ERRORS " + e);
        }
    }
}
