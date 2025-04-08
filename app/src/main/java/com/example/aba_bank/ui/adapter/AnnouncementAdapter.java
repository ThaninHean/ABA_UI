package com.example.aba_bank.ui.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aba_bank.R;
import com.example.aba_bank.ui.model.Announcements;

import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {
    private List<Announcements> listAnnountments;

    public AnnouncementAdapter(List<Announcements> listAnnountments) {
        this.listAnnountments = listAnnountments;
    }

    @NonNull
    @Override
    public AnnouncementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementAdapter.ViewHolder holder, int position) {
        Announcements announcements = listAnnountments.get(position);
        holder.txtTitle.setText(announcements.getTitle());
        holder.txtDate.setText(announcements.getDmy());
        holder.textDescription.setText(announcements.getTxtDescription());

        Glide.with(holder.itemView.getContext()).load(announcements.getImages()).into(holder.bannerImage);

    }

    @Override
    public int getItemCount() {
        return listAnnountments != null ? listAnnountments.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bannerImage;
        TextView txtTitle, txtDate,textDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.textDate);
            bannerImage = itemView.findViewById(R.id.bannerImage);
            txtTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.txtDescription);

        }
    }
}
