package com.nayana.example.vehicleserviceremainderapp.DataHolder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nayana.example.vehicleserviceremainderapp.Activities.SetAlarmDetails;
import com.nayana.example.vehicleserviceremainderapp.Model.Vehicle;
import com.nayana.example.vehicleserviceremainderapp.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class AddFourWheelerVehicleDetails extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private StorageReference storage;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String userID;
    private ProgressDialog progressDialog;
    private static final int IMAGE_REQUEST_CODE = 1;
    private Uri imageUri;

    private ImageButton vehicleImageButton;
    private EditText vehicleName;
    private EditText vehicleNumber;
    private EditText recentServiceDate;
    private EditText recentServiceMetre;
    private EditText nextServiceDate;
    private Button setAlarmButton;
    private EditText nextServiceMetre;

    private EditText vehicleInsurance;
    private EditText insuranceExpiry;
    private EditText engineOil;
    private EditText engineService;

    private EditText brakeReplaced;
    private EditText clutchReplaced;
    private EditText batteryReplaced;
    private EditText mirrorReplaced;
    private EditText airConditionService;
    private EditText radiatorService;

    private EditText chainReplaced;
    private EditText backTyreReplaced;
    private EditText frontTyreReplaced;
    private EditText vehicleSeatReplaced;
    private EditText indicatorReplaced;
    private EditText headlightReplaced;
    private EditText aboutVehicle;
    private Button setAlarmButtonMore;

    private Button saveVehicleDataButton;
    private static final String TAG = "AddFourWheelerVehicleDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_four_wheeler_vehicle_details);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userID = firebaseUser.getUid();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Vehicle").child(userID);
        storage = FirebaseStorage.getInstance().getReference();

        progressDialog = new ProgressDialog(this);

        vehicleImageButton = (ImageButton) findViewById(R.id.FW_vehicleImageButtonID);
        vehicleName = (EditText) findViewById(R.id.FW_vehicleNameID);
        vehicleNumber = (EditText) findViewById(R.id.FW_vehicleNumberID);
        recentServiceDate = (EditText) findViewById(R.id.FW_recentServiceDateID);
        recentServiceMetre = (EditText) findViewById(R.id.FW_recentServiceMetreID);
        nextServiceDate = (EditText) findViewById(R.id.FW_nextServiceDateID);
        nextServiceMetre = (EditText) findViewById(R.id.FW_nextServiceMetreID);
        vehicleInsurance = findViewById(R.id.FW_vehicleInsuranceID);
        insuranceExpiry = findViewById(R.id.FW_insuranceExpiryID);
        engineOil = findViewById(R.id.FW_engineOilID);
        engineService = findViewById(R.id.FW_engineServiceID);
        chainReplaced = findViewById(R.id.FW_chainReplacedID);
        backTyreReplaced = findViewById(R.id.FW_backTyreReplacedID);
        frontTyreReplaced = findViewById(R.id.FW_frontTyreReplacedID);
        vehicleSeatReplaced = findViewById(R.id.FW_vehicleSeatReplacedID);
        headlightReplaced = findViewById(R.id.FW_headlightReplacedID);
        indicatorReplaced = findViewById(R.id.FW_indicatorReplacedID);
        aboutVehicle = findViewById(R.id.FW_aboutVehicleID);
        setAlarmButton = (Button) findViewById(R.id.FW_setAlarmButton);
        setAlarmButtonMore = findViewById(R.id.FW_setAlarmButtonMore);
        saveVehicleDataButton = (Button) findViewById(R.id.FW_saveVehicleDataButton);

        vehicleImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT); //Allow the user to select a particular kind of data and return it.
                galleryIntent.setType("image/*"); // select all type of images
                startActivityForResult( galleryIntent , IMAGE_REQUEST_CODE );
            }
        });

        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //createPopUpSetAlarmDetails();
                startActivity( new Intent( AddFourWheelerVehicleDetails.this , SetAlarmDetails.class));
            }
        });

        setAlarmButtonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //createPopUpSetAlarmDetails();
                startActivity( new Intent( AddFourWheelerVehicleDetails.this , SetAlarmDetails.class));
            }
        });

        saveVehicleDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveVehicleData();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){

            imageUri = data.getData();
            //vehicleImageButton.setImageURI(imageUri);
            Picasso.with(this)
                    .load(imageUri)
                    .fit()
                    //.centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(vehicleImageButton);

        }
    }

    private void saveVehicleData() {

        final String vehicleNameET = vehicleName.getText().toString().trim();
        final String vehicleNumberET = vehicleNumber.getText().toString().trim();
        final String recentServiceET = recentServiceDate.getText().toString().trim();
        final String recentServiceMetreET = recentServiceMetre.getText().toString().trim();
        final String nextServiceET = nextServiceDate.getText().toString().trim();
        final String nextServiceMetreET = nextServiceMetre.getText().toString().trim();
        final String vehicleInsuranceET = vehicleInsurance.getText().toString().trim();
        final String insuranceExpiryET = insuranceExpiry.getText().toString().trim();
        final String engineOilET = engineOil.getText().toString().trim();
        final String engineServiceET = engineService.getText().toString().trim();
        final String chainReplacedET = chainReplaced.getText().toString().trim();
        final String backTyreReplacedET = backTyreReplaced.getText().toString().trim();
        final String frontTyreReplacedET = frontTyreReplaced.getText().toString().trim();
        final String vehicleSeatReplacedET = vehicleSeatReplaced.getText().toString().trim();
        final String headlightReplacedET = headlightReplaced.getText().toString().trim();
        final String indicatorReplacedET = indicatorReplaced.getText().toString().trim();
        final String aboutVehicleET = aboutVehicle.getText().toString().trim();

        if ( !TextUtils.isEmpty( vehicleNameET ) && !TextUtils.isEmpty( vehicleNumberET ) &&
                !TextUtils.isEmpty( recentServiceET ) && !TextUtils.isEmpty( recentServiceMetreET ) &&
                !TextUtils.isEmpty( nextServiceET ) && !TextUtils.isEmpty( nextServiceMetreET ) && imageUri != null ){
            //start uploading to database

            progressDialog.setMessage("Saving vehicle data....");
            progressDialog.show();

            StorageReference storageReference = storage.child("Vehicle_images").child(imageUri.getLastPathSegment());

            storageReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @SuppressLint("LongLogTag")
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();

                            while ( !downloadUrl.isSuccessful());

                            Uri downloadUri = downloadUrl.getResult();

                            Log.d( TAG+" : imageurl " , downloadUrl.toString());

                            Log.i( TAG , "VehicleName() : "+ vehicleNameET
                                            +" VehicleNumber() : "+ vehicleNumberET
                                            +" RecentServiceDate() : "+ recentServiceET
                                            +" RecentServiceMetreReading() : "+ recentServiceMetreET
                                            +" NextServiceDate() : "+ nextServiceET
                                            +" NextServiceMetreReading() : "+ nextServiceMetreET
                                            +" VehicleInsuranceDate() : "+ vehicleInsuranceET
                                            +" VehicleInsuranceExpiryDate() : "+ insuranceExpiryET
                                            +" EngineOilReplacedDate() : "+ engineOilET
                                            +" EngineServiceDate() : "+ engineServiceET
                                            +" ChainReplacedDate() : "+ chainReplacedET
                                            +" BackTyreReplacedDate() : "+ backTyreReplacedET
                                            +" FrontTyreReplacedDate() : "+ frontTyreReplacedET
                                            +" VehicleSeatReplacedDate() : "+ vehicleSeatReplacedET
                                            +" HeadlightReplacedDate() : "+ headlightReplacedET
                                            +" IndicatorReplacedDate() : "+ indicatorReplacedET);

                            DatabaseReference newPost = databaseReference.push(); //creates new instance id for every post

                            Vehicle vehicle = new Vehicle( downloadUri.toString() , vehicleNameET , vehicleNumberET , recentServiceET , recentServiceMetreET , nextServiceET ,
                                    nextServiceMetreET , vehicleInsuranceET , insuranceExpiryET , engineOilET ,engineServiceET , chainReplacedET ,
                                    backTyreReplacedET , frontTyreReplacedET , vehicleSeatReplacedET , headlightReplacedET , indicatorReplacedET , aboutVehicleET );

                            //newPost.child(userID).setValue(vehicle);

                            Log.d( "vehicle.toString() : " , vehicle.toString());
                            newPost.setValue(vehicle);
                            progressDialog.dismiss();

                            Toast.makeText( AddFourWheelerVehicleDetails.this , "Vehicle added!!!" , Toast.LENGTH_LONG).show();
                            startActivity(new Intent( AddFourWheelerVehicleDetails.this , DisplayVehicleListActivity.class));
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText( AddFourWheelerVehicleDetails.this , "Problem adding post", Toast.LENGTH_LONG).show();
                }
            });
        }
        else if (!TextUtils.isEmpty( vehicleNameET ) && !TextUtils.isEmpty( vehicleNumberET ) &&
                    !TextUtils.isEmpty( recentServiceET ) && !TextUtils.isEmpty( recentServiceMetreET ) &&
                        !TextUtils.isEmpty( nextServiceET ) && !TextUtils.isEmpty( nextServiceMetreET ))
                {

                    progressDialog.setMessage("Saving vehicle data....");
                    progressDialog.show();

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Vehicle vehicle = new Vehicle( "" , vehicleNameET , vehicleNumberET , recentServiceET , recentServiceMetreET , nextServiceET ,
                                                            nextServiceMetreET , vehicleInsuranceET , insuranceExpiryET , engineOilET ,engineServiceET , chainReplacedET ,
                                                                backTyreReplacedET , frontTyreReplacedET , vehicleSeatReplacedET , headlightReplacedET , indicatorReplacedET , aboutVehicleET );

                            //databaseReference.child(userID).setValue(vehicle);
                            databaseReference.setValue(vehicle);

                            Log.d( "vehicle.toString() : " , vehicle.toString());

                            progressDialog.dismiss();

                            Toast.makeText( AddFourWheelerVehicleDetails.this , "Vehicle added!!! from addValueEventListener" , Toast.LENGTH_LONG).show();
                            startActivity(new Intent( AddFourWheelerVehicleDetails.this , DisplayVehicleListActivity.class));
                            finish();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            Toast.makeText( AddFourWheelerVehicleDetails.this , "Problem adding post from addValueEventListener", Toast.LENGTH_LONG).show();
                        }
                    });
                }

        else Toast.makeText( AddFourWheelerVehicleDetails.this , "Problem saving data"+"\n"+"Enter all fields", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.saveData) {
            saveVehicleData();
            //startActivity( new Intent( AddFourWheelerVehicleDetails.this , DisplayVehicleListActivity.class));
            //super.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

