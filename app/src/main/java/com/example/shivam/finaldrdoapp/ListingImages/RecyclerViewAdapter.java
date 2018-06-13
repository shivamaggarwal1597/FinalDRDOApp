package com.example.shivam.finaldrdoapp.ListingImages;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.shivam.finaldrdoapp.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by shivam on 25/7/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>  {
    public List<LandslideModel> model;
    public Context context;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference ;
    public FirebaseStorage firebaseStorage;
    public StorageReference storageReference;
    public RecyclerViewAdapter(Context context, List<LandslideModel> model) {
        this.model = model;
        this.context = context;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout, parent, false);
        viewHolder = new RecyclerViewHolder(layoutView, model);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
       firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReferenceFromUrl("gs://drdoapp.appspot.com/").child("media");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        String path = model.get(position).getImage();
        String text = model.get(position).getDesc().toString();
        String buff[] = text.split("at ");
        String jell = buff[1];
        String kell[] = jell.split("__");
        String date_arr[] = kell[0].split("_");
        String day_of_landslide = date_arr[2];
        String month_of_landslide = date_arr[1];
        String year_of_landslide = date_arr[0];
        String time_arr_d[] = kell[1].split("jp");
        String time_arr[] = time_arr_d[0].split("_");
        holder.textView.setText("Date - "+day_of_landslide+" : "+month_of_landslide+" : "+year_of_landslide);
        holder.time_text_view.setText("Time - "+time_arr[0]+" : "+time_arr[1]+" : "+time_arr[2]);
        Glide.with(context).using(new FirebaseImageLoader()).load(storageReference.child(path)).into(holder.imageView);
        holder.show_on_map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDirections(31.6340, 74.8723, 28.7041, 77.1025);
            }
        });
    }

    public void showDirections(double lat, double lng, double lat1, double lng1) {

        final Intent intent = new
                Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" +
                "saddr=" + lat + "," + lng + "&daddr=" + lat1 + "," +
                lng1));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        context.startActivity(intent);

    }
    @Override
    public int getItemCount() {
        return model.size();
    }
}
