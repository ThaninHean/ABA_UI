package com.example.aba_bank.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aba_bank.R;
import com.example.aba_bank.ui.model.NewsPromotions;

import java.util.List;

public class NewsPromotionsAdapter extends RecyclerView.Adapter<NewsPromotionsAdapter.ViewHolder> {
    private Context context;
    private List<NewsPromotions> newsPromotionsList;

    public NewsPromotionsAdapter(Context context, List<NewsPromotions> newsPromotionsList) {
        this.context = context;
        this.newsPromotionsList = newsPromotionsList;
    }

    @NonNull
    @Override
    public NewsPromotionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_promotions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsPromotionsAdapter.ViewHolder holder, int position) {
        NewsPromotions newsPromotions = newsPromotionsList.get(position);
        holder.newsImage.setImageResource(newsPromotions.getImageNews());
    }

    @Override
    public int getItemCount() {
        return newsPromotionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.newsImage);
        }
    }
}
