package com.example.shivam.finaldrdoapp.ListingImages;

/**
 * Created by shivam on 25/7/17.
 */

public class LandslideModel {

    public String image ;

    public LandslideModel(String image, String desc) {
        this.image = image;
        this.desc = desc;
    }

    public LandslideModel() {


    }

    public String desc ;

    public String getImage() {
        return image;
    }

    public void setPath(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
