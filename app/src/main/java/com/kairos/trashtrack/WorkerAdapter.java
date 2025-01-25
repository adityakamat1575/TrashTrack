package com.kairos.trashtrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {
    private final List<Worker> workers;
    private final OnWorkerClickListener listener;

    public interface OnWorkerClickListener {
        void onWorkerClick(Worker worker);
    }

    public WorkerAdapter(List<Worker> workers, OnWorkerClickListener listener) {
        this.workers = workers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Worker worker = workers.get(position);
        holder.nameTextView.setText(worker.getName());
        holder.categoryTextView.setText(worker.getCategory());
        holder.itemView.setOnClickListener(v -> listener.onWorkerClick(worker));
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, categoryTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
        }
    }
}