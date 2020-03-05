package com.example.marketplace;

import android.media.Image;

public class Product {
    private  int productID , userID, currentQuantity, soldQuantity;
    private double pricePerItem;
    private String productName , category, productBrand;
    private StringBuilder productDescription;
    private Image productPicture;

    Product(int productID , int userID, int currentQuantity, int soldQuantity, double pricePerItem,
            String productName , String category, String productBrand, StringBuilder productDescription, Image productPicture){
        this.productID = productID;
        this.userID = userID;
        this.currentQuantity = currentQuantity;
        this.soldQuantity = soldQuantity;
        this.pricePerItem = pricePerItem;
        this.productName = productName;
        this.category = category;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productPicture = productPicture;

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

    public StringBuilder getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(StringBuilder productDescription) {
        this.productDescription = productDescription;
    }

    public Image getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(Image productPicture) {
        this.productPicture = productPicture;
    }
}
