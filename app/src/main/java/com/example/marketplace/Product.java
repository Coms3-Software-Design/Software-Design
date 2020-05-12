package com.example.marketplace;

import android.media.Image;

public class Product {
    private  int productID , userID, currentQuantity, soldQuantity;
    private double pricePerItem;
    private String productName , category, productBrand;
    private String productDescription;
    private String productPicture;
    private String productsURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/Products/";

    Product(int productID , int userID, int currentQuantity, int soldQuantity, double pricePerItem,
            String productName , String category, String productBrand, String productDescription){
        this.productID = productID;
        this.userID = userID;
        this.currentQuantity = currentQuantity;
        this.soldQuantity = soldQuantity;
        this.pricePerItem = pricePerItem;
        this.productName = productName;
        this.category = category;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productPicture = productsURL.concat(String.valueOf(productID)).concat(".jpg").concat("?=" + System.currentTimeMillis());

    }

    /*
     * Getters and setters here
     */
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(double pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }
}


//