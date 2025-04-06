package com.example.aba_bank.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aba_bank.R;
import com.example.aba_bank.ui.model.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    List<Transaction> transactions;

    public TransactionAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.name.setText(transaction.getName());
        holder.amount.setText(transaction.getAmount());
        holder.time.setText(transaction.getTime());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, amount, time;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            amount = itemView.findViewById(R.id.txtAmount);
            time = itemView.findViewById(R.id.txtTime);
        }
    }
}

