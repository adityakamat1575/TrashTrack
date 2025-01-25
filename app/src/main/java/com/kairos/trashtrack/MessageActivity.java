package com.kairos.trashtrack;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessageActivity extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "garbage_reminder_channel";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // Extract notification payload
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        String title = notification != null && notification.getTitle() != null ? notification.getTitle() : "Reminder";
        String message = notification != null && notification.getBody() != null ? notification.getBody() : "Garbage collection is due.";

        // Handle data payload (if present)
        if (remoteMessage.getData().size() > 0) {
            // Example: Handle custom data payload here
            String customData = remoteMessage.getData().get("customKey");
        }

        // Show the notification
        showNotification(title, message);
    }

    private void showNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Create Notification Channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager);
        }

        // Create intent for notification
        Intent intent = new Intent(this, MainActivity.class);
        int pendingIntentFlags = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                ? PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE
                : PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, pendingIntentFlags);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Replace with your app icon
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message)) // BigTextStyle for long messages
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // Display the notification with a unique ID
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

    private void createNotificationChannel(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Garbage Collection Reminders",
                    NotificationManager.IMPORTANCE_HIGH // Adjust as needed
            );
            channel.setDescription("Notifications for garbage collection reminders");
            notificationManager.createNotificationChannel(channel);
        }
    }
}
