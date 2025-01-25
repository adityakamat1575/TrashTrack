package com.kairos.trashtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;

public class DiscussionListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DiscussionAdapter adapter;
    private ArrayList<Discussion> discussions;
    private FirebaseFirestore firestore;
    private Button addDiscussionButton;
    private ProgressBar progressBar;
    private TextView emptyStateText;
    private ListenerRegistration listenerRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_list);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        addDiscussionButton = findViewById(R.id.addDiscussionButton);
        progressBar = findViewById(R.id.progressBar);
        emptyStateText = findViewById(R.id.emptyStateText);

        // Initialize Firestore and discussions list
        firestore = FirebaseFirestore.getInstance();
        discussions = new ArrayList<>();
        adapter = new DiscussionAdapter(discussions, this);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Show progress bar initially
        progressBar.setVisibility(View.VISIBLE);

        // Load discussions from Firestore
        loadDiscussions();

        // Navigate to AddDiscussionActivity
        addDiscussionButton.setOnClickListener(v -> {
            startActivity(new Intent(DiscussionListActivity.this, AddDiscussionActivity.class));
        });
    }

    private void loadDiscussions() {
        listenerRegistration = firestore.collection("discussions")
                .orderBy("timestamp")
                .addSnapshotListener((value, error) -> {
                    progressBar.setVisibility(View.GONE);

                    if (error != null) {
                        Toast.makeText(this, "Error loading discussions: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (value != null && value.isEmpty()) {
                        emptyStateText.setVisibility(View.VISIBLE);
                    } else {
                        emptyStateText.setVisibility(View.GONE);
                    }

                    for (DocumentChange documentChange : value.getDocumentChanges()) {
                        if (documentChange.getType() == DocumentChange.Type.ADDED) {
                            Discussion discussion = documentChange.getDocument().toObject(Discussion.class);
                            discussions.add(discussion);
                            adapter.notifyItemInserted(discussions.size() - 1);
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove Firestore listener to prevent memory leaks
        if (listenerRegistration != null) {
            listenerRegistration.remove();
        }
    }
}