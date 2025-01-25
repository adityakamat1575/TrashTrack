TrashTrack
TrashTrack is an Android application designed to help users manage their waste disposal, recycling, and tracking of garbage collection. The app offers features like tracking waste types, scheduling reminders for garbage collection, and educating users on waste sorting.

Features
User Authentication: Allows users to sign in and sign out securely using Firebase Authentication.
Garbage Collection Reminder: Schedules and sends notifications for upcoming garbage collection.
Sorting Guide: Provides an easy-to-follow guide for sorting different types of waste.
Resource Education: Educates users on various waste disposal methods and recycling.
Community Discussion: Allows users to engage in discussions on waste management topics.
Requirements
Android Studio: Ensure you're using Android Studio to build and run the application.
Firebase: The app uses Firebase for authentication and user management.
Internet Connection: Required for Firebase services.

Open the project in Android Studio:

Open Android Studio and select Open an existing project.
Navigate to the folder where you cloned the repository and select it.
Configure Firebase:

Go to the Firebase Console.
Create a new project, then add your Android app by following the instructions on Firebase for adding Firebase to an Android project.
Download the google-services.json file from Firebase and place it in the app/ directory of your project.
Sync Gradle:

After configuring Firebase and downloading google-services.json, sync the project with Gradle by selecting File > Sync Project with Gradle Files in Android Studio.
Run the app:

Connect an Android device or use an emulator, then click Run in Android Studio to build and install the app.
Firebase Setup
Firebase Authentication
Enable Firebase Authentication:

Go to the Authentication section in Firebase.
Enable Email/Password authentication under Sign-in method.
Add Firebase dependencies: Add the following dependencies to your build.gradle (app-level) file:
implementation 'com.google.firebase:firebase-auth:21.0.1'
implementation 'com.google.firebase:firebase-firestore:24.0.1'
implementation 'com.google.firebase:firebase-messaging:23.0.1'
Add Firebase SDK to your project: In your build.gradle (project-level), add the following line:

classpath 'com.google.gms:google-services:4.3.10'
Sync the project.

Fork the repository.
Create a new branch (git checkout -b feature-name).
Commit your changes (git commit -am 'Add new feature').
Push to the branch (git push origin feature-name).
Open a Pull Request.