package com.kairos.trashtrack;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText commentEditText;
    private Button submitCommentButton;
    private FirebaseFirestore firestore;
    private CommentAdapter adapter;
    private ArrayList<Comment> comments;
    private String discussionId;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        recyclerView = findViewById(R.id.recyclerViewComments);
        commentEditText = findViewById(R.id.editTextComment);
        submitCommentButton = findViewById(R.id.buttonSubmitComment);
        progressBar = findViewById(R.id.progressBarComments);

        firestore = FirebaseFirestore.getInstance();
        comments = new ArrayList<>();
        adapter = new CommentAdapter(comments, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

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

                    for (DocumentChange documentChange : value.getDocumentChanges()) {
                        if (documentChange.getType() == DocumentChange.Type.ADDED) {
                            Comment comment = documentChange.getDocument().toObject(Comment.class);
                            comments.add(comment);
                            adapter.notifyItemInserted(comments.size() - 1);
                        }
                    }
                });
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
