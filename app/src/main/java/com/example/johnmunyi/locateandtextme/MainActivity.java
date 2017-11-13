package com.example.johnmunyi.locateandtextme;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
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

public class MainActivity extends AppCompatActivity {

    private Button mapMe, textMe;
    private TextView lati, longi;
    private Layout mapDisplay;
    private String strPhone, strMessage;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapMe = (Button) findViewById(R.id.mapMe);
        textMe = (Button) findViewById(R.id.textMe);

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
                Log.d("Mr", "Text Button clicked");
                String strPhone = "250734598922";
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(strPhone, null, strMessage, null, null);
                Toast.makeText(getApplicationContext(), "Sent.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
