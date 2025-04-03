package com.example.aba_bank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aba_bank.R;
import com.example.aba_bank.ui.adapter.NewsPromotionsAdapter;
import com.example.aba_bank.ui.adapter.ServiceAdapter;
import com.example.aba_bank.ui.model.NewsPromotions;
import com.example.aba_bank.ui.model.Services;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayoutProfile;
    private NewsPromotionsAdapter adapter;
    private ServiceAdapter serviceAdapter;
    private RecyclerView recyclerViewNews, recyclerViewExplore;
    private boolean isBalanceVisible = true;
    private Handler handler = new Handler();
    private int currentPosition = 0;
    private List<NewsPromotions> newsList; // Store images in a variable to prevent recreation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            v.setPadding(0, insets.getSystemWindowInsetTop(), 0, 0);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Initialize methods
        toggleBalanceVisibility();
        openProfileActivity();
        initNewsRecyclerView();
        initServiceRecyclerView();
        startAutoScroll();
    }
    public void openProfileActivity() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });
    }

    private void startAutoScroll() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (newsList == null || newsList.isEmpty()) return;

                if (currentPosition >= newsList.size()) {
                    currentPosition = 0;
                }
                recyclerViewNews.smoothScrollToPosition(currentPosition);
                currentPosition++;
                handler.postDelayed(this, 2000);
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    private void initServiceRecyclerView() {
        recyclerViewExplore = findViewById(R.id.recyclerViewExplore);
        recyclerViewExplore.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        serviceAdapter = new ServiceAdapter(this, getServicesImage());
        recyclerViewExplore.setAdapter(serviceAdapter);
    }

    private List<Services> getServicesImage() {
        List<Services> servicesList = new ArrayList<>();
        servicesList.add(new Services(R.drawable.vet, "VET Express"));
        servicesList.add(new Services(R.drawable.bookmebus, "BookMeBus"));
        return servicesList;
    }

    private void toggleBalanceVisibility() {
        ImageView eyeIcon = findViewById(R.id.eyeOn);
        TextView balance = findViewById(R.id.showBalance);
        ImageView balanceImage = findViewById(R.id.balanceImage);

        updateBalanceUI(eyeIcon, balance, balanceImage);

        eyeIcon.setOnClickListener(view -> {
            isBalanceVisible = !isBalanceVisible;
            updateBalanceUI(eyeIcon, balance, balanceImage);
        });
    }

    private void updateBalanceUI(ImageView eyeIcon, TextView balance, ImageView balanceImage) {
        if (isBalanceVisible) {
            balance.setVisibility(View.VISIBLE);
            balanceImage.setVisibility(View.GONE);
            balance.setText("$1,000");
            eyeIcon.setImageResource(R.drawable.ic_eyes);
        } else {
            balance.setVisibility(View.GONE);
            balanceImage.setVisibility(View.VISIBLE);
            eyeIcon.setImageResource(R.drawable.ic_eye_off);
        }
    }

    private void initNewsRecyclerView() {
        recyclerViewNews = findViewById(R.id.recyclerViewNews);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Store images once and reuse them
        newsList = getNewPromotionsImage();
        adapter = new NewsPromotionsAdapter(this, newsList);
        recyclerViewNews.setAdapter(adapter);

        // Handle item click
        adapter.setOnItemClickListener(position -> {
            Toast.makeText(MainActivity.this, "Clicked item: " + position, Toast.LENGTH_SHORT).show();
        });
    }

    private List<NewsPromotions> getNewPromotionsImage() {
        List<NewsPromotions> list = new ArrayList<>();
        list.add(new NewsPromotions(R.drawable.image2));
        list.add(new NewsPromotions(R.drawable.image3));
        list.add(new NewsPromotions(R.drawable.image4));
        list.add(new NewsPromotions(R.drawable.image5));
        list.add(new NewsPromotions(R.drawable.image6));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            openProfileActivity();
            return true;
        } else if (id == R.id.chat) {
            openActivity(ChatActivity.class, "Chat");
        } else if (id == R.id.notification) {
            openActivity(NotificationActivity.class, "Notification");
        } else if (id == R.id.ic_qr) {
            openActivity(QrActivity.class, "QR");
        }
        return super.onOptionsItemSelected(item);
    }

//    private void openProfileActivity() {
//        Intent intent = new Intent(this, ProfileActivity.class);
//        startActivity(intent);
//    }

    private void openActivity(Class<?> activityClass, String message) {
        startActivity(new Intent(this, activityClass));
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
