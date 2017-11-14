package com.example.johnmunyi.locateandtextme;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static com.example.johnmunyi.locateandtextme.MapsActivity.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    private Button mapMe, textMe;
    private TextView lati, longi;
    private Layout mapDisplay;
    private String strPhone, strMessage;
    private LocationManager locationManager;
    private Location location;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted;
    private Location mCurrentLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapMe = (Button) findViewById(R.id.mapMe);
        textMe = (Button) findViewById(R.id.textMe);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    mCurrentLocation = location;
                }
            });
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        mapMe.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("Mr", "Map show button was clicked");
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        textMe.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{android.Manifest.permission.SEND_SMS}, 1);
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{android.Manifest.permission.RECEIVE_SMS}, 1);
                }
                MapsActivity mapper = new MapsActivity();
                Log.d("Mr", "Text Button clicked");
                String details1 = valueOf(mCurrentLocation.getLatitude());
                String details2 = valueOf(mCurrentLocation.getLongitude());
                String strPhone = "250734598922";
                String strMessage = "Location details:" + details1 + " " + details2;
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(strPhone, null, strMessage, null, null);
                Toast.makeText(getApplicationContext(), "Sent.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
