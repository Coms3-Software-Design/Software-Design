package com.example.marketplace.classes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class ProductTest {

    private Product product;

    @Before
    public void setUp() throws Exception {
        int productID = 1,userID = 12, currentQuantity = 5, soldQuantity=2;
         double pricePerItem = 20;
         String productName = "shoes", category="tech", productBrand= "nike",prodType = "goods";
         String productDescription= "comfort";
         String productPicture="1.jpeg";
         String productsURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/Products/";

        product = new Product(productID,userID,currentQuantity,soldQuantity,pricePerItem,productName,category,productBrand,productDescription,prodType);
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void getProdType() throws NoSuchFieldException, IllegalAccessException {

        final Field field = product.getClass().getDeclaredField("prodType");
        field.setAccessible(true);
        field.set(product,"goods");
        final String results = product.getProdType();
        assertEquals("goods",results);


    }

    @Test
    public void setProdType() throws NoSuchFieldException, IllegalAccessException {
        product.setProdType("John");
        final Field field10 = product.getClass().getDeclaredField("prodType");
        field10.setAccessible(true);
        assertEquals("John",field10.get(product));
    }

    @Test
    public void getProductID() throws NoSuchFieldException, IllegalAccessException {
        final Field field = product.getClass().getDeclaredField("productID");
        field.setAccessible(true);
        field.set(product,1);
        final int results = product.getProductID();
        assertEquals(1,results);
    }

    @Test
    public void setProductID() throws NoSuchFieldException, IllegalAccessException {
        product.setProductID(5);
        final Field field10 = product.getClass().getDeclaredField("productID");
        field10.setAccessible(true);
        assertEquals( 5,field10.get(product));
    }

    @Test
    public void getUserID() throws NoSuchFieldException, IllegalAccessException {
        final Field field = product.getClass().getDeclaredField("userID");
        field.setAccessible(true);
        field.set(product,1);
        final int results = product.getUserID();
        assertEquals(1,results);
    }

    @Test
    public void setUserID() throws NoSuchFieldException, IllegalAccessException {
        product.setUserID(4);
        final Field field10 = product.getClass().getDeclaredField("userID");
        field10.setAccessible(true);
        assertEquals(4,field10.get(product));
    }

    @Test
    public void getCurrentQuantity() throws NoSuchFieldException, IllegalAccessException {
        final Field field = product.getClass().getDeclaredField("currentQuantity");
        field.setAccessible(true);
        field.set(product,1);
        final int results = product.getCurrentQuantity();
        assertEquals(1,results);
    }

    @Test
    public void setCurrentQuantity() throws NoSuchFieldException, IllegalAccessException {
        product.setCurrentQuantity(5);
        final Field field10 = product.getClass().getDeclaredField("currentQuantity");
        field10.setAccessible(true);
        assertEquals(5,field10.get(product));
    }

    @Test
    public void getSoldQuantity() throws NoSuchFieldException, IllegalAccessException {
        final Field field = product.getClass().getDeclaredField("soldQuantity");
        field.setAccessible(true);
        field.set(product,1);
        final int results = product.getSoldQuantity();
        assertEquals(1,results);
    }

    @Test
    public void setSoldQuantity() throws NoSuchFieldException, IllegalAccessException {

        product.setSoldQuantity(5);
        final Field field10 = product.getClass().getDeclaredField("soldQuantity");
        field10.setAccessible(true);
        assertEquals(5,field10.get(product));
    }

    @Test
    public void getPricePerItem() throws NoSuchFieldException, IllegalAccessException {
        final Field field = product.getClass().getDeclaredField("pricePerItem");
        field.setAccessible(true);
        field.set(product,1.0);
        final double results = product.getPricePerItem();
        assertEquals(1.0,results,1.0);
    }

    @Test
    public void setPricePerItem() throws NoSuchFieldException, IllegalAccessException {
        product.setPricePerItem(5.0);
        final Field field10 = product.getClass().getDeclaredField("pricePerItem");
        field10.setAccessible(true);
        assertEquals(5.0,field10.get(product));
    }

    @Test
    public void getProductName() throws NoSuchFieldException, IllegalAccessException {
        final Field field = product.getClass().getDeclaredField("productName");
        field.setAccessible(true);
        field.set(product,"goods");
        final String results = product.getProductName();
        assertEquals("goods",results);
    }

    @Test
    public void setProductName() throws NoSuchFieldException, IllegalAccessException {
        product.setProductName("John");
        final Field field10 = product.getClass().getDeclaredField("productName");
        field10.setAccessible(true);
        assertEquals("John",field10.get(product));
    }

    @Test
    public void getCategory() throws NoSuchFieldException, IllegalAccessException {
        final Field field = product.getClass().getDeclaredField("category");
        field.setAccessible(true);
        field.set(product,"goods");
        final String results = product.getCategory();
        assertEquals("goods",results);
    }

    @Test
    public void setCategory() throws NoSuchFieldException, IllegalAccessException {
        product.setCategory("John");
        final Field field10 = product.getClass().getDeclaredField("category");
        field10.setAccessible(true);
        assertEquals("John",field10.get(product));
    }

    @Test
    public void getProductBrand() throws NoSuchFieldException, IllegalAccessException {

        final Field field = product.getClass().getDeclaredField("productBrand");
        field.setAccessible(true);
        field.set(product,"goods");
        final String results = product.getProductBrand();
        assertEquals("goods",results);
    }

    @Test
    public void setProductBrand() throws NoSuchFieldException, IllegalAccessException {
        product.setProductBrand("John");
        final Field field10 = product.getClass().getDeclaredField("productBrand");
        field10.setAccessible(true);
        assertEquals("John",field10.get(product));
    }

    @Test
    public void getProductDescription() throws NoSuchFieldException, IllegalAccessException {
        final Field field = product.getClass().getDeclaredField("productDescription");
        field.setAccessible(true);
        field.set(product,"goods");
        final String results = product.getProductDescription();
        assertEquals("goods",results);
    }

    @Test
    public void setProductDescription() throws NoSuchFieldException, IllegalAccessException {
        product.setProductDescription("John");
        final Field field10 = product.getClass().getDeclaredField("productDescription");
        field10.setAccessible(true);
        assertEquals("John",field10.get(product));
    }

    @Test
    public void getProductPicture() throws NoSuchFieldException, IllegalAccessException {
        final Field field = product.getClass().getDeclaredField("productPicture");
        field.setAccessible(true);
        field.set(product,"goods");
        final String results = product.getProductPicture();
        assertEquals("goods",results);
    }

    @Test
    public void setProductPicture() throws NoSuchFieldException, IllegalAccessException {
        product.setProductPicture("John");
        final Field field10 = product.getClass().getDeclaredField("productPicture");
        field10.setAccessible(true);
        assertEquals("John",field10.get(product));
    }

    @Test
    public void describeContents() {
    }

    @Test
    public void writeToParcel() {
    }
}