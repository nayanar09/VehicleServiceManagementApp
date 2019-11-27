package com.nayana.example.vehicleserviceremainderapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nayana.example.vehicleserviceremainderapp.R;

import java.util.Calendar;

public class SetAlarmDetails extends AppCompatActivity {

    private EditText alarmNote;
    private TextView alarmTV;
    private TextView alarmDateTimeTV;
    private Button addAlarm;
    private Button cancelAlarm;

    public String labelNote;
    int redColorValue = Color.RED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_details);

        alarmNote = findViewById(R.id.alarmNoteTV);
        alarmTV = findViewById(R.id.alarmText);
        alarmDateTimeTV = findViewById(R.id.alarmDateTimeTV);
        addAlarm = findViewById(R.id.setAlarmRemainder);
        cancelAlarm = findViewById(R.id.cancelAlarm);

        labelNote = alarmNote.getText().toString().trim();

        if( TextUtils.isEmpty(labelNote)){
            //alarmNote.setHintTextColor(redColorValue);
            //alarmNote.setError("Please enter reminder note..");
            alarmNote.setError("required");
        }

        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });

        cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
    }

    //Use this function it will enable you to pic date and time one by one then set it to global variable date. No library no XML.
    //using below lines of code no need of using TimePicker or DatePicker in XML
    public void showDateTimePicker() {

        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();

        new DatePickerDialog(SetAlarmDetails.this , new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                date.set( year, monthOfYear, dayOfMonth );
                Log.d("SetAlarmDetails : ", "Alarm date " + date.toString());

                new TimePickerDialog( SetAlarmDetails.this , new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        date.set( Calendar.HOUR_OF_DAY , hourOfDay );
                        date.set( Calendar.MINUTE , minute );
                        date.set( Calendar.SECOND , 0 );

                        setAlarm(date);

                        Log.v("SetAlarmDetails", "Alarm time " + date.getTime());
                        alarmDateTimeTV.setText(date.getTime().toString());

                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show(); //setting TimePickerDialog
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show(); //setting DatePickerDialog
    }

    public void cancelAlarm (){

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        alarmTV.setText(R.string.alarmCancel);

    }

    public void setAlarm( Calendar calendar ){

        AlarmManager alarmManager = (AlarmManager) getSystemService( Context.ALARM_SERVICE );

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if ( calendar.before(Calendar.getInstance()) ) {
            calendar.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        alarmTV.setText(R.string.alarmSet);
        Toast.makeText(getApplicationContext(), "Alarm is set!!" , Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.done) {
            //TODO : saveAlarmData();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
