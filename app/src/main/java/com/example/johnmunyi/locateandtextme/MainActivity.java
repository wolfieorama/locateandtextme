package com.example.johnmunyi.locateandtextme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mapMe, textMe;
    private TextView lati, longi;
    private Layout mapDisplay;

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
            }
        });

        textMe.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Mr", "Text Button clicked");
            }
        });
    }
}
