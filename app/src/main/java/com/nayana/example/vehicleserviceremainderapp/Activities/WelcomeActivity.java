package com.nayana.example.vehicleserviceremainderapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.nayana.example.vehicleserviceremainderapp.DataHolder.AddTwoWheelerVehicleDetails;
import com.nayana.example.vehicleserviceremainderapp.R;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton twoWheelerButton;
    private ImageButton fourWheelerButton;
    private ImageButton sixWheelerButton;
    private Button gotoDisplayVehiclesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        twoWheelerButton = findViewById(R.id.twoWheelerIB);
        fourWheelerButton = findViewById(R.id.fourWheelerIB);
        sixWheelerButton = findViewById(R.id.sixWheelerIB);
        gotoDisplayVehiclesButton = findViewById(R.id.gotoDisplayVehicleButton);

        twoWheelerButton.setOnClickListener(this);
        fourWheelerButton.setOnClickListener(this);
        sixWheelerButton.setOnClickListener(this);
        gotoDisplayVehiclesButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ){

            case R.id.twoWheelerIB : startActivity( new Intent( getApplicationContext() , AddTwoWheelerVehicleDetails.class ));
                break;

            case R.id.fourWheelerIB :
                break;

            case R.id.sixWheelerIB :
                break;

            case R.id.gotoDisplayVehicleButton :
                break;
        }

    }
}
