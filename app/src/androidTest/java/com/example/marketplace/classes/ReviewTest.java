package com.example.marketplace.classes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class ReviewTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void Review() throws NoSuchFieldException, IllegalAccessException {
        String Rev = "shameel";
        String Review = "hey theree";
        float rate = (float) 2.5;
        final Review temp = new Review(Rev , Review, rate);
        temp.setReviewer("John");
        temp.setReview("Hey");

        final Field field = temp.getClass().getDeclaredField("Reviewer");
        final  Field field1 = temp.getClass().getDeclaredField("review");
        final  Field field2 = temp.getClass().getDeclaredField("rating");
        field.setAccessible(true);
        field1.setAccessible(true);
        field2.setAccessible(true);

        assertEquals("John",field.get(temp));
        assertEquals("Hey",field1.get(temp));
        assertEquals(rate,field2.get(temp));

    }

    @Test
    public void getReviewer() throws NoSuchFieldException, IllegalAccessException{
        String Rev = "shameel";
        String Review = "hey theree";
        float rate = (float) 2.5;
        final Review temp = new Review(Rev , Review, rate);
        final Field field = temp.getClass().getDeclaredField("Reviewer");
        field.setAccessible(true);
        field.set(temp,"shameel");
        final String results = temp.getReviewer();
        assertEquals("shameel",results);
    }

    @Test
    public void setReviewer() throws NoSuchFieldException, IllegalAccessException {
        String Rev = "shameel";
        String Review = "hey theree";
        float rate = (float) 2.5;
        final Review temp = new Review(Rev , Review, rate);
        temp.setReviewer("John");
        final Field field = temp.getClass().getDeclaredField("Reviewer");
        field.setAccessible(true);
        assertEquals("John",field.get(temp));
    }

    @Test
    public void getReview() throws NoSuchFieldException,IllegalAccessException {
        String Rev = "shameel";
        String Review = "hey theree";
        float rate = (float) 2.5;
        final Review temp = new Review(Rev , Review, rate);
        final Field field = temp.getClass().getDeclaredField("review");
        field.setAccessible(true);
        field.set(temp,"hey theree");
        final String results = temp.getReview();
        assertEquals("hey theree",results);
    }

    @Test
    public void setReview() throws NoSuchFieldException, IllegalAccessException {
        String Rev = "shameel";
        String Review = "hey theree";
        float rate = (float) 2.5;
        final Review temp = new Review(Rev , Review, rate);

        temp.setReview("Hey shameel");

        final Field field = temp.getClass().getDeclaredField("review");
        field.setAccessible(true);
        assertEquals("Hey shameel",field.get(temp));

    }

    @Test
    public void getRatings() throws NoSuchFieldException, IllegalAccessException {
        String Rev = "shameel";
        String Review = "hey theree";
        float rate = (float) 2.5;
        final Review temp = new Review(Rev , Review, rate);
        final Field field = temp.getClass().getDeclaredField("rating");
        field.setAccessible(true);
        field.set(temp,(float)2.5);
        final float results = temp.getRatings();
        //noinspection deprecation
        assertEquals( rate,results,2.5);
    }
}