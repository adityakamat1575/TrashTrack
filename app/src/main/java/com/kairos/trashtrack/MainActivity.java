package com.kairos.trashtrack;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser ;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Check if the user is logged in and set the content view accordingly
        checkUser ();

        // Now that content view is set, we can safely initialize buttons and handle click events
        Button Signout, trashtt, sort, Alert, Map, Schedule, Community, details, educate;

        // Initialize buttons and set up click listeners
        Signout = findViewById(R.id.Signout);
        Signout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent signoutIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(signoutIntent);
        });

        trashtt = findViewById(R.id.Garbagett);
        trashtt.setOnClickListener(view -> {
            Intent garbage = new Intent(MainActivity.this, GarbageTT.class);
            startActivity(garbage);
        });

        sort = findViewById(R.id.SortingGuide);
        sort.setOnClickListener(view -> {
            Intent sortIntent = new Intent(MainActivity.this, SortingGuide.class);
            startActivity(sortIntent);
        });

        Alert = findViewById(R.id.Alert);
        Alert.setOnClickListener(view -> {
            Intent alertIntent = new Intent(MainActivity.this, TrackActivity.class);
            startActivity(alertIntent);
        });

        Map = findViewById(R.id.Map);
        Map.setOnClickListener(view -> {
            Intent mapIntent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(mapIntent);
        });

        Schedule = findViewById(R.id.Schedule);
        Schedule.setOnClickListener(view -> {
            Intent schIntent = new Intent(MainActivity.this, ScheduleReminder.class);
            startActivity(schIntent);
        });

        Community = findViewById(R.id.Community);
        Community.setOnClickListener(view -> {
            Intent communityIntent = new Intent(MainActivity.this, DiscussionListActivity.class);
            startActivity(communityIntent);
        });

        details = findViewById(R.id.detail);
        details.setOnClickListener(view -> {
            Intent detailIntent = new Intent(MainActivity.this, workersDetail.class);
            startActivity(detailIntent);
        });

        educate = findViewById(R.id.Educate);
        educate.setOnClickListener(view -> {
            Intent eduIntent = new Intent(MainActivity.this, ResourceActivity.class);
            startActivity(eduIntent);
        });
    }

    private void checkUser () {
        FirebaseUser  currentUser  = mAuth.getCurrentUser ();
        if (currentUser  == null) {
            // User is not logged in, redirect to LoginActivity
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
            finish(); // Prevent going back to MainActivity
        } else {
            // User is logged in, proceed to MainActivity layout
            setContentView(R.layout.activity_main); // Only set content after the check
        }
    }
}