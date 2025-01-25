package com.kairos.trashtrack;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ResourceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ResourceAdapter adapter;
    private List<CustomResource> resourceList;  // Changed from Resource to CustomResource

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);  // Use the traditional way to set content view

        recyclerView = findViewById(R.id.recyclerView);  // Use findViewById to access the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the resource list
        resourceList = new ArrayList<>();
        resourceList.add(new CustomResource(getString(R.string.reduce_reuse_recycle), getString(R.string.reduce_reuse_recycle_desc), R.drawable.recycle));
        resourceList.add(new CustomResource(getString(R.string.composting), getString(R.string.composting_desc), R.drawable.compost));
        resourceList.add(new CustomResource(getString(R.string.hazardous_waste), getString(R.string.hazardous_waste_desc), R.drawable.hazardous));
        resourceList.add(new CustomResource(getString(R.string.ewaste_management), getString(R.string.ewaste_management_desc), R.drawable.ewaste));

        // Set up the adapter
        adapter = new ResourceAdapter(resourceList, resource -> {
            // Handle item click
            Intent intent = new Intent(ResourceActivity.this, ResourceDetailActivity.class);
            intent.putExtra("resource", resource);  // Pass the entire resource object
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);  // Set the adapter to the RecyclerView
    }
}