package com.example.marketplace.classes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

import static org.junit.Assert.*;

public class CategoryTest {
    private String title , image;
    private static String imgURLPrefix = "http://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/categories/";
    private Category category;
    @Before
    public void setUp() throws Exception {
        title = "Same";
        image = "Yey";
        category = new Category(title);

    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void getTitle() throws NoSuchFieldException, IllegalAccessException {

        final Field field = category.getClass().getDeclaredField("title");
        field.setAccessible(true);
        field.set(category,title);
        final String results = category.getTitle();
        assertEquals(title,results);
    }

    @Test
    public void setTitle() throws NoSuchFieldException, IllegalAccessException {
        category.setTitle(title);
        final Field field10 = category.getClass().getDeclaredField("title");
        field10.setAccessible(true);
        assertEquals(title,field10.get(category));
    }

    @Test
    public void getImage() throws NoSuchFieldException, IllegalAccessException {
        final Field field = category.getClass().getDeclaredField("image");
        field.setAccessible(true);
        field.set(category,image);
        final String results = category.getImage();
        assertEquals(image,results);
    }

    @Test
    public void setImage() throws NoSuchFieldException, IllegalAccessException {
        category.setImage(title);
        final Field field10 = category.getClass().getDeclaredField("image");
        field10.setAccessible(true);
        assertEquals(title,field10.get(category));
    }
}