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
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetView;
    private RecyclerView recyclerView;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setupBottomSheet();
        setupTabLayout();
        setupRecyclerView();
        handleBottomSheetDismiss();
        bottomSheetDialog.show();
    }

    private void setupBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(NotificationActivity.this);
        bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_notifications, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        // ✅ Handles back button and outside touch dismiss
        bottomSheetDialog.setOnDismissListener(dialog -> navigateBackToMain());
    }

    private void setupRecyclerView() {
        recyclerView = bottomSheetView.findViewById(R.id.recyclerViewTransactions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TransactionAdapter(getMyAlerts()));
    }

    private void setupTabLayout() {
        tabLayout = bottomSheetView.findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        recyclerView.setAdapter(new TransactionAdapter(getMyAlerts()));
                        break;
                    case 1:
                        recyclerView.setAdapter(new TransactionAdapter(getTransactions()));
                        break;
                    case 2:
                        recyclerView.setAdapter(new TransactionAdapter(getAnnouncements()));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private List<Transaction> getMyAlerts() {
        List<Transaction> list = new ArrayList<>();
        list.add(new Transaction("ABA Bank", "Your account was accessed at 3:18 PM", "3:18 PM"));
        list.add(new Transaction("ABA Bank", "Profile updated successfully", "1:20 PM"));
        list.add(new Transaction("ABA Bank", "Your account was accessed at 3:18 PM", "3:18 PM"));
        list.add(new Transaction("ABA Bank", "Profile updated successfully", "1:20 PM"));
        list.add(new Transaction("ABA Bank", "Your account was accessed at 3:18 PM", "3:18 PM"));
        list.add(new Transaction("ABA Bank", "Profile updated successfully", "1:20 PM"));
        return list;
    }

    private List<Transaction> getTransactions() {
        List<Transaction> list = new ArrayList<>();
        list.add(new Transaction("SEM CHIM", "50.00 USD paid from account", "3:18 PM"));
        list.add(new Transaction("Ke SeangLeng", "5,000.00 KHR paid from account", "7:18 PM"));
        list.add(new Transaction("SEM CHIM", "50.00 USD paid from account", "3:18 PM"));
        list.add(new Transaction("Ke SeangLeng", "5,000.00 KHR paid from account", "7:18 PM"));
        list.add(new Transaction("SEM CHIM", "50.00 USD paid from account", "3:18 PM"));
        list.add(new Transaction("Ke SeangLeng", "5,000.00 KHR paid from account", "7:18 PM"));
        return list;
    }

    private List<Transaction> getAnnouncements() {
        List<Transaction> list = new ArrayList<>();
        list.add(new Transaction("ABA Bank", "System maintenance on April 10", "Yesterday"));
        list.add(new Transaction("ABA Bank", "New feature: Scan QR to pay", "2 days ago"));
        list.add(new Transaction("ABA Bank", "System maintenance on April 10", "Yesterday"));
        list.add(new Transaction("ABA Bank", "New feature: Scan QR to pay", "2 days ago"));
        list.add(new Transaction("ABA Bank", "System maintenance on April 10", "Yesterday"));
        list.add(new Transaction("ABA Bank", "New feature: Scan QR to pay", "2 days ago"));
        return list;
    }

    private void handleBottomSheetDismiss() {
        View internalSheet = bottomSheetDialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (internalSheet != null) {
            BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(internalSheet);
            behavior.setHideable(true);
            behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        bottomSheetDialog.dismiss();
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {}
            });
        }
    }

    private void navigateBackToMain() {
        Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
            bottomSheetDialog.dismiss(); // onDismiss will call navigateBackToMain()
        } else {
            super.onBackPressed();
        }
    }

}
