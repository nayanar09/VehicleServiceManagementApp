package com.nayana.example.vehicleserviceremainderapp.DataHolder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nayana.example.vehicleserviceremainderapp.R;
import com.squareup.picasso.Picasso;

public class VehicleDetails extends AppCompatActivity {

    private ImageView vehicleImageDL;
    private TextView vehicleNameDL;
    private TextView vehicleNumberDL;
    private TextView nextServiceDateDL;
    private TextView recentServiceDateDL;
    private TextView recentServiceMetreReadingDL;
    private TextView nextServiceMetreReadingDL;
    private TextView InsuredDateDL;
    private TextView InsuredExpiryDateDL;
    private TextView engineOilReplacedDateDL;
    private TextView engineServiceDateDL;
    private TextView chainReplacedDateDL;
    private TextView backTyreReplacedDateDL;
    private TextView frontTyreReplacedDateDL;
    private TextView vehicleSeatReplacedDateDL;
    private TextView headlightReplacedDateDL;
    private TextView indicatorReplacedDateDL;
    private TextView aboutVehicleDL;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);

        vehicleImageDL = findViewById(R.id.vehicleImageDL);
        vehicleNameDL = findViewById(R.id.vehicleNameDL);
        vehicleNumberDL = findViewById(R.id.vehicleNumberDL);
        nextServiceDateDL = findViewById(R.id.nextServiceDateDL);
        recentServiceDateDL = findViewById(R.id.recentServiceDateDL);
        recentServiceMetreReadingDL = findViewById(R.id.recentServiceMetreReadingDL);
        nextServiceMetreReadingDL = findViewById(R.id.nextServiceMetreReadingDL);
        InsuredDateDL = findViewById(R.id.InsuredDateDL);
        InsuredExpiryDateDL = findViewById(R.id.InsuredExpiryDateDL);
        engineOilReplacedDateDL = findViewById(R.id.engineOilReplacedDateDL);
        engineServiceDateDL = findViewById(R.id.engineServiceDateDL);
        chainReplacedDateDL = findViewById(R.id.chainReplacedDateDL);
        backTyreReplacedDateDL = findViewById(R.id.backTyreReplacedDateDL);
        frontTyreReplacedDateDL = findViewById(R.id.frontTyreReplacedDateDL);
        vehicleSeatReplacedDateDL = findViewById(R.id.vehicleSeatReplacedDateDL);
        headlightReplacedDateDL = findViewById(R.id.headlightReplacedDateDL);
        indicatorReplacedDateDL = findViewById(R.id.indicatorReplacedDateDL);
        aboutVehicleDL = findViewById(R.id.aboutVehicleDL);

        bundle = getIntent().getExtras();

        Picasso.with(VehicleDetails.this)
                .load(bundle.getString("VehicleImage"))
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(vehicleImageDL);

        vehicleNameDL.setText(bundle.getString("VehicleName"));
        vehicleNumberDL.setText(bundle.getString("VehicleNumber"));
        recentServiceDateDL.setText(bundle.getString("RecentServiceDate"));
        recentServiceMetreReadingDL.setText(bundle.getString("RecentServiceMetreReading"));
        nextServiceDateDL.setText(bundle.getString("NextServiceDate"));
        nextServiceMetreReadingDL.setText(bundle.getString("NextServiceMetreReading"));
        InsuredDateDL.setText(bundle.getString("VehicleInsuranceDate"));
        InsuredExpiryDateDL.setText(bundle.getString("VehicleInsuranceExpiryDate"));
        engineOilReplacedDateDL.setText(bundle.getString("EngineOilReplacedDate"));
        engineServiceDateDL.setText(bundle.getString("EngineServiceDate"));
        chainReplacedDateDL.setText(bundle.getString("ChainReplacedDate"));
        backTyreReplacedDateDL.setText(bundle.getString("BackTyreReplacedDate"));
        frontTyreReplacedDateDL.setText(bundle.getString("FrontTyreReplacedDate"));
        vehicleSeatReplacedDateDL.setText(bundle.getString("VehicleSeatReplacedDate"));
        headlightReplacedDateDL.setText(bundle.getString("HeadlightReplacedDate"));
        indicatorReplacedDateDL.setText(bundle.getString("IndicatorReplacedDate"));
        aboutVehicleDL.setText(bundle.getString("AboutVehicle"));

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
