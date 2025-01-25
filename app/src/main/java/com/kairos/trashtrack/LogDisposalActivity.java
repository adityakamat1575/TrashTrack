package com.kairos.trashtrack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LogDisposalActivity extends AppCompatActivity {
    private Spinner spinnerWasteType;
    private EditText etWasteWeight;
    private Button btnSubmitDisposal;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_disposal);

        spinnerWasteType = findViewById(R.id.spinnerWasteType);
        etWasteWeight = findViewById(R.id.etWasteWeight);
        btnSubmitDisposal = findViewById(R.id.btnSubmitDisposal);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        btnSubmitDisposal.setOnClickListener(v -> {
            String wasteType = spinnerWasteType.getSelectedItem().toString();
            String weightStr = etWasteWeight.getText().toString().trim();

            if (weightStr.isEmpty()) {
                Toast.makeText(LogDisposalActivity.this, getString(R.string.error_enter_weight), Toast.LENGTH_SHORT).show();
                return;
            }

            double weight;
            try {
                weight = Double.parseDouble(weightStr);
                if (weight <= 0) {
                    Toast.makeText(LogDisposalActivity.this, getString(R.string.error_invalid_weight), Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(LogDisposalActivity.this, getString(R.string.error_invalid_weight), Toast.LENGTH_SHORT).show();
                return;
            }

            String userId;
            if (mAuth.getCurrentUser() != null) {
                userId = mAuth.getCurrentUser().getUid();
            } else {
                Toast.makeText(LogDisposalActivity.this, getString(R.string.error_user_not_logged_in), Toast.LENGTH_SHORT).show();
                return;
            }

            int pointsEarned = PointsCalculator.calculatePoints(wasteType, weight);

            Map<String, Object> disposalData = new HashMap<>();
            disposalData.put("type", wasteType);
            disposalData.put("weight", weight);
            disposalData.put("timestamp", FieldValue.serverTimestamp());

            btnSubmitDisposal.setEnabled(false);

            db.collection("users").document(userId)
                    .collection("disposals")
                    .add(disposalData)
                    .addOnSuccessListener(documentReference -> {
                        db.collection("users").document(userId)
                                .update("points", FieldValue.increment(pointsEarned))
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(LogDisposalActivity.this, getString(R.string.success_disposal_logged, pointsEarned), Toast.LENGTH_SHORT).show();
                                    finish();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(LogDisposalActivity.this, "Failed to update points.", Toast.LENGTH_SHORT).show();
                                    btnSubmitDisposal.setEnabled(true);
                                });
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(LogDisposalActivity.this, "Failed to log disposal.", Toast.LENGTH_SHORT).show();
                        btnSubmitDisposal.setEnabled(true);
                    });
        });


    }

}
