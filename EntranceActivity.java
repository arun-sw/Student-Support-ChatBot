package com.example.studentsupportchartbot;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class EntranceActivity extends AppCompatActivity {

    CardView userCard;
    CardView adminCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        userCard = findViewById(R.id.cardUser);
        adminCard = findViewById(R.id.cardAdmin);

        userCard.setOnClickListener(v -> {
            Intent intent = new Intent(EntranceActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        adminCard.setOnClickListener(v -> {
            Intent intent = new Intent(EntranceActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
