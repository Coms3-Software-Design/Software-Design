package com.example.marketplace;

import java.util.Date;

public class User {

    private String name;
    private String surname;
    private String userName;
    private String password;
    private String dateOfBirth;
    private String gender;
    private StringBuilder bio;
    private double balance;
    private int contactDetails, userID;
    private Date dateCreated;


    /*
    * Below is the User's constractor
    * */
    User(String name, String surname, String userName, String password, String dateOfBirth, String gender, Date dateCreated,
         StringBuilder bio , double balance , int contactDetails, int userID){

        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.dateCreated = dateCreated;
        this.bio = bio;
        this.balance = balance;
        this.contactDetails = contactDetails;

    }

    /*
    * Getters and setters below
    */

    public Date getDateCreated() {
        return dateCreated;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public StringBuilder getBio() {
        return bio;
    }

    public void setBio(StringBuilder bio) {
        this.bio = bio;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(int contactDetails) {
        this.contactDetails = contactDetails;
    }

    /*
     * Getters and setters end here
     */
}

