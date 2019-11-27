package com.nayana.example.vehicleserviceremainderapp.DataHolder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nayana.example.vehicleserviceremainderapp.Activities.AddMoreVehicleDetails;
import com.nayana.example.vehicleserviceremainderapp.Activities.SetAlarmDetails;
import com.nayana.example.vehicleserviceremainderapp.Model.Vehicle;
import com.nayana.example.vehicleserviceremainderapp.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class AddTwoWheelerVehicleDetails extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private StorageReference storage;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private ProgressDialog progressDialog;
    private static final int IMAGE_REQUEST_CODE = 1;
    private Uri imageUri;
    private Vehicle vehicle;

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
    private EditText chainReplaced;
    private EditText backTyreReplaced;
    private EditText frontTyreReplaced;
    private EditText vehicleSeatReplaced;
    private EditText indicatorReplaced;
    private EditText headlightReplaced;
    private EditText aboutVehicle;
    private Button setAlarmButtonMore;

    private Button saveVehicleDataButton;
    private Button addMoreVehicleDetails;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog; //A subclass of Dialog that can display one, two or three buttons.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle_details);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Vehicle");
        storage = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);

        vehicleImageButton = (ImageButton) findViewById(R.id.vehicleImageButtonID);
        vehicleName = (EditText) findViewById(R.id.vehicleNameID);
        vehicleNumber = (EditText) findViewById(R.id.vehicleNumberID);
        recentServiceDate = (EditText) findViewById(R.id.recentServiceDateID);
        recentServiceMetre = (EditText) findViewById(R.id.recentServiceMetreID);
        nextServiceDate = (EditText) findViewById(R.id.nextServiceDateID);
        nextServiceMetre = (EditText) findViewById(R.id.nextServiceMetreID);
        vehicleInsurance = findViewById(R.id.vehicleInsuranceID);
        insuranceExpiry = findViewById(R.id.insuranceExpiryID);
        engineOil = findViewById(R.id.engineOilID);
        engineService = findViewById(R.id.engineServiceID);
        chainReplaced = findViewById(R.id.chainReplacedID);
        backTyreReplaced = findViewById(R.id.backTyreReplacedID);
        frontTyreReplaced = findViewById(R.id.frontTyreReplacedID);
        vehicleSeatReplaced = findViewById(R.id.vehicleSeatReplacedID);
        headlightReplaced = findViewById(R.id.headlightReplacedID);
        indicatorReplaced = findViewById(R.id.indicatorReplacedID);
        aboutVehicle = findViewById(R.id.aboutVehicleID);
        setAlarmButton = (Button) findViewById(R.id.setAlarmButton);
        setAlarmButtonMore = findViewById(R.id.setAlarmButtonMore);
        saveVehicleDataButton = (Button) findViewById(R.id.saveVehicleDataButton);
        addMoreVehicleDetails = (Button) findViewById(R.id.addMoreVehicleDetailsButton);

//        final String vehicleNameEditText = vehicleName.getText().toString().trim();
//
//        //mandatory fields
//        if ( vehicleImageButton != null && vehicleNameEditText.isEmpty())
//        {
//            vehicleName.setError( "please enter Vehicle name");
//            vehicleName.requestFocus();
//            return;
//        }

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
                startActivity( new Intent( AddTwoWheelerVehicleDetails.this , SetAlarmDetails.class));
            }
        });

        setAlarmButtonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //createPopUpSetAlarmDetails();
                startActivity( new Intent( AddTwoWheelerVehicleDetails.this , SetAlarmDetails.class));
            }
        });

        saveVehicleDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveVehicleData();
            }
        });

        addMoreVehicleDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( AddTwoWheelerVehicleDetails.this , AddMoreVehicleDetails.class));
            }
        });
    }

//    private void createPopUpSetAlarmDetails() {
//
//        dialogBuilder = new AlertDialog.Builder(this);
//
//        View view = getLayoutInflater().inflate(R.layout.activity_set_alarm_details, null);
//
//        dialogBuilder.setView(view);
//        dialog = dialogBuilder.create();
//        dialog.show();
//
//        Button yesButton , noButton;
//
//        yesButton = findViewById(R.id.yesButtonAD);
//        noButton = findViewById(R.id.noButtonAD);
//
//        noButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        yesButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity( new Intent( AddTwoWheelerVehicleDetails.this , SetAlarmDetails.class));
//                    }
//        });
//    }

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

        //EXAMPLE
//        if ( !TextUtils.isEmpty( vehicleNameET ) && !TextUtils.isEmpty( vehicleNumberET ) &&
//                !TextUtils.isEmpty( recentServiceET ) && !TextUtils.isEmpty( recentServiceMetreET ) &&
//                    !TextUtils.isEmpty( nextServiceET ) && !TextUtils.isEmpty( nextServiceMetreET ) && imageUri != null ){
//            //start uploading to database example
//
//            progressDialog.setMessage("Saving vehicle data....");
//            progressDialog.show();
//
//            Vehicle vehicle = new Vehicle( vehicleNameET , vehicleNumberET , recentServiceET , recentServiceMetreET , nextServiceET , nextServiceMetreET );
//
//            databaseReference.setValue(vehicle).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    Toast.makeText( AddTwoWheelerVehicleDetails.this, "Item Added", Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
//                }
//            });
//        }
//        else Toast.makeText( AddTwoWheelerVehicleDetails.this , "Problem saving data"+"\n"+"Enter all fields", Toast.LENGTH_LONG).show();

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

                            Log.d( "AddPostActivity : imageurl " , downloadUrl.toString());

                            Log.i( "AddVehicleActivity" ,
                                    "VehicleName() : "+ vehicleNameET
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
                                //old way to upload to database
                            //newPost.child("vehicleImage").setValue(downloadUrl.toString());
                            //                                        newPost.child("vehicleName").setValue(vehicleNameET);
                            //                                        newPost.child("vehicleNumber").setValue(vehicleNumberET);
                            //                                        newPost.child("recentServiceDate").setValue(recentServiceET);
                            //                                        newPost.child("recentServiceMetreReading").setValue(recentServiceMetreET);
                            //                                        newPost.child("nextServiceDate").setValue(nextServiceET);
                            //                                        newPost.child("nextServiceMetreReading").setValue(nextServiceMetreET)
                            //                                        newPost.child("vehicleInsurance").setValue(vehicleInsuranceET);
                            //                                        newPost.child("insuranceExpiry").setValue(insuranceExpiryET);
                            //                                        newPost.child("engineOil").setValue(engineOilET);
                            //                                        newPost.child("engineService").setValue(engineServiceET);
                            //                                        newPost.child("chainReplaced").setValue(chainReplacedET);
                            //                                        newPost.child("backTyreReplaced").setValue(backTyreReplacedET);
                            //                                        newPost.child("frontTyreReplaced").setValue(frontTyreReplacedET);
                            //                                        newPost.child("vehicleSeatReplaced").setValue(vehicleSeatReplacedET);
                            //                                        newPost.child("headlightReplaced").setValue(headlightReplacedET);
                            //                                        newPost.child("indicatorReplaced").setValue(indicatorReplacedET);
                            //                                        newPost.child("aboutVehicle").setValue(aboutVehicleET);

                            Map<String,String> dataToSave = new HashMap<>();
                            //HashMap<String,String> dataToSave = new HashMap<>();

                            dataToSave.put("vehicleImage" , downloadUri.toString());
                            dataToSave.put("vehicleName" , vehicleNameET);
                            dataToSave.put("vehicleNumber" , vehicleNumberET);
                            dataToSave.put("recentServiceDate" , recentServiceET);
                            dataToSave.put("recentServiceMetreReading" , recentServiceMetreET);
                            dataToSave.put("nextServiceDate" , nextServiceET);
                            dataToSave.put("nextServiceMetreReading" , nextServiceMetreET);
                            dataToSave.put("vehicleInsurance" , vehicleInsuranceET);
                            dataToSave.put("insuranceExpiry" , insuranceExpiryET);
                            dataToSave.put("engineOil" , engineOilET);
                            dataToSave.put("engineService" , engineServiceET);
                            dataToSave.put("chainReplaced" , chainReplacedET);
                            dataToSave.put("backTyreReplaced" , backTyreReplacedET);
                            dataToSave.put("frontTyreReplaced" , frontTyreReplacedET);
                            dataToSave.put("vehicleSeatReplaced" , vehicleSeatReplacedET);
                            dataToSave.put("headlightReplaced" , headlightReplacedET);
                            dataToSave.put("indicatorReplaced" , indicatorReplacedET);
                            dataToSave.put("aboutVehicle" , aboutVehicleET);

                            newPost.setValue(dataToSave);
                            progressDialog.dismiss();

                            Toast.makeText( AddTwoWheelerVehicleDetails.this , "Vehicle added!!!" , Toast.LENGTH_LONG).show();
                            startActivity(new Intent( AddTwoWheelerVehicleDetails.this , DisplayVehicleListActivity.class));
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText( AddTwoWheelerVehicleDetails.this , "Problem adding post", Toast.LENGTH_LONG).show();
                }
            });
        }
        else Toast.makeText( AddTwoWheelerVehicleDetails.this , "Problem saving data"+"\n"+"Enter all fields", Toast.LENGTH_LONG).show();

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
            startActivity( new Intent( AddTwoWheelerVehicleDetails.this , DisplayVehicleListActivity.class));
            //super.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
