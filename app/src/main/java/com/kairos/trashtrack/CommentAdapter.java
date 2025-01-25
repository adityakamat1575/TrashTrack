package com.kairos.trashtrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private ArrayList<Comment> comments;

    public CommentAdapter(ArrayList<Comment> comments, CommentActivity commentActivity) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);

        // Set comment text
        holder.commentText.setText(comment.getCommentText() != null ? comment.getCommentText() : "No comment");

        // Format and set the timestamp
        if (comment.getTimestamp() > 0) {
            holder.timestamp.setText(formatTimestamp(comment.getTimestamp()));
        } else {
            holder.timestamp.setText("Unknown time");
        }

        // Set user ID
        holder.userId.setText(comment.getUserId() != null ? comment.getUserId() : "Anonymous");
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView commentText, timestamp, userId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commentText = itemView.findViewById(R.id.commentText);
            timestamp = itemView.findViewById(R.id.commentTimestamp);
            userId = itemView.findViewById(R.id.commentUserId);
        }
    }
}
