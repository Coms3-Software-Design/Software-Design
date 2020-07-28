package com.example.marketplace.classes;

import java.util.Date;

public class Transaction {
    private int transactionID , productID, boughtByID,ownerID, productPrice;
    private String productName, productOwnerName;
    private String transactionDate;

    public Transaction(int transactionID, int productID, String productName, int ownerID, String productOwner, int boughtByID, String transactionDate,int productPrice)
    {
        this.productName = productName;
        this.ownerID = ownerID;
        this.productOwnerName = productOwner;
        this.transactionID = transactionID;
        this.productID = productID;
        this.boughtByID = boughtByID;
        this.transactionID = transactionID;
        this.productPrice = productPrice;
        this.transactionDate = transactionDate;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getBoughtByID() {
        return boughtByID;
    }

    public void setBoughtByID(int boughtByID) {
        this.boughtByID = boughtByID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductOwnerName() {
        return productOwnerName;
    }

    public void setProductOwnerName(String productOwnerName) {
        this.productOwnerName = productOwnerName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
