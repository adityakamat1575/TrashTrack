package com.kairos.trashtrack;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ReminderWorker extends Worker {

    private static final String CHANNEL_ID = "garbage_reminder_channel";

    public ReminderWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            showNotification("Garbage Pickup Reminder", "The garbage truck is coming soon!");
            return Result.success();
        } catch (Exception e) {
            Log.e("ReminderWorker", "Error showing notification", e);
            return Result.failure();
        }
    }

    private void showNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        // Create the notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(CHANNEL_ID);
            if (channel == null) {
                channel = new NotificationChannel(
                        CHANNEL_ID,
                        "Garbage Collection Reminders",
                        NotificationManager.IMPORTANCE_DEFAULT // Use DEFAULT unless critical
                );
                channel.setDescription("Notifications for garbage collection reminders");
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Intent to open the app when the notification is clicked
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        int pendingIntentFlags = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                ? PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                : PendingIntent.FLAG_UPDATE_CURRENT;

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, pendingIntentFlags);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Replace with app icon
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message)) // Handle long messages
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // Use a unique notification ID
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }
}
