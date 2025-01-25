package com.kairos.trashtrack;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerHistory;
    private HistoryAdapter adapter;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private List<Disposal> disposals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerHistory = findViewById(R.id.recyclerHistory);
        recyclerHistory.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        disposals = new ArrayList<>();
        adapter = new HistoryAdapter(disposals);
        recyclerHistory.setAdapter(adapter);

        loadHistory();
    }

    private void loadHistory() {
        String userId = mAuth.getCurrentUser().getUid();
        db.collection("users").document(userId).collection("disposals")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    disposals.clear();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String type = document.getString("type");
                        double weight = document.getDouble("weight");
                        String timestamp = document.getTimestamp("timestamp").toDate().toString();
                        disposals.add(new Disposal(type, weight, timestamp));
                    }
                    adapter.notifyDataSetChanged();
                });
    }
}
