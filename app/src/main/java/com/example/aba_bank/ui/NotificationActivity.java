package com.example.aba_bank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aba_bank.R;
import com.example.aba_bank.ui.adapter.TransactionAdapter;
import com.example.aba_bank.ui.model.Transaction;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);

        // Setup BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(NotificationActivity.this);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_notifications, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        // Setup RecyclerView inside BottomSheet
        RecyclerView recyclerView = bottomSheetView.findViewById(R.id.recyclerViewTransactions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Mock transaction list
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction("Ke SeangLeng", "5,000.00 KHR is paid from account", "7:18 PM"));
        transactionList.add(new Transaction("SEM CHIM", "50.00 USD is paid from account", "3:18 PM"));
        transactionList.add(new Transaction("Ke SeangLeng", "5,000.00 KHR is paid from account", "7:18 PM"));
        transactionList.add(new Transaction("SEM CHIM", "50.00 USD is paid from account", "3:18 PM"));
        // Set adapter
        TransactionAdapter adapter = new TransactionAdapter(transactionList);
        recyclerView.setAdapter(adapter);

        // Handle swipe down to dismiss and return to MainActivity
        View internalSheet = bottomSheetDialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (internalSheet != null) {
            BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(internalSheet);
            behavior.setHideable(true);

            behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        bottomSheetDialog.dismiss();

                        // Navigate back to MainActivity
                        Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    // Optional: Handle dragging animation
                }
            });
        }

        // Show bottom sheet
        bottomSheetDialog.show();
    }
}
