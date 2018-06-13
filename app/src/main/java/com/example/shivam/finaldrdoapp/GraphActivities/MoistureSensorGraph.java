package com.example.shivam.finaldrdoapp.GraphActivities;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.finaldrdoapp.R;
import com.macroyau.thingspeakandroid.ThingSpeakChannel;
import com.macroyau.thingspeakandroid.ThingSpeakLineChart;
import com.macroyau.thingspeakandroid.model.ChannelFeed;
import com.macroyau.thingspeakandroid.model.Feed;

import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class MoistureSensorGraph extends AppCompatActivity {
    ThingSpeakLineChart thingSpeakLineChart;
    TextView textView;
    LineChartView lineChartView;
    ThingSpeakChannel thingSpeakChannel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moisture_sensor_graph);
        thingSpeakChannel = new ThingSpeakChannel(495459,"MGW3BFZPPGHHCPB9");
        thingSpeakChannel.setChannelFeedUpdateListener(new ThingSpeakChannel.ChannelFeedUpdateListener() {
            @Override
            public void onChannelFeedUpdated(long channelId, String channelName, ChannelFeed channelFeed) {
                //code goes here
                Toast.makeText(MoistureSensorGraph.this,channelName,Toast.LENGTH_SHORT).show();
                Log.e("accelerometer sensor-->","ACTIVE ");
                for(Feed channelFeed1 : channelFeed.getFeeds()){


                       //code for showing notification
                        Log.e("567890",channelFeed1.getField1());

                    if (Double.parseDouble(channelFeed1.getField1())>58.00000){
                        Log.e("THreshold crossed : ","True");
                        displayBig(channelFeed1.getField1());
                    }
                       // Toast.makeText(MoistureSensorGraph.this,channelFeed1.getField1(),Toast.LENGTH_SHORT).show();


                }


            }
        });
        textView = (TextView)findViewById(R.id.aboutSensorMoistureTextView);
        textView.setText("Soil moisture sensor uses capacitance to measure dielectric permittivity of the surrounding medium . It is used to measure the water content of the soil" +
                " Big changes in soil moisture levels can predict a landslide . Threshold Value : ");
        thingSpeakChannel.loadChannelFeed();
        lineChartView = (LineChartView)findViewById(R.id.moistureChart);
        thingSpeakLineChart = new ThingSpeakLineChart(495459,1);
        thingSpeakLineChart.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                lineChartView.setLineChartData(lineChartData);
                lineChartView.setMaximumViewport(maxViewport);
                lineChartView.setCurrentViewport(initialViewport);
            }
        });
        thingSpeakLineChart.loadChartData();

    }

    private void displayBig(String eventInfo ){
        String message=eventInfo;
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);

        builder.setSmallIcon(R.drawable.applogo);
        builder.setContentTitle("My Notification");
        builder.setContentText("Moisture Level Near Danger"+eventInfo);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
        builder.addAction(R.drawable.applogo,"Add",null);
        builder.addAction(R.drawable.applogo,"Close",null);

        NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(2,builder.build());
    }
}
