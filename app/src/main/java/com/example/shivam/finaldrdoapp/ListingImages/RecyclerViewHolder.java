package com.example.shivam.finaldrdoapp.ListingImages;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shivam.finaldrdoapp.R;

import java.util.List;

/**
 * Created by shivam on 25/7/17.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = RecyclerViewHolder.class.getSimpleName();
    public ImageView imageView;
    public TextView textView;
    Button show_on_map_button;
    public TextView time_text_view;
    public List<LandslideModel> taskObject;
    public RecyclerViewHolder(final View itemView,List<LandslideModel> landslideModels) {
        super(itemView);
        this.taskObject = landslideModels;
        time_text_view = (TextView)itemView.findViewById(R.id.time_text_view);
        show_on_map_button = (Button)itemView.findViewById(R.id.navigate_button);
        imageView = (ImageView)itemView.findViewById(R.id.custom_layout_image_view);
        textView  = (TextView)itemView.findViewById(R.id.custom_layout_text_view);
    }
}
