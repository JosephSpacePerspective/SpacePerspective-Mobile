package com.SpacePerspective.mobile;


import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class HomeActivity extends Activity {

    Button powerButton, thermalButton, pressureButton, commsButton, descentButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        powerButton = (Button) findViewById(R.id.power);
        thermalButton = (Button) findViewById(R.id.thermal);
        pressureButton = (Button) findViewById(R.id.pressure);
        commsButton = (Button) findViewById(R.id.comms);
        descentButton = (Button) findViewById(R.id.descent);

        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performDataQuery("PW");
            }
        });

        thermalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performDataQuery("TH");
            }
        });

        pressureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performDataQuery("PR");
            }
        });

        commsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performDataQuery("CO");
            }
        });

        descentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performDataQuery("DE");
            }
        });
    }

    public void performDataQuery(String PID) {

        Intent dataIntent = new Intent(this, DataActivity.class);
        dataIntent.putExtra("pid", PID);
        startActivity(dataIntent);
    }

}
