package com.example.marketplace.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

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


    /*
    * Below is the User's constractor
    * */
    public User(String userID, String name, String surname, String userName, String password, String contactDetails, String dateOfBirth, String dateCreated, String gender,
                String bio, double balance, String Profilepic){

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
        this.proPicURL = Profilepic;
 // still need to figure out how to put a picture into the data base
    }

    /*
    * Getters and setters below
    */

    protected User(Parcel in) {
        name = in.readString();
        surname = in.readString();
        userName = in.readString();
        password = in.readString();
        dateOfBirth = in.readString();
        gender = in.readString();
        bio = in.readString();
        balance = in.readDouble();
        contactDetails = in.readString();
        userID = in.readString();
        dateCreated = in.readString();
        proPicURL = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getDateCreated() {
        return dateCreated;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getProPicURL() {
        return proPicURL;
    }

    public void setProPicURL(String proPicURL) {
        this.proPicURL = proPicURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(dateOfBirth);
        dest.writeString(gender);
        dest.writeString(bio);
        dest.writeDouble(balance);
        dest.writeString(contactDetails);
        dest.writeString(userID);
        dest.writeString(dateCreated);
        dest.writeString(proPicURL);
    }

    /*
     * Getters and setters end here
     */
}

