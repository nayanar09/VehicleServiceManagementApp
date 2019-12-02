package com.nayana.example.vehicleserviceremainderapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.nayana.example.vehicleserviceremainderapp.DataHolder.AddFourWheelerVehicleDetails;
import com.nayana.example.vehicleserviceremainderapp.DataHolder.AddSixWheelerVehicleDetails;
import com.nayana.example.vehicleserviceremainderapp.DataHolder.AddTwoWheelerVehicleDetails;
import com.nayana.example.vehicleserviceremainderapp.DataHolder.DisplayVehicleListActivity;
import com.nayana.example.vehicleserviceremainderapp.DataHolder.VehicleRecyclerViewAdapter;
import com.nayana.example.vehicleserviceremainderapp.Model.Vehicle;
import com.nayana.example.vehicleserviceremainderapp.R;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton twoWheelerButton;
    private ImageButton fourWheelerButton;
    private ImageButton sixWheelerButton;
    private Button gotoDisplayVehiclesButton;

    private VehicleRecyclerViewAdapter vehicleRecyclerViewAdapter;
    private List<Vehicle> vehicleList = new ArrayList();
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        twoWheelerButton = findViewById(R.id.twoWheelerIB);
        fourWheelerButton = findViewById(R.id.fourWheelerIB);
        sixWheelerButton = findViewById(R.id.sixWheelerIB);
        gotoDisplayVehiclesButton = findViewById(R.id.gotoDisplayVehicleButton);

        vehicleRecyclerViewAdapter = new VehicleRecyclerViewAdapter( getApplicationContext() , vehicleList );
        count = vehicleRecyclerViewAdapter.getItemCount();

        twoWheelerButton.setOnClickListener(this);
        fourWheelerButton.setOnClickListener(this);
        sixWheelerButton.setOnClickListener(this);
        gotoDisplayVehiclesButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ){

            case R.id.twoWheelerIB : twoWheeler();
                break;

            case R.id.fourWheelerIB : fourWheeler();
                break;

            case R.id.sixWheelerIB : sixWheeler();
                break;

//            case R.id.gotoDisplayVehicleButton : checkIfVehicleExists();
//                break;

            case R.id.gotoDisplayVehicleButton :
                                                startActivity( new Intent( getApplicationContext() , DisplayVehicleListActivity.class ));
                                                finish();
                                                break;
        }

    }

    private void twoWheeler(){

        Toast.makeText( getApplicationContext() , "Add Two Wheeler Vehicle" , Toast.LENGTH_LONG).show();
        startActivity( new Intent( getApplicationContext() , AddTwoWheelerVehicleDetails.class ));
        //finish();

    }
    private void fourWheeler(){

        Toast.makeText( getApplicationContext() , "Add Four Wheeler Vehicle" , Toast.LENGTH_LONG).show();
        startActivity( new Intent( getApplicationContext() , AddFourWheelerVehicleDetails.class ));
        //finish();

    }
    private void sixWheeler(){

        Toast.makeText( getApplicationContext() , "Add Six Wheeler Vehicle" , Toast.LENGTH_LONG).show();
        startActivity( new Intent( getApplicationContext() , AddSixWheelerVehicleDetails.class ));
        //finish();

    }

    private void checkIfVehicleExists(){

        if ( count == 0 )
            {
                Toast.makeText( getApplicationContext() , "First add Vehicles to view them" , Toast.LENGTH_LONG).show();
            }
        else
            {
                startActivity( new Intent( getApplicationContext() , DisplayVehicleListActivity.class ));
                //finish();
            }

    }
}
