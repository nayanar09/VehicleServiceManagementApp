package com.nayana.example.vehicleserviceremainderapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nayana.example.vehicleserviceremainderapp.Activities.WelcomeActivity;
import com.nayana.example.vehicleserviceremainderapp.DataHolder.DisplayVehicleListActivity;
import com.nayana.example.vehicleserviceremainderapp.R;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser firebaseUser;

    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button createAccountButton;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                firebaseUser = firebaseAuth.getCurrentUser();

                if ( firebaseUser != null ){
                    // user signed in
                    Log.d(TAG, "onAuthStateChanged: User signed in");
                    Log.d(TAG, "UserName : " + firebaseUser.getEmail());
                    //Toast.makeText( MainActivity.this , "User Signed In" , Toast.LENGTH_LONG).show();
                    //startActivity( new Intent( MainActivity.this , VehicleListActivity.class));
                    startActivity( new Intent( MainActivity.this , DisplayVehicleListActivity.class));
                    finish();
                }
                else
                {
                    // user signed out
                    Log.d(TAG, "onAuthStateChanged: User signed out");
                    //Toast.makeText( MainActivity.this , "User not Signed In" , Toast.LENGTH_LONG).show();
                }
            }
        };

        email = (EditText) findViewById(R.id.emailID);
        password = (EditText) findViewById(R.id.passwordID);
        loginButton = (Button) findViewById(R.id.loginID);
        createAccountButton = (Button) findViewById(R.id.createAccountID);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailString = email.getText().toString();
                String pwd = password.getText().toString();

                if ( !TextUtils.isEmpty(emailString) && !TextUtils.isEmpty(pwd) ){ //if both not empty
                    login( emailString , pwd );
                    Toast.makeText( MainActivity.this , "Hello!!!!" , Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText( MainActivity.this , "please enter email and password" , Toast.LENGTH_LONG).show();
                }
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( MainActivity.this , CreateAccount.class));
            }
        });
    }

    private void login(String emailString, String pwd) {

        firebaseAuth.signInWithEmailAndPassword( emailString , pwd )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()){
                            Toast.makeText( MainActivity.this , "SignIn Unsuccessful" , Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText( MainActivity.this , "SignIn Successful" , Toast.LENGTH_LONG).show();

                            //startActivity( new Intent( MainActivity.this , DisplayVehicleListActivity.class));
                            startActivity( new Intent( MainActivity.this , WelcomeActivity.class));
                            finish();
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ( item.getItemId() ==  R.id.action_signout){
            firebaseAuth.signOut();
            Toast.makeText( MainActivity.this , "User Signed Out" , Toast.LENGTH_LONG).show();
            Log.d( TAG, firebaseUser.getEmail());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.signout_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null)
            firebaseAuth.removeAuthStateListener(authStateListener);
    }
}
