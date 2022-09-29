package com.parth.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.parth.design.R;

public class GeneralCardViewPager extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.image3, R.drawable.image1, R.drawable.image2, R.drawable.image4};

    public GeneralCardViewPager(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
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
        View view = layoutInflater.inflate(R.layout.general_cards_view, null);
        ImageView imageView = view.findViewById(R.id.general_card_imageview);
        imageView.setImageResource(images[position]);
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
