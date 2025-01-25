package com.kairos.trashtrack;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResourceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_detail);

        // Retrieve the passed CustomResource object
        CustomResource resource = (CustomResource) getIntent().getSerializableExtra("resource");  // Use Serializable

        // Find views to display the resource data
        TextView titleView = findViewById(R.id.tvTitle);
        TextView descriptionView = findViewById(R.id.tvDescription);
        ImageView imageView = findViewById(R.id.ivImage);

        // Set the data into the views
        if (resource != null) {
            titleView.setText(resource.getTitle());
            descriptionView.setText(resource.getDescription());
            imageView.setImageResource(resource.getImageResId());
        } else {
            titleView.setText("No Title");
            descriptionView.setText("No Description Available");
            // Optionally set a default image if necessary
            // imageView.setImageResource(R.drawable.default_image);
        }
    }
}