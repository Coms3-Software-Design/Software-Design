package com.example.marketplace.classes;

import java.util.Date;

public class Transaction {
    private int transactionID , productID, boughtByID;
    private Date transactionDate;

    Transaction(int transactionID ,int productID , int boughtByID,Date transactionDate)
    {
        this.transactionID = transactionID;
        this.productID = productID;
        this.boughtByID = boughtByID;
        this.transactionID = transactionID;
    }

    public Date getTransactionDate() {
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
}
