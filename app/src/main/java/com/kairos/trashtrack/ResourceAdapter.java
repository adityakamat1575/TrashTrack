package com.kairos.trashtrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder> {

    private List<CustomResource> resourceList;  // Changed from Resource to CustomResource
    private OnItemClickListener listener;

    // Define an interface for item clicks
    public interface OnItemClickListener {
        void onItemClick(CustomResource resource);  // Changed to CustomResource
    }

    // Constructor for the adapter
    public ResourceAdapter(List<CustomResource> resourceList, OnItemClickListener listener) {
        this.resourceList = resourceList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resource, parent, false);
        return new ResourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceViewHolder holder, int position) {
        // Bind the data to the ViewHolder
        CustomResource resource = resourceList.get(position);  // Changed to CustomResource
        holder.bind(resource, listener);
    }

    @Override
    public int getItemCount() {
        return resourceList.size();
    }

    // ViewHolder class
    public static class ResourceViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private ImageView image;

        public ResourceViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle); // Find the title TextView
            description = itemView.findViewById(R.id.tvDescription); // Find the description TextView
            image = itemView.findViewById(R.id.ivImage); // Find the image ImageView
        }

        // Bind the resource data to the views
        public void bind(CustomResource resource, OnItemClickListener listener) {
            title.setText(resource.getTitle());  // Changed to CustomResource
            description.setText(resource.getDescription());  // Changed to CustomResource
            image.setImageResource(resource.getImageResId()); // Set the image for the resource

            // Set an onClickListener for the item view
            itemView.setOnClickListener(v -> listener.onItemClick(resource));
        }
    }
}
