package com.example.aba_bank.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aba_bank.R;
import com.example.aba_bank.ui.model.Services;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    private Context context;
    private List<Services> servicesList;

    public ServiceAdapter(Context context, List<Services> servicesList) {
        this.context = context;
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ViewHolder holder, int position) {
        Services services = servicesList.get(position);
        holder.title.setText(services.getTitle());
        Glide.with(context)
                .load(services.getImageId())
                .into(holder.imageServices);
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageServices;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageServices = itemView.findViewById(R.id.imageService);
            title = itemView.findViewById(R.id.title);
        }
    }
}
