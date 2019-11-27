package com.nayana.example.vehicleserviceremainderapp.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //TODO : Implement ringing alarm
        makeText( context,"Alarm is Ringing .................!!!" , Toast.LENGTH_LONG).show();
    }
}
