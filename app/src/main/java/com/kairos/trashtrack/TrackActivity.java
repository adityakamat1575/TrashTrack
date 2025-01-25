package com.kairos.trashtrack;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class TrackActivity extends AppCompatActivity {
    private TextView tvWelcome, tvPoints;
    private Button btnLogDisposal, btnHistory;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvPoints = findViewById(R.id.tvPoints);
        btnLogDisposal = findViewById(R.id.btnLogDisposal);
        btnHistory = findViewById(R.id.btnHistory);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        String userId = mAuth.getCurrentUser().getUid();
        db.collection("users").document(userId).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String name = documentSnapshot.getString("name");
                int points = documentSnapshot.getLong("points").intValue();
                tvWelcome.setText("Welcome, " + name + "!");
                tvPoints.setText("Points: " + points);
            }
        });

        btnLogDisposal.setOnClickListener(view -> {
            Intent intent = new Intent(TrackActivity.this, LogDisposalActivity.class);
            startActivity(intent);
        });

        btnHistory.setOnClickListener(view -> {
            Intent intent = new Intent(TrackActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }
}