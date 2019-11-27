package com.nayana.example.vehicleserviceremainderapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nayana.example.vehicleserviceremainderapp.R;
import com.squareup.picasso.Picasso;

public class AddMoreVehicleDetails extends AppCompatActivity {

    private ImageView vehicleImageD;
    private TextView vehicleNameD;
    private TextView vehicleNumberD;
    private TextView nextServiceDateD;
    private TextView recentServiceDateD;
    private TextView recentServiceMetreReadingD;
    private TextView nextServiceMetreReadingD;
    private TextView InsuredDateD;
    private TextView InsuredExpiryDateD;
    private TextView engineOilReplacedDateD;
    private TextView engineServiceDateD;
    private TextView chainReplacedDateD;
    private TextView backTyreReplacedDateD;
    private TextView frontTyreReplacedDateD;
    private TextView vehicleSeatReplacedDateD;
    private TextView headlightReplacedDateD;
    private TextView indicatorReplacedDateD;
    private TextView aboutVehicleD;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_more_vehicle_details);

        vehicleImageD = findViewById(R.id.vehicleImageDL);
        vehicleNameD = findViewById(R.id.vehicleNameDL);
        vehicleNumberD = findViewById(R.id.vehicleNumberDL);
        nextServiceDateD = findViewById(R.id.nextServiceDateDL);
        recentServiceDateD = findViewById(R.id.recentServiceDateDL);
        recentServiceMetreReadingD = findViewById(R.id.recentServiceMetreReadingDL);
        nextServiceMetreReadingD = findViewById(R.id.nextServiceMetreReadingDL);
        InsuredDateD = findViewById(R.id.InsuredDateDL);
        InsuredExpiryDateD = findViewById(R.id.InsuredExpiryDateDL);
        engineOilReplacedDateD = findViewById(R.id.engineOilReplacedDateDL);
        engineServiceDateD = findViewById(R.id.engineServiceDateDL);
        chainReplacedDateD = findViewById(R.id.chainReplacedDateDL);
        backTyreReplacedDateD = findViewById(R.id.backTyreReplacedDateDL);
        frontTyreReplacedDateD = findViewById(R.id.frontTyreReplacedDateDL);
        vehicleSeatReplacedDateD = findViewById(R.id.vehicleSeatReplacedDateDL);
        headlightReplacedDateD = findViewById(R.id.headlightReplacedDateDL);
        indicatorReplacedDateD = findViewById(R.id.indicatorReplacedDateDL);
        aboutVehicleD = findViewById(R.id.aboutVehicleDL);

//        bundle = getIntent().getExtras();
//
//        Picasso.with(AddMoreVehicleDetails.this)
//                .load(bundle.getString("VehicleImage"))
//                .placeholder(R.mipmap.ic_launcher_round)
//                .fit()
//                .centerCrop()
//                .into(vehicleImageD);
//
//        vehicleNameD.setText(bundle.getString("VehicleName"));
//        vehicleNumberD.setText(bundle.getString("VehicleNumber"));
//        recentServiceDateD.setText(bundle.getString("RecentServiceDate"));
//        recentServiceMetreReadingD.setText(bundle.getString("RecentServiceMetreReading"));
//        nextServiceDateD.setText(bundle.getString("NextServiceDate"));
//        nextServiceMetreReadingD.setText(bundle.getString("NextServiceMetreReading"));
//        InsuredDateD.setText(bundle.getString("VehicleInsuranceDate"));
//        InsuredExpiryDateD.setText(bundle.getString("VehicleInsuranceExpiryDate"));
//        engineOilReplacedDateD.setText(bundle.getString("EngineOilReplacedDate"));
//        engineServiceDateD.setText(bundle.getString("EngineServiceDate"));
//        chainReplacedDateD.setText(bundle.getString("ChainReplacedDate"));
//        backTyreReplacedDateD.setText(bundle.getString("BackTyreReplacedDate"));
//        frontTyreReplacedDateD.setText(bundle.getString("FrontTyreReplacedDate"));
//        vehicleSeatReplacedDateD.setText(bundle.getString("VehicleSeatReplacedDate"));
//        headlightReplacedDateD.setText(bundle.getString("HeadlightReplacedDate"));
//        indicatorReplacedDateD.setText(bundle.getString("IndicatorReplacedDate"));
//        aboutVehicleD.setText(bundle.getString("AboutVehicle"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_delete , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() )
        {
            case R.id.editVehicle : editVehicle();
                break;

            case R.id.deleteVehicle : deleteVehicle();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void editVehicle() {
        Toast.makeText( getApplicationContext() , "Trying to edit Vehicle" , Toast.LENGTH_LONG).show();
    }

    private void deleteVehicle() {
        Toast.makeText( getApplicationContext() , "Trying to delete Vehicle" , Toast.LENGTH_LONG).show();
    }
}
