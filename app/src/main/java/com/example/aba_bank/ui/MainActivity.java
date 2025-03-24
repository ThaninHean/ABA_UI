package com.example.aba_bank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.aba_bank.R;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayoutProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Edge-to-edge support (ensure this works across devices)
        EdgeToEdge.enable(this);

        // Set the layout
        setContentView(R.layout.activity_main);

        // Initialize profile layout interaction
        openProfileActivity();

    }

    // Intent to open Profile Activity
    public void openProfileActivity() {
        constraintLayoutProfile = findViewById(R.id.LayoutProfile);
        constraintLayoutProfile.setOnClickListener(v -> {
            // Open Profile Activity when the layout is clicked
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });
    }

    // Menu Creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it's present
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }

    // Handling Menu Item Clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle menu item clicks
        if (id == R.id.chat) {
            // Open Chat Activity
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Chat", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.notification) {
            // Open Notification Activity
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Notification", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.ic_qr) {
            // Open QR Activity
            Intent intent = new Intent(this, QrActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "QR", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }


}
