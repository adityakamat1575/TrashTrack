package com.kairos.trashtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class DiscussionListActivity extends AppCompatActivity {

    private LinearLayout discussionsContainer;
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
        discussionsContainer = findViewById(R.id.discussionsContainer);
        addDiscussionButton = findViewById(R.id.addDiscussionButton);
        progressBar = findViewById(R.id.progressBar);
        emptyStateText = findViewById(R.id.emptyStateText);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

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
                        discussionsContainer.removeAllViews(); // Clear previous discussions
                    } else {
                        emptyStateText.setVisibility(View.GONE);
                        discussionsContainer.removeAllViews(); // Clear previous discussions
                    }

                    for (DocumentChange documentChange : value.getDocumentChanges()) {
                        if (documentChange.getType() == DocumentChange.Type.ADDED) {
                            Discussion discussion = documentChange.getDocument().toObject(Discussion.class);
                            addDiscussionToView(discussion);
                        }
                    }
                });
    }

    private void addDiscussionToView(Discussion discussion) {
        // Create a new TextView for the discussion
        TextView discussionTextView = new TextView(this);
        discussionTextView.setText(discussion.getTitle()); // Assuming Discussion has a getTitle() method
        discussionTextView.setPadding(0, 10, 0, 10);
        discussionTextView.setTextSize(18);
        discussionTextView.setOnClickListener(v -> {
            // Handle discussion click, e.g., navigate to CommentActivity
            Intent intent = new Intent(DiscussionListActivity.this, CommentActivity.class);
            intent.putExtra("discussionId", discussion.getId()); // Assuming Discussion has a getId() method
            startActivity(intent);
        });
        discussionsContainer.addView(discussionTextView);
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