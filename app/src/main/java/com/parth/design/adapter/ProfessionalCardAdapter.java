package com.parth.design.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parth.design.R;
import com.parth.design.model.ProfessionalCardModel;

import java.util.List;

public class ProfessionalCardAdapter extends RecyclerView.Adapter<ProfessionalCardAdapter.ProfessionalCardViewHolder> {
    private final List<ProfessionalCardModel> professionalCardList;

    public ProfessionalCardAdapter(List<ProfessionalCardModel> professionalCardList) {
        this.professionalCardList = professionalCardList;
    }

    @NonNull
    @Override
    public ProfessionalCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.professionals_cards_list, parent, false);
        return new ProfessionalCardAdapter.ProfessionalCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessionalCardViewHolder holder, int position) {
        holder.person_name.setText(professionalCardList.get(position).getPersonName());
        holder.rating_text.setText(professionalCardList.get(position).getRatingText());
        holder.distance_text.setText(professionalCardList.get(position).getDistanceText());
        holder.professionalCardImage.setImageResource(professionalCardList.get(position).getProfessionalCardImage());
    }

    @Override
    public int getItemCount() {
        return professionalCardList.size();
    }

    public static class ProfessionalCardViewHolder extends RecyclerView.ViewHolder {
        private final ImageView professionalCardImage;
        private final TextView person_name, rating_text, distance_text;

        public ProfessionalCardViewHolder(@NonNull View itemView) {
            super(itemView);

            professionalCardImage = itemView.findViewById(R.id.imageView_professionalCard);
            person_name = itemView.findViewById(R.id.personName_textView_professionalCard);
            rating_text = itemView.findViewById(R.id.rating_textView_professionalCard);
            distance_text = itemView.findViewById(R.id.distance_textView_professionalCard);
        }
    }
}
