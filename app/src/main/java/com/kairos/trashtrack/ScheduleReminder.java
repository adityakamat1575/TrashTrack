package com.kairos.trashtrack;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ScheduleReminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_reminder);

        scheduleReminder();
    }

    private void scheduleReminder() {
        // Calculate the time difference to the next reminder
        long delay = calculateDelayToPickupTime(); // Calculate delay based on user input

        if (delay > 0) {
            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(ReminderWorker.class)
                    .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                    .build();

            // Enqueue the work request with WorkManager
            WorkManager.getInstance(this).enqueue(workRequest);

            // Display a confirmation Toast
            Toast.makeText(this, "Reminder scheduled for " + delay / 1000 / 60 + " minutes from now", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "The scheduled time has already passed. Please check the time again.", Toast.LENGTH_SHORT).show();
        }
    }

    private long calculateDelayToPickupTime() {
        // Set the garbage pickup time to 8 AM tomorrow
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);  // Set to 8 AM
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);  // Ensure no milliseconds are set

        long currentTimeMillis = System.currentTimeMillis();
        long targetTimeMillis = calendar.getTimeInMillis();

        if (targetTimeMillis <= currentTimeMillis) {
            // If it's past 8 AM today, set it for 8 AM tomorrow
            targetTimeMillis += TimeUnit.DAYS.toMillis(1);
        }

        // Return the difference in milliseconds
        return targetTimeMillis - currentTimeMillis;
    }
}
