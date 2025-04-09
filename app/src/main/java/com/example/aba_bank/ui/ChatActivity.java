package com.example.aba_bank.ui;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aba_bank.R;

public class ChatActivity extends AppCompatActivity {
    ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);
        btn_back = findViewById(R.id.ic_back);
        btn_back.setOnClickListener(view -> {
            Intent intent = new Intent(ChatActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inchat, menu);
        return true;
    }
}
