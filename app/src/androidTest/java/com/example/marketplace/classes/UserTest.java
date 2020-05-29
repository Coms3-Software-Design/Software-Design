package com.example.marketplace.classes;

import android.os.Parcel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class UserTest {
    private User user;
    private String name;
    private String surname;
    private String userName;
    private String password;
    private String dateOfBirth;
    private String gender;
    private String bio;
    private double balance;
    private String contactDetails, userID;
    private String dateCreated;
    private String proPicURL;

    @Before
    public void setUp() throws Exception {
         name = "shameel";
         surname = "Nkosi";
         userName = "Beastt";
         password = "12345";
         dateOfBirth = "27 Aug";
         gender = "male";
         bio = "Yeah";
         balance = 50;
         contactDetails = "0621214210";
         userID = "1814731";
         dateCreated = "today";
         proPicURL = userID.concat("jpeg");

         user = new User(userID,name,surname,userName,password,contactDetails,dateOfBirth,dateCreated,gender,bio,balance,proPicURL);
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void getDateCreated() throws NoSuchFieldException, IllegalAccessException {

        final Field field = user.getClass().getDeclaredField("dateCreated");
        field.setAccessible(true);
        field.set(user,"today");
        final String results = user.getDateCreated();
        assertEquals("today",results);
    }

    @Test
    public void getUserID() throws NoSuchFieldException, IllegalAccessException {
        final Field field = user.getClass().getDeclaredField("userID");
        field.setAccessible(true);
        field.set(user,"1814731");
        final String results = user.getUserID();
        assertEquals("1814731",results);
    }

    @Test
    public void setUserID() throws NoSuchFieldException, IllegalAccessException {
        user.setUserID("John");
        final Field field10 = user.getClass().getDeclaredField("userID");
        field10.setAccessible(true);
        assertEquals("John",field10.get(user));
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        final Field field = user.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(user,"shameel");
        final String results = user.getName();
        assertEquals("shameel",results);
    }

    @Test
    public void setName() throws NoSuchFieldException, IllegalAccessException {
        user.setName("John");
        final Field field9 = user.getClass().getDeclaredField("name");
        field9.setAccessible(true);
        assertEquals("John",field9.get(user));
    }

    @Test
    public void getSurname() throws NoSuchFieldException, IllegalAccessException {
        final Field field = user.getClass().getDeclaredField("surname");
        field.setAccessible(true);
        field.set(user,"Nkosi");
        final String results = user.getSurname();
        assertEquals("Nkosi",results);
    }

    @Test
    public void setSurname() throws NoSuchFieldException, IllegalAccessException {
        user.setSurname("John");
        final Field field8 = user.getClass().getDeclaredField("surname");
        field8.setAccessible(true);
        assertEquals("John",field8.get(user));
    }

    @Test
    public void getUserName() throws NoSuchFieldException, IllegalAccessException {
        final Field field = user.getClass().getDeclaredField("userName");
        field.setAccessible(true);
        field.set(user,"Beastt");
        final String results = user.getUserName();
        assertEquals("Beastt",results);
    }

    @Test
    public void setUserName() throws NoSuchFieldException, IllegalAccessException {
        user.setUserName("John");
        final Field field7 = user.getClass().getDeclaredField("userName");
        field7.setAccessible(true);
        assertEquals("John",field7.get(user));
    }

    @Test
    public void getPassword() throws NoSuchFieldException, IllegalAccessException {
        final Field field = user.getClass().getDeclaredField("password");
        field.setAccessible(true);
        field.set(user,"12345");
        final String results = user.getPassword();
        assertEquals("12345",results);
    }

    @Test
    public void setPassword() throws NoSuchFieldException, IllegalAccessException {
        user.setPassword("John");
        final Field field6 = user.getClass().getDeclaredField("password");
        field6.setAccessible(true);
        assertEquals("John",field6.get(user));
    }

    @Test
    public void getDateOfBirth() throws NoSuchFieldException, IllegalAccessException {
        final Field field = user.getClass().getDeclaredField("dateOfBirth");
        field.setAccessible(true);
        field.set(user,"27 Aug");
        final String results = user.getDateOfBirth();
        assertEquals("27 Aug",results);
    }

    @Test
    public void setDateOfBirth() throws NoSuchFieldException, IllegalAccessException {
        user.setDateOfBirth("John");
        final Field field5 = user.getClass().getDeclaredField("dateOfBirth");
        field5.setAccessible(true);
        assertEquals("John",field5.get(user));
    }

    @Test
    public void getGender() throws NoSuchFieldException, IllegalAccessException {
        final Field field = user.getClass().getDeclaredField("gender");
        field.setAccessible(true);
        field.set(user,"male");
        final String results = user.getGender();
        assertEquals("male",results);
    }

    @Test
    public void setGender() throws NoSuchFieldException, IllegalAccessException {
        user.setGender("male");
        final Field field3 = user.getClass().getDeclaredField("gender");
        field3.setAccessible(true);
        assertEquals("male",field3.get(user));
    }

    @Test
    public void getBio() throws NoSuchFieldException, IllegalAccessException {
        final Field field = user.getClass().getDeclaredField("bio");
        field.setAccessible(true);
        field.set(user,"Hey");
        final String results = user.getBio();
        assertEquals("Hey",results);
    }

    @Test
    public void setBio() throws NoSuchFieldException, IllegalAccessException {
        user.setBio("John");
        final Field field4 = user.getClass().getDeclaredField("bio");
        field4.setAccessible(true);
        assertEquals("John",field4.get(user));
    }

    @Test
    public void getBalance() throws NoSuchFieldException, IllegalAccessException {
        final Field field = user.getClass().getDeclaredField("balance");
        field.setAccessible(true);
        field.set(user,50);
        final double results = user.getBalance();
        assertEquals(50,results,50);
    }

    @Test
    public void setBalance() throws NoSuchFieldException, IllegalAccessException {
        user.setBalance(50);
        final Field field2 = user.getClass().getDeclaredField("balance");
        field2.setAccessible(true);
        assertEquals(50,user.getBalance(),50);
    }

    @Test
    public void getContactDetails() throws NoSuchFieldException, IllegalAccessException {
        final Field field = user.getClass().getDeclaredField("contactDetails");
        field.setAccessible(true);
        field.set(user,"0621214210");
        final String results = user.getContactDetails();
        assertEquals("0621214210",results);
    }

    @Test
    public void setContactDetails() throws NoSuchFieldException, IllegalAccessException {
        user.setContactDetails("0621214210");
        final Field field1 = user.getClass().getDeclaredField("contactDetails");
        field1.setAccessible(true);
        assertEquals("0621214210",field1.get(user));
    }

    @Test
    public void getProPicURL() throws NoSuchFieldException, IllegalAccessException {
        final Field field = user.getClass().getDeclaredField("proPicURL");
        field.setAccessible(true);
        field.set(user,"today");
        final String results = user.getProPicURL();
        assertEquals("today",results);
    }

    @Test
    public void setProPicURL() throws NoSuchFieldException, IllegalAccessException {
        user.setProPicURL("John");
        final Field field = user.getClass().getDeclaredField("proPicURL");
        field.setAccessible(true);
        assertEquals("John",field.get(user));
    }
    
    @Test
    public void User() throws NoSuchFieldException, IllegalAccessException {
        user.setProPicURL("John");
        final Field field = user.getClass().getDeclaredField("proPicURL");
        field.setAccessible(true);
        assertEquals("John",field.get(user));

        user.setContactDetails("0621214210");
        final Field field1 = user.getClass().getDeclaredField("contactDetails");
        field1.setAccessible(true);
        assertEquals("0621214210",field1.get(user));

        user.setBalance(50);
        final Field field2 = user.getClass().getDeclaredField("balance");
        field2.setAccessible(true);
        assertEquals(50,user.getBalance(),50);

        user.setBio("John");
        final Field field4 = user.getClass().getDeclaredField("bio");
        field4.setAccessible(true);
        assertEquals("John",field4.get(user));

        user.setGender("male");
        final Field field3 = user.getClass().getDeclaredField("gender");
        field3.setAccessible(true);
        assertEquals("male",field3.get(user));

        user.setDateOfBirth("John");
        final Field field5 = user.getClass().getDeclaredField("dateOfBirth");
        field5.setAccessible(true);
        assertEquals("John",field5.get(user));

        user.setPassword("John");
        final Field field6 = user.getClass().getDeclaredField("password");
        field6.setAccessible(true);
        assertEquals("John",field6.get(user));

        user.setUserName("John");
        final Field field7 = user.getClass().getDeclaredField("userName");
        field7.setAccessible(true);
        assertEquals("John",field7.get(user));

        user.setSurname("John");
        final Field field8 = user.getClass().getDeclaredField("surname");
        field8.setAccessible(true);
        assertEquals("John",field8.get(user));

        user.setName("John");
        final Field field9 = user.getClass().getDeclaredField("name");
        field9.setAccessible(true);
        assertEquals("John",field9.get(user));

        user.setUserID("John");
        final Field field10 = user.getClass().getDeclaredField("userID");
        field10.setAccessible(true);
        assertEquals("John",field10.get(user));

    }
    @Test
    public void describeContents() {
    }

    @Test
    public void writeToParcel() {
    }
}