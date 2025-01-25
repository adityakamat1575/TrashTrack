package com.kairos.trashtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResourceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

        // Resource 1
        TextView title1 = findViewById(R.id.resource_title_1);
        ImageView image1 = findViewById(R.id.resource_image_1);
        TextView desc1 = findViewById(R.id.resource_desc_1);

        title1.setText(getString(R.string.reduce_reuse_recycle));
        image1.setImageResource(R.drawable.recycle);
        desc1.setText(getString(R.string.reduce_reuse_recycle_desc));

        // Set click listener for resource 1
        title1.setOnClickListener(v -> openResourceDetailActivity(new CustomResource(
                getString(R.string.reduce_reuse_recycle),
                getString(R.string.reduce_reuse_recycle_desc),
                R.drawable.recycle
        )));

        // Resource 2
        // Repeat for other resources...
        // You can create more TextViews and ImageViews for each resource similarly.
    }

    private void openResourceDetailActivity(CustomResource resource) {
        Intent intent = new Intent(ResourceActivity.this, ResourceDetailActivity.class);
        intent.putExtra("resource", resource);  // Pass the entire resource object
        startActivity(intent);
    }
}