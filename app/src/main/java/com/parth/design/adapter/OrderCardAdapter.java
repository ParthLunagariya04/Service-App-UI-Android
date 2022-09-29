package com.parth.design.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parth.design.R;
import com.parth.design.model.OrderCardModel;

import java.util.List;

public class OrderCardAdapter extends RecyclerView.Adapter<OrderCardAdapter.OrderCardViewHolder> {
    private final List<OrderCardModel> orderCardList;

    public OrderCardAdapter(List<OrderCardModel> orderCardList) {
        this.orderCardList = orderCardList;
    }

    @NonNull
    @Override
    public OrderCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_cards, parent, false);
        return new OrderCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCardViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);

        holder.imageView.setImageResource(orderCardList.get(position).getImage());
        holder.userNameOrder1.setText(orderCardList.get(position).getUserNameOrder());
        holder.mainCategoryName1.setText(orderCardList.get(position).getMainCategoryName());
        holder.subCategoryName1.setText(orderCardList.get(position).getSubCategoryName());
        holder.date1.setText(orderCardList.get(position).getDate());
        holder.stateOfOrder1.setText(orderCardList.get(position).getStateOfOrder());

        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return orderCardList.size();
    }

    public static class OrderCardViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView userNameOrder1, mainCategoryName1, subCategoryName1, date1, stateOfOrder1;

        public OrderCardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.order_cards_imageView);
            userNameOrder1 = itemView.findViewById(R.id.userName_order_textView);
            mainCategoryName1 = itemView.findViewById(R.id.mainCategory_order_textView);
            subCategoryName1 = itemView.findViewById(R.id.subCategory_order_textView);
            date1 = itemView.findViewById(R.id.date_order_textView);
            stateOfOrder1 = itemView.findViewById(R.id.state_order_textView);
        }
    }
}
