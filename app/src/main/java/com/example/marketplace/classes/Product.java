package com.example.marketplace.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private  int productID , userID, currentQuantity, soldQuantity;
    private double pricePerItem;
    private String productName , category, productBrand,prodType;
    private String productDescription;
    private String productPicture;
    private String productsURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/Products/";

    public Product(int productID, int userID, int currentQuantity, int soldQuantity, double pricePerItem,
                   String productName, String category, String productBrand, String productDescription, String prodType){
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
        this.prodType = prodType;

    }

    protected Product(Parcel in) {
        productID = in.readInt();
        userID = in.readInt();
        currentQuantity = in.readInt();
        soldQuantity = in.readInt();
        pricePerItem = in.readDouble();
        productName = in.readString();
        category = in.readString();
        productBrand = in.readString();
        productDescription = in.readString();
        productPicture = in.readString();
        productsURL = in.readString();
        prodType = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    /*
     * Getters and setters here
     */

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productID);
        dest.writeInt(userID);
        dest.writeInt(currentQuantity);
        dest.writeInt(soldQuantity);
        dest.writeDouble(pricePerItem);
        dest.writeString(productName);
        dest.writeString(category);
        dest.writeString(productBrand);
        dest.writeString(productDescription);
        dest.writeString(productPicture);
        dest.writeString(productsURL);
        dest.writeString(prodType);
    }
}


//