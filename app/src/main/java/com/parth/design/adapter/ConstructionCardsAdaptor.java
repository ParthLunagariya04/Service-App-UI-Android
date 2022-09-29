package com.parth.design.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parth.design.R;
import com.parth.design.model.ConstructionCardsModel;

import java.util.List;

public class ConstructionCardsAdaptor extends RecyclerView.Adapter<ConstructionCardsAdaptor.ConstructionCardsViewHolder> {
    private List<ConstructionCardsModel> constructionCardsList;

    public ConstructionCardsAdaptor(List<ConstructionCardsModel> constructionCardsList) {
        this.constructionCardsList = constructionCardsList;
    }

    public void setFilteredList(List<ConstructionCardsModel> filteredList){
        this.constructionCardsList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ConstructionCardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.construction_cards_view, parent, false);
        return new ConstructionCardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConstructionCardsViewHolder holder, int position) {
        holder.catagory.setText(constructionCardsList.get(position).getCategoryName());
        holder.imageView.setImageResource(constructionCardsList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return constructionCardsList.size();
    }

    public static class ConstructionCardsViewHolder extends RecyclerView.ViewHolder {
        private final TextView catagory;
        private final ImageView imageView;

        public ConstructionCardsViewHolder(@NonNull View itemView) {
            super(itemView);

            catagory = itemView.findViewById(R.id.construction_textView);
            imageView = itemView.findViewById(R.id.construction_card_imageview);
        }
    }

}
