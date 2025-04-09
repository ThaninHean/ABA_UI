package com.example.aba_bank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aba_bank.R;
import com.example.aba_bank.ui.adapter.AnnouncementAdapter;
import com.example.aba_bank.ui.adapter.TransactionAdapter;
import com.example.aba_bank.ui.model.Announcements;
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
        setupRecyclerView(getTransactions()); // Default content
        handleBottomSheetDismiss();

        bottomSheetDialog.show();
    }

    private void setupBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_notifications, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        recyclerView = bottomSheetView.findViewById(R.id.recyclerViewTransactions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bottomSheetDialog.setOnDismissListener(dialog -> navigateBackToMain());
    }

    private void setupRecyclerView(Object items) {
        if (items instanceof List<?>) {
            if (!((List<?>) items).isEmpty()) {
                Object firstItem = ((List<?>) items).get(0);
                if (firstItem instanceof Transaction) {
                    recyclerView.setAdapter(new TransactionAdapter((List<Transaction>) items));
                } else if (firstItem instanceof Announcements) {
                    recyclerView.setAdapter(new AnnouncementAdapter((List<Announcements>) items));
                }
            }
        }
    }

    private void setupTabLayout() {
        tabLayout = bottomSheetView.findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        setupRecyclerView(getMyAlerts());
                        break;
                    case 1:
                        setupRecyclerView(getTransactions());
                        break;
                    case 2:
                        setupRecyclerView(getAnnouncements());
                        break;
                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private List<Transaction> getMyAlerts() {
        List<Transaction> list = new ArrayList<>();
        String title = "ABA Bank";
        list.add(new Transaction(title, "Your account was accessed at 3:18 PM", "3:18 PM"));
        list.add(new Transaction(title, "Profile updated successfully", "1:20 PM"));
        list.add(new Transaction(title, "Your account was accessed at 3:18 PM", "3:18 PM"));
        list.add(new Transaction(title, "Profile updated successfully", "1:20 PM"));
        return list;
    }

    private List<Transaction> getTransactions() {
        List<Transaction> list = new ArrayList<>();
        list.add(new Transaction("SEM CHIM", "50.00 USD paid from account", "3:18 PM"));
        list.add(new Transaction("Ke SeangLeng", "5,000.00 KHR paid from account", "7:18 PM"));
        list.add(new Transaction("SEM CHIM", "50.00 USD paid from account", "3:18 PM"));
        return list;
    }

    private List<Announcements> getAnnouncements() {
        List<Announcements> list = new ArrayList<>();
        list.add(new Announcements("15 AUG 2024", R.drawable.banner1, "ឱ្យអ្នកស្គាល់ទាំងអស់ប្រើABA បានលុយ", "បាន ៥០០០ រៀល ពេលណែនាំមិត្តភក្កិ គ្រួសារ ឬអ្នកណាម្នាក់"));
        list.add(new Announcements("13 FEB 2025", R.drawable.banner2, "ជំនួយការផ្ទាល់ខ្លួនរបស់អ្នក", "ដឹងអត់? អ្នកមានជំនួយការផ្ទាល់មួយ សម្រាប់បម្រើសេវាកម្ម"));
        list.add(new Announcements("02 MAR 2025", R.drawable.banner3, "Celebrate joy & prosperity with ABA", "Get ready for a joyful Khmer New Year with"));
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

                @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {}
            });
        }
    }

    private void navigateBackToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
            bottomSheetDialog.dismiss(); // Triggers navigateBackToMain
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("NotificationActivity", "onDestroy called");
    }
}
