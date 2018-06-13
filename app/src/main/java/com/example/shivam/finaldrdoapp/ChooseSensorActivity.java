package com.example.shivam.finaldrdoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shivam.finaldrdoapp.GraphActivities.AccelerometreSensorGraph;
import com.example.shivam.finaldrdoapp.GraphActivities.MoistureSensorGraph;
import com.example.shivam.finaldrdoapp.GraphActivities.RainSensorGraph;

public class ChooseSensorActivity extends AppCompatActivity implements View.OnClickListener{
    Button rainSensorBtn,accSensorBtn,moistureSensorBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_sensor);
        rainSensorBtn = (Button) findViewById(R.id.rain);
        moistureSensorBtn= (Button) findViewById(R.id.moisture);
        accSensorBtn = (Button) findViewById(R.id.acc);
        rainSensorBtn.setOnClickListener(this);
        accSensorBtn.setOnClickListener(this);
        moistureSensorBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.acc:
                //start the activity with the acc graph
                Intent intent = new Intent(ChooseSensorActivity.this, AccelerometreSensorGraph.class);
                startActivity(intent);
                break;
            case R.id.moisture:
                //start the activity with the moisture graph
                Intent intent1 = new Intent(ChooseSensorActivity.this,MoistureSensorGraph.class);
                startActivity(intent1);
                break;
            case R.id.rain:
                //start the activity with the rain graph
                Intent intent2 = new Intent(ChooseSensorActivity.this, RainSensorGraph.class);
                startActivity(intent2);
                break;
        }
    }
}
