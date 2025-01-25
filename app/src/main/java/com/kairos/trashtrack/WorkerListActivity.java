package com.kairos.trashtrack;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class WorkerListActivity extends AppCompatActivity {

    private LinearLayout workersContainer;
    private List<Worker> workerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list);

        workersContainer = findViewById(R.id.workersContainer);

        // Sample data
        workerList = new ArrayList<>();
        workerList.add(new Worker("John Doe", "Senior Cleanliness Worker"));
        workerList.add(new Worker("Jane Smith", "Junior Cleanliness Worker"));
        workerList.add(new Worker("Mike Johnson", "Supervisor"));

        // Populate the LinearLayout with worker data
        populateWorkers();
    }

    private void populateWorkers() {
        for (Worker worker : workerList) {
            // Create a new TextView for each worker
            TextView workerTextView = new TextView(this);
            workerTextView.setText(worker.getName() + " - " + worker.getPosition());
            workerTextView.setPadding(0, 10, 0, 10);
            workerTextView.setTextSize(18);
            workerTextView.setOnClickListener(v -> onWorkerClick(worker)); // Set click listener
            workersContainer.addView(workerTextView);
        }
    }

    public void onWorkerClick(Worker worker) {
        // Handle worker click event
        Toast.makeText(this, "Clicked: " + worker.getName(), Toast.LENGTH_SHORT).show();
        // You can also start a new activity or perform other actions here
    }
}