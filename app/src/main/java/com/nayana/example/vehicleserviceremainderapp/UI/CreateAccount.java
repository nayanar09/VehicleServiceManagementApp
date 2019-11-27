package com.nayana.example.vehicleserviceremainderapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nayana.example.vehicleserviceremainderapp.DataHolder.DisplayVehicleListActivity;
import com.nayana.example.vehicleserviceremainderapp.R;

public class CreateAccount extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText CA_email;
    private EditText CA_password;
    private Button CA_createAccount;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private ProgressDialog mProgressDialog;

    public static final String TAG = "CreateAccount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firstName = (EditText) findViewById(R.id.CAfirstnameID);
        lastName = (EditText) findViewById(R.id.CAlastnameID);
        CA_email = (EditText) findViewById(R.id.CAemailID);
        CA_password = (EditText) findViewById(R.id.CApasswordID);
        CA_createAccount = (Button) findViewById(R.id.CAcreateAccountID);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Blog_Users");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mProgressDialog = new ProgressDialog(this);

        CA_createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createNewAccount();
            }
        });
    }

    private void createNewAccount() {

        final String firstname = firstName.getText().toString().trim();
        final String lastname = lastName.getText().toString().trim();
        String email = CA_email.getText().toString().trim();
        String password = CA_password.getText().toString().trim();

        if (!TextUtils.isEmpty(firstname) && !TextUtils.isEmpty(lastname) &&!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

            mProgressDialog.setMessage("Creating Account");
            mProgressDialog.show();
            mAuth.createUserWithEmailAndPassword( email , password ).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    if ( authResult != null ){
                        String userID = mAuth.getCurrentUser().getUid();
                        Log.d(TAG , mAuth.getCurrentUser().getEmail()+" created new account");
                        //Log.d(TAG , userID+" created new account");

                        DatabaseReference currentUserDB = databaseReference.child(userID);
                        currentUserDB.child("firstName : ").setValue(firstname);
                        currentUserDB.child("lastName : ").setValue(lastname);

                        Log.i(TAG , "currentUserDB.toString()");
                        Log.i(TAG , currentUserDB.toString());

                        mProgressDialog.dismiss();
                        Toast.makeText( CreateAccount.this , "Account Created Successfully", Toast.LENGTH_LONG).show();
                        Toast.makeText( CreateAccount.this , "Welcome new Blogger", Toast.LENGTH_LONG).show();

                        //send users to PostListActivity
                        Intent intent = new Intent( CreateAccount.this , DisplayVehicleListActivity.class );
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //If set, and the activity being launched is already running in the current task, then instead of launching a new instance of that activity, all of the other activities on top of it will be closed and this Intent will be delivered to the (now on top) old activity as a new Intent.
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText( CreateAccount.this , "User not authorised to create account", Toast.LENGTH_LONG).show();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText( CreateAccount.this , "Problem creating Account", Toast.LENGTH_LONG).show();
                }
            });
        }else
            Toast.makeText( CreateAccount.this , "Enter all fields", Toast.LENGTH_LONG).show();

    }
}
