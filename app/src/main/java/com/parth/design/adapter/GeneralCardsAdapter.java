package com.parth.design.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parth.design.R;
import com.parth.design.model.GeneralCardsModel;

import java.util.List;

public class GeneralCardsAdapter extends RecyclerView.Adapter<GeneralCardsAdapter.GeneralCardsViewHolder> {
    private final List<GeneralCardsModel> generalCardsList;

    public GeneralCardsAdapter(List<GeneralCardsModel> generalCardsModelsList) {
        this.generalCardsList = generalCardsModelsList;
    }

    @NonNull
    @Override
    public GeneralCardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_cards_view, parent, false);
        return new GeneralCardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralCardsViewHolder holder, int position) {
        holder.imageView.setImageResource(generalCardsList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return generalCardsList.size();
    }

    public static class GeneralCardsViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public GeneralCardsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.construction_card_imageview);
        }
    }

}
