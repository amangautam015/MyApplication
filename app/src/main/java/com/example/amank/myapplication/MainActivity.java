package com.example.amank.myapplication;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    Button showNotification ,stopNotification,alertButton;
    NotificationManager notificationManager;
    boolean isNotificActive=false;
    int notifId =   33;
    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showNotification = (Button)findViewById(R.id.button);
        stopNotification = (Button)findViewById(R.id.buttonn);
        alertButton=(Button) findViewById(R.id.button2);

      //  btnDatePicker=(Button)findViewById(R.id.btn_date);
      //  btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        txtDate.setOnClickListener(this);
        txtTime.setOnClickListener(this);
    }


    public void showNotification(View view){
    NotificationCompat.Builder notificbuilder = new NotificationCompat.Builder(this)
            .setContentTitle("Hello World")
            .setContentText("Message")
            .setTicker("Alert")
            .setSmallIcon(R.drawable.notification_icon);
        Intent moreInfoIntent = new Intent(this,MainActivityy.class);
        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(this);
        tStackBuilder.addParentStack(MainActivity.class);
        tStackBuilder.addNextIntent(moreInfoIntent);
        PendingIntent pendingIntent = tStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        notificbuilder.setContentIntent(pendingIntent);
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifId,notificbuilder.build());
        isNotificActive=true;


    }
    public void stopNotification(View view){
         if (isNotificActive){
             notificationManager.cancel(notifId);
         }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v == txtDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == txtTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setAlarm(View view){
        Long alertTime = new GregorianCalendar().getTimeInMillis()+5000;
        Intent alertIntent = new Intent(this,AlertReceiver.class);
        AlarmManager alarmManager =(AlarmManager)getSystemService(Context.ALARM_SERVICE);
     //   PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alertIntent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP,alertTime,PendingIntent.getBroadcast(this,1,alertIntent,PendingIntent.FLAG_UPDATE_CURRENT));
        //alarmManager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 5000,
       //         pendingIntent);
    }
    }

