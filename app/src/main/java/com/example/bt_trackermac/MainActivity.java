package com.example.bt_trackermac;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
    }


    public void goToLogActivity(View view) {
        Intent toLog = new Intent(this, LogActivity.class);
        startActivity(toLog);
    }

    public void goToNormalActivity(View view) {
        Intent toNormal = new Intent(this, NormalActivity.class);
        startActivity(toNormal);
    }

    public void goToMechanismActivity(View view) {
        Intent toMechanism = new Intent(this, MechanismActivity.class);
        startActivity(toMechanism);
    }

    public void goToHandleActivity(View view) {
        Intent toHandle = new Intent(this, HandleActivity.class);
        startActivity(toHandle);
    }

    public void setReminder(View view) {
        // When user clicks "setReminder" button, a toast message will pop up to let user know that a reminder is set
        Toast.makeText(this,"Reminder set!", Toast.LENGTH_SHORT).show();
        // Create an intent object to start the ReminderBroadcastReceiver class
        Intent intent = new Intent(this, ReminderBroadcastReceiver.class);
        PendingIntent pd = PendingIntent.getBroadcast(this,0,intent,0);
        AlarmManager alarmManager=
                (AlarmManager)getSystemService(ALARM_SERVICE);
        long interval = 1000*6;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),interval,pd);
    }

    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            String channelID = "BT_Tracker_Channel";
            String channelName = "BTTrackerReminderChannel";
            String channelDescription = "Channel for BT Tracker reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelID, channelName, importance);
            channel.setDescription(channelDescription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
