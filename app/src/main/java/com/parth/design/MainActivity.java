package com.parth.design;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.BundleCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.parth.design.fragments.ContactFragment;
import com.parth.design.fragments.FeedBackFragment;
import com.parth.design.fragments.HomeFragment;
import com.parth.design.fragments.OrdersFragment;
import com.parth.design.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    @SuppressLint({"NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        //getWindow().setStatusBarColor(Color.WHITE);

        // BOTTOM NAVIGATION VIEW
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //code for badge
        int menuItemId = bottomNavigationView.getMenu().getItem(3).getItemId();
        BadgeDrawable badge = bottomNavigationView.getOrCreateBadge(menuItemId);
        badge.setNumber(2);

        //swipe using fragments
        /*viewPager = findViewById(R.id.fragment_viewPager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);*/


        // default home fragment
        loadFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_icon_id:

                        loadFragment(new HomeFragment());
                        item.isChecked();
                        break;

                    case R.id.profile_icon_id:
                        loadFragment(new ProfileFragment());
                        item.isChecked();
                        break;

                    case R.id.contact_icon_id:
                        loadFragment(new ContactFragment());
                        item.isChecked();
                        break;

                    case R.id.feed_back_icon_id:
                        loadFragment(new FeedBackFragment());
                        item.isChecked();
                        break;

                    case R.id.order_icon_id:
                        loadFragment(new OrdersFragment());
                        item.isChecked();
                        break;
                }

                return false;
            }
        });
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.nav_frame_layout1, fragment).
                addToBackStack(null).
                commit();
    }
}