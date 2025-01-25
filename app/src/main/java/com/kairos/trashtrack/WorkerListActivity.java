package com.kairos.trashtrack;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WorkerListActivity extends AppCompatActivity implements WorkerAdapter.OnWorkerClickListener {

    private RecyclerView recyclerView;
    private WorkerAdapter workerAdapter;
    private List<Worker> workerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        workerList = new ArrayList<>();
        workerList.add(new Worker("John Doe", "Senior Cleanliness Worker"));
        workerList.add(new Worker("Jane Smith", "Junior Cleanliness Worker"));
        workerList.add(new Worker("Mike Johnson", "Supervisor"));

        workerAdapter = new WorkerAdapter(workerList, this);
        recyclerView.setAdapter(workerAdapter);
    }

    @Override
    public void onWorkerClick(Worker worker) {
        // Handle worker click event
        // For example, you can start a new activity or show a toast
        // Toast.makeText(this, "Clicked: " + worker.getName(), Toast.LENGTH_SHORT).show();
    }
}