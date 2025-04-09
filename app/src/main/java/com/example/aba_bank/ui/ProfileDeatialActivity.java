package com.example.aba_bank.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aba_bank.R;

public class ProfileDeatialActivity extends AppCompatActivity {
    private ImageView imageProfile, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_deatial);
        imageProfile = findViewById(R.id.imageProfile);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> onBackPressed());
        int imageResId = getIntent().getIntExtra("imageResId", R.drawable.profile); // fallback
        imageProfile.setImageResource(imageResId);

    }
}
