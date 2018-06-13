package com.example.shivam.finaldrdoapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OptionsActivity extends AppCompatActivity implements View.OnClickListener{
    //in this activity i would provide a choice whether to view images or monitor sensor data
    Button sensor,image ;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        linearLayout = (LinearLayout)findViewById(R.id.linear);
        linearLayout.setBackgroundResource(R.drawable.landslideweblogo);
        sensor = (Button)findViewById(R.id.sensorData);
        image = (Button)findViewById(R.id.viewimages);
        sensor.setOnClickListener(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        image.setOnClickListener(this);
        databaseReference.child("motion").child("isMOtion").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(String.class).equals("true")){
                    displayBig();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void displayBig( ){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("ALERT");
        builder.setContentText("Motion Detected at mountain");
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("motion detected"));
        builder.addAction(R.mipmap.ic_launcher,"Add",null);
        builder.addAction(R.mipmap.ic_launcher,"Close",null);
        NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(2,builder.build());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sensorData:
                Intent intent = new Intent(OptionsActivity.this,ChooseSensorActivity.class);
                startActivity(intent);
                break;
            case R.id.viewimages:
                Intent intent1 = new Intent(OptionsActivity.this,ShowMotionImages.class);
                startActivity(intent1);
                break;
        }
    }
}
