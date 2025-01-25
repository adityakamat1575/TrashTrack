package com.kairos.trashtrack;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<Disposal> disposals;

    public HistoryAdapter(List<Disposal> disposals) {
        this.disposals = disposals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disposal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Disposal disposal = disposals.get(position);
        holder.tvType.setText("Type: " + disposal.getType());
        holder.tvWeight.setText("Weight: " + disposal.getWeight() + " kg");
        holder.tvTimestamp.setText("Date: " + disposal.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return disposals.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvType, tvWeight, tvTimestamp;

        ViewHolder(View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tvType);
            tvWeight = itemView.findViewById(R.id.tvWeight);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }
    }
}
