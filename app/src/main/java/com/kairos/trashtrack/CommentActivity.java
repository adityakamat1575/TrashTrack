package com.kairos.trashtrack;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {

    private EditText commentEditText;
    private Button submitCommentButton;
    private FirebaseFirestore firestore;
    private LinearLayout commentsContainer;
    private String discussionId;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        commentEditText = findViewById(R.id.editTextComment);
        submitCommentButton = findViewById(R.id.buttonSubmitComment);
        progressBar = findViewById(R.id.progressBarComments);
        commentsContainer = findViewById(R.id.commentsContainer);

        firestore = FirebaseFirestore.getInstance();

        // Get the discussionId from the intent
        discussionId = getIntent().getStringExtra("discussionId");

        // Load comments from Firestore
        loadComments();

        // Submit a new comment
        submitCommentButton.setOnClickListener(v -> addComment());
    }

    private void loadComments() {
        progressBar.setVisibility(View.VISIBLE);

        CollectionReference commentsRef = firestore.collection("discussions")
                .document(discussionId)
                .collection("comments");

        commentsRef.orderBy("timestamp")
                .addSnapshotListener((value, error) -> {
                    progressBar.setVisibility(View.GONE); // Hide ProgressBar

                    if (error != null) {
                        Toast.makeText(this, "Error loading comments", Toast.LENGTH_SHORT).show();
                        Log.e("CommentActivity", "Error loading comments", error);
                        return;
                    }

                    // Clear the existing comments
                    commentsContainer.removeAllViews();

                    for (DocumentChange documentChange : value.getDocumentChanges()) {
                        if (documentChange.getType() == DocumentChange.Type.ADDED) {
                            Comment comment = documentChange.getDocument().toObject(Comment.class);
                            addCommentToView(comment);
                        }
                    }
                });
    }

    private void addCommentToView(Comment comment) {
        // Create a new TextView for the comment
        TextView commentTextView = new TextView(this);
        commentTextView.setText(comment.getCommentText());
        commentTextView.setPadding(0, 10 , 0, 10);
        commentTextView.setTextSize(16);
        commentsContainer.addView(commentTextView);
    }

    private void addComment() {
        String commentText = commentEditText.getText().toString().trim();

        if (TextUtils.isEmpty(commentText)) {
            commentEditText.setError(getString(R.string.error_empty_comment));
            return;
        }

        Map<String, Object> comment = new HashMap<>();
        comment.put("commentText", commentText);
        comment.put("timestamp", FieldValue.serverTimestamp());
        comment.put("userId", "Anonymous"); // Replace with user ID if authentication is implemented

        firestore.collection("discussions")
                .document(discussionId)
                .collection("comments")
                .add(comment)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, getString(R.string.comment_added_success), Toast.LENGTH_SHORT).show();
                    commentEditText.setText("");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("CommentActivity", "Error adding comment", e);
                });
    }
}