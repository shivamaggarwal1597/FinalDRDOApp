package com.example.shivam.finaldrdoapp.GraphActivities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

public class AccelerometreSensorGraph extends AppCompatActivity {
    LineChartView lineChartView;
    ThingSpeakLineChart thingSpeakLineChart;
    TextView textView;
    ThingSpeakChannel thingSpeakChannel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometre_sensor_graph);
        thingSpeakChannel = new ThingSpeakChannel(482596,"MGW3BFZPPGHHCPB9");
        thingSpeakChannel.setChannelFeedUpdateListener(new ThingSpeakChannel.ChannelFeedUpdateListener() {
            @Override
            public void onChannelFeedUpdated(long channelId, String channelName, ChannelFeed channelFeed) {
                //code goes here
                Toast.makeText(AccelerometreSensorGraph.this,channelName,Toast.LENGTH_SHORT).show();
                Log.e("accelerometer sensor-->","ACTIVE ");
                for(Feed channelFeed1 : channelFeed.getFeeds()){
                    //code for showing notification
                    Log.e("5678900000",channelFeed1.getFields().length+"  Feild 1: " + channelFeed1.getField1()
                    +"  Feild 2 : "+ channelFeed1.getField2() + "  Feild 3 : "+ channelFeed1.getField3() + "  Feild 4 : "+ channelFeed1.getField4()+ "  Feild 5 : "+ channelFeed1.getField5() +"  Feild 6 : "+ channelFeed1.getField6());

                   /* if (Double.parseDouble(channelFeed1.getField2())>58.00000){
                        Log.e("Threshold crossed : ","True");
                        displayBig(channelFeed1.getField2());
                    }*/
                }
            }
        });
        textView = (TextView)findViewById(R.id.aboutSensorTextView);
        textView.setText("Accelerometer is an electromechanical device that will measure accelaration forces along the three axis .\n These forces may be static like the " +
                "constant force of gravity pulling at your feet or they could be dynamic, caused by moving or vibrating the accelerometer . ");
        thingSpeakChannel.loadChannelFeed();
        lineChartView = (LineChartView)findViewById(R.id.accelerometerChart);
        thingSpeakLineChart = new ThingSpeakLineChart(482596,1);
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
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("ALERT");
        builder.setContentText("Acceleration Level Near Danger"+eventInfo);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
        builder.addAction(R.mipmap.ic_launcher,"Add",null);
        builder.addAction(R.mipmap.ic_launcher,"Close",null);
        NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(2,builder.build());
    }
}