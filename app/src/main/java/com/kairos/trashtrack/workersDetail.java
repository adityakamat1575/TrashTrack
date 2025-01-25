package com.kairos.trashtrack;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class workersDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_detail);

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView categoryTextView = findViewById(R.id.categoryTextView);
        EditText messageEditText = findViewById(R.id.messageEditText);
        Button sendButton = findViewById(R.id.sendButton);

        String name = getIntent().getStringExtra("name");
        String category = getIntent().getStringExtra("category");

        nameTextView.setText(name);
        categoryTextView.setText(category);

        sendButton.setOnClickListener(v -> {
            String message = messageEditText.getText().toString();
            if (!message.isEmpty()) {
                Toast.makeText(this, "Message sent to " + name, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please enter a message.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}