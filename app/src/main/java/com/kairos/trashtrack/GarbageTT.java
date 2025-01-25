package com.kairos.trashtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class GarbageTT extends AppCompatActivity {

    private ListView listView;
    private List<String> garbageCollectionTimetable;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garbage_tt); // Ensure this matches your layout file name

        // Initialize ListView
        listView = findViewById(R.id.listView);

        // Sample data for the ListView
        garbageCollectionTimetable = new ArrayList<>();
        garbageCollectionTimetable.add("Monday: General Waste @ 6:30");
        garbageCollectionTimetable.add("Tuesday: Recycling @ 6:30");
        garbageCollectionTimetable.add("Wednesday: Yard Waste @ 6:30");
        garbageCollectionTimetable.add("Thursday: General Waste @ 6:30");
        garbageCollectionTimetable.add("Friday: Recycling @ 6:30");
        garbageCollectionTimetable.add("Saturday: E-Waste @ 6:30");

        // Set up the ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, garbageCollectionTimetable);
        listView.setAdapter(adapter);

        back = (Button)findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back1 = new Intent(GarbageTT.this, MainActivity.class);
                startActivity(back1);
                finish();
            }
        });
    }
}