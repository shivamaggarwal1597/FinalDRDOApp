package com.example.shivam.finaldrdoapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shivam.finaldrdoapp.ListingImages.LandslideModel;
import com.example.shivam.finaldrdoapp.ListingImages.RecyclerViewAdapter;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ShowMotionImages extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    List<LandslideModel> landslideModels;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_motion_images);
        landslideModels = new ArrayList<LandslideModel>();
        recyclerView = (RecyclerView)findViewById(R.id.recycle);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseStorage = FirebaseStorage.getInstance();




        databaseReference.child("Image_DB").addValueEventListener(new ValueEventListener() {

            @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
                landslideModels.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
              LandslideModel lm=ds.getValue(LandslideModel.class);
              landslideModels.add(lm);
              recyclerViewAdapter = new RecyclerViewAdapter(ShowMotionImages.this,landslideModels);
              recyclerView.setAdapter(recyclerViewAdapter);
                }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }
}
