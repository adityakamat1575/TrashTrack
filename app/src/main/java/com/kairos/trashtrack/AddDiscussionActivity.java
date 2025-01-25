package com.kairos.trashtrack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddDiscussionActivity extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText;
    private Button submitButton;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discussion);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        submitButton = findViewById(R.id.submitButton);
        firestore = FirebaseFirestore.getInstance();

        submitButton.setOnClickListener(v -> addDiscussion());
    }

    private void addDiscussion() {
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_fill_fields), Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> discussion = new HashMap<>();
        discussion.put("title", title);
        discussion.put("description", description);
        discussion.put("timestamp", FieldValue.serverTimestamp());

        setLoading(true);
        firestore.collection("discussions")
                .add(discussion)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, getString(R.string.success_discussion_added), Toast.LENGTH_SHORT).show();
                    setLoading(false);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("AddDiscussion", "Error adding discussion", e);
                    setLoading(false);
                });
    }

    private void setLoading(boolean isLoading) {
        submitButton.setEnabled(!isLoading);
        // Optional: Show/Hide a ProgressBar if you have one.
    }
}
