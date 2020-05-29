package com.example.marketplace.classes;

import android.icu.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

import static org.junit.Assert.*;

public class TransactionTest {
    private Transaction transaction;
    private int transactionID = 5, productID = 5, boughtByID = 5;
    private Date transactionDate = new Date();
    @Before
    public void setUp() throws Exception {

        transaction = new Transaction(transactionID , productID,boughtByID,transactionDate);
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void getTransactionDate() throws NoSuchFieldException, IllegalAccessException {

        final Field field = transaction.getClass().getDeclaredField("transactionDate");
        field.setAccessible(true);
        field.set(transaction,transactionDate);
        final Date results = transaction.getTransactionDate();
        assertEquals(transactionDate,results);
    }

    @Test
    public void getTransactionID() throws NoSuchFieldException, IllegalAccessException {
        final Field field = transaction.getClass().getDeclaredField("transactionID");
        field.setAccessible(true);
        field.set(transaction,transactionID);
        final int results = transaction.getTransactionID();
        assertEquals(transactionID,results);
    }

    @Test
    public void setTransactionID() throws NoSuchFieldException, IllegalAccessException {

        transaction.setTransactionID(transactionID);
        final Field field10 = transaction.getClass().getDeclaredField("transactionID");
        field10.setAccessible(true);
        assertEquals(transactionID,field10.get(transaction));
    }

    @Test
    public void getProductID() throws NoSuchFieldException, IllegalAccessException {
        final Field field = transaction.getClass().getDeclaredField("productID");
        field.setAccessible(true);
        field.set(transaction,productID);
        final int results = transaction.getProductID();
        assertEquals(productID,results);
    }

    @Test
    public void setProductID() throws NoSuchFieldException, IllegalAccessException {

        transaction.setProductID(productID);
        final Field field10 = transaction.getClass().getDeclaredField("productID");
        field10.setAccessible(true);
        assertEquals(productID,field10.get(transaction));
    }

    @Test
    public void getBoughtByID() throws NoSuchFieldException, IllegalAccessException {
        final Field field = transaction.getClass().getDeclaredField("boughtByID");
        field.setAccessible(true);
        field.set(transaction,boughtByID);
        final int results = transaction.getBoughtByID();
        assertEquals(boughtByID,results);
    }

    @Test
    public void setBoughtByID() throws NoSuchFieldException, IllegalAccessException {
        transaction.setBoughtByID(boughtByID);
        final Field field10 = transaction.getClass().getDeclaredField("boughtByID");
        field10.setAccessible(true);
        assertEquals(boughtByID,field10.get(transaction));
    }
}