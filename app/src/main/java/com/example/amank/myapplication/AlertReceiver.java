package com.example.amank.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by amank on 4/8/17.
 */

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
                createNotification(context,"Times up","10 sec asa","Alert");


    }

    public  void createNotification(Context context, String msg,String  msgText,String msgAlert){

        PendingIntent notificationIntent = PendingIntent.getActivity(context,0,
        new Intent(context,MainActivity.class),0);
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(msg)
                .setTicker(msgAlert)
                .setContentText(msgText);
     mBuilder.setContentIntent(notificationIntent);
        mBuilder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
     //   mBuilder.setAutoCancel(true);
        NotificationManager   notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());
    }

}
