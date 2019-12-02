package com.nayana.example.vehicleserviceremainderapp.DataHolder;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nayana.example.vehicleserviceremainderapp.Activities.WelcomeActivity;
import com.nayana.example.vehicleserviceremainderapp.Model.Vehicle;
import com.nayana.example.vehicleserviceremainderapp.R;
import com.nayana.example.vehicleserviceremainderapp.UI.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisplayVehicleListActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String userID;

    private RecyclerView recyclerView;
    private VehicleRecyclerViewAdapter vehicleRecyclerViewAdapter;
    private List<Vehicle> vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_vehicle_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( DisplayVehicleListActivity.this , AddTwoWheelerVehicleDetails.class));
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userID = firebaseUser.getUid();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Vehicle").child(userID);
        databaseReference.keepSynced(true);

        recyclerView = findViewById(R.id.VehicleDisplayRecyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager( this ));

        vehicleList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for ( DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Vehicle vehicle = snapshot.getValue(Vehicle.class);
                    vehicleList.add(vehicle);

                    Log.i( "DisplayVehicleList : " ,
                        "VehicleName() : "+ vehicle.getVehicleName()
                                +" VehicleNumber() : "+ vehicle.getVehicleNumber()
                                +" RecentServiceDate() : "+ vehicle.getRecentServiceDate()
                                +" RecentServiceMetreReading() : "+ vehicle.getRecentServiceMetreReading()
                                +" NextServiceDate() : "+ vehicle.getNextServiceDate()
                                +" NextServiceMetreReading() : "+ vehicle.getNextServiceMetreReading()
                                +" VehicleInsuranceDate() : "+ vehicle.getVehicleInsuranceDate()
                                +" VehicleInsuranceExpiryDate() : "+ vehicle.getVehicleInsuranceExpiryDate()
                                +" VehicleImage() : "+ vehicle.getVehicleImage()
                                +" EngineOilReplacedDate() : "+vehicle.getEngineOilReplacedDate()
                                +" EngineServiceDate() : "+vehicle.getEngineServiceDate()
                                +" ChainReplacedDate() : "+vehicle.getChainReplacedDate()
                                +" BackTyreReplacedDate() : "+vehicle.getBackTyreReplacedDate()
                                +" FrontTyreReplacedDate() : "+vehicle.getFrontTyreReplacedDate()
                                +" VehicleSeatReplacedDate() : "+vehicle.getVehicleSeatReplacedDate()
                                +" HeadlightReplacedDate() : "+vehicle.getHeadlightReplacedDate()
                                +" IndicatorReplacedDate() : "+vehicle.getIndicatorReplacedDate());

                    Collections.reverse(vehicleList);

                    vehicleRecyclerViewAdapter = new VehicleRecyclerViewAdapter( DisplayVehicleListActivity.this , vehicleList );
                    recyclerView.setAdapter(vehicleRecyclerViewAdapter);;
                    vehicleRecyclerViewAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext() , "Error :"+ databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId()){

            case R.id.action_add :
                if ( firebaseAuth != null && firebaseUser != null ) {

                    startActivity( new Intent( getApplicationContext() , WelcomeActivity.class));
                    finish();
                }
                break;

            case R.id.action_signout :
                if ( firebaseAuth != null && firebaseUser != null){

                    firebaseAuth.signOut();
                    Toast.makeText( DisplayVehicleListActivity.this , "User Signed Out" , Toast.LENGTH_LONG).show();
                    Log.d( "VehicleListActivity" , firebaseUser.getEmail());
                    startActivity( new Intent( DisplayVehicleListActivity.this , MainActivity.class));
                    finish();
                }
        }

        return super.onOptionsItemSelected(item);
    }

}
