package com.example.aba_bank.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.aba_bank.R;

public class ProfileActivity extends AppCompatActivity {
    private TextView btnBack;
    private CardView cardViewProfile;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        btnBack = findViewById(R.id.close);
        cardViewProfile = findViewById(R.id.cardProfile);
        cardViewProfile.setOnClickListener(v ->{
            Intent intent = new Intent(ProfileActivity.this, ProfileDeatialActivity.class);
            intent.putExtra("iamgeProfle", R.id.imageProfile);
            startActivity(intent);
        });
        btnBack.setOnClickListener(V -> onBackPressed());

    }
}