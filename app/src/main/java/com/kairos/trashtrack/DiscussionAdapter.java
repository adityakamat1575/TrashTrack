package com.kairos.trashtrack;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.ViewHolder> {

    private final List<Discussion> discussions;
    private final Context context;

    // Constant for intent extra key
    public static final String EXTRA_DISCUSSION_ID = "discussionId";

    public DiscussionAdapter(List<Discussion> discussions, Context context) {
        this.discussions = discussions != null ? discussions : new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext(); // Get context from parent
        View view = LayoutInflater.from(context).inflate(R.layout.item_discussion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Discussion discussion = discussions.get(position);

        // Handle null safety for title and description
        holder.title.setText(discussion.getTitle() != null ? discussion.getTitle() : context.getString(R.string.default_title));
        holder.description.setText(discussion.getDescription() != null ? discussion.getDescription() : context.getString(R.string.default_description));

        // Add click listener to open CommentActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CommentActivity.class);

            // Pass discussion ID only if it's not null
            if (discussion.getDiscussionId() != null) {
                intent.putExtra(EXTRA_DISCUSSION_ID, discussion.getDiscussionId());
            }

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return discussions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.discussionTitle);
            description = itemView.findViewById(R.id.discussionDescription);

            // Optional: Set a default ripple effect for feedback
            itemView.setBackgroundResource(android.R.drawable.list_selector_background);
        }
    }
}