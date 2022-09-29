package com.parth.design.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.parth.design.fragments.ContactFragment;
import com.parth.design.fragments.FeedBackFragment;
import com.parth.design.fragments.HomeFragment;
import com.parth.design.fragments.OrdersFragment;
import com.parth.design.fragments.ProfileFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeFragment();
        } else if (position == 1) {
            return new ProfileFragment();
        } else if (position == 2) {
            return new ContactFragment();
        } else if (position == 3) {
            return new FeedBackFragment();
        } else {
            return new OrdersFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
