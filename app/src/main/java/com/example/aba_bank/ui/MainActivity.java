package com.example.aba_bank.ui;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.aba_bank.ui.model.NewsPromotions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayoutProfile;
    private NewsPromotionsAdapter adapter;
    private RecyclerView recyclerView;
    private boolean isBalanceVisible = true;

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

        openProfileActivity();
        toggleBalanceVisibility();
        initRecyclerView();
    }

    public void toggleBalanceVisibility() {
        ImageView eyeIcon = findViewById(R.id.eyeOn);
        TextView balance = findViewById(R.id.showBalance);
        ImageView balanceImage = findViewById(R.id.balanceImage);

        if (isBalanceVisible) {
            balance.setVisibility(View.VISIBLE);
            balanceImage.setVisibility(View.GONE);
            eyeIcon.setImageResource(R.drawable.ic_eyes);
        } else {
            balance.setVisibility(View.GONE);
            balanceImage.setVisibility(View.VISIBLE);
            eyeIcon.setImageResource(R.drawable.ic_eye_off);
        }

        eyeIcon.setOnClickListener(view -> {
            if (isBalanceVisible) {
                balance.setVisibility(View.GONE);
                balanceImage.setVisibility(View.VISIBLE);
                eyeIcon.setImageResource(R.drawable.ic_eye_off);
            } else {
                balance.setVisibility(View.VISIBLE);
                balanceImage.setVisibility(View.GONE);
                balance.setText("$1,000");
                eyeIcon.setImageResource(R.drawable.ic_eyes);
            }
            isBalanceVisible = !isBalanceVisible;
        });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<NewsPromotions> list = getNewPromotionsImage();
        adapter = new NewsPromotionsAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    private List<NewsPromotions> getNewPromotionsImage() {
        List<NewsPromotions> list = new ArrayList<>();
        list.add(new NewsPromotions(R.drawable.image2)); // Drawable resource ID
        list.add(new NewsPromotions(R.drawable.image3));
        list.add(new NewsPromotions(R.drawable.image4));
        list.add(new NewsPromotions(R.drawable.image5));
        list.add(new NewsPromotions(R.drawable.image6));
        return list;
    }

    public void openProfileActivity() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.chat) {
            openActivity(ChatActivity.class, "Chat");
        } else if (id == R.id.notification) {
            openActivity(NotificationActivity.class, "Notification");
        } else if (id == R.id.ic_qr) {
            openActivity(QrActivity.class, "QR");
        }
        return super.onOptionsItemSelected(item);
    }

    private void openActivity(Class<?> activityClass, String message) {
        startActivity(new Intent(this, activityClass));
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
