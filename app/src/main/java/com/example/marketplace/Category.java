package com.example.marketplace;

public class Category {

    private String title , image;
    private static String imgURLPrefix = "http://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/categories/";
   public Category(String title){
        this.title = title;
        this.image = imgURLPrefix.concat(imgURLPrefix);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
