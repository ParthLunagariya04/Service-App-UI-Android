package com.parth.design.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.parth.design.R;
import com.parth.design.model.RetrofitModelWithList;

public class GeneralCardViewPager extends PagerAdapter {

    private final Context context;
    RetrofitModelWithList list;
    private LayoutInflater layoutInflater;

    public GeneralCardViewPager(Context context, RetrofitModelWithList list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.getdata().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
        @SuppressLint("InflateParams")
        View view = layoutInflater.inflate(R.layout.general_cards_view, null);
        ImageView imageView = view.findViewById(R.id.general_card_imageview);
        Glide.with(context).load("http://143.110.246.84/arbab/" + list.getdata().get(position).getImage()).into(imageView);
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
