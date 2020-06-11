package com.example.marketplace.activities;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.marketplace.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class RegisterTest {

    private EditText UserID,Name,Last_Name,Password,PhoneNumber, userName;
    private RadioGroup radioGroup;
    private Button btnRegister;
    private TextView DOBtext;
    private Calendar dateOB = Calendar.getInstance();
    private Date dateOfBirth , dateCreated;
    private Register register;

    @Before
    public void setUp() throws Exception {

        register = Robolectric.buildActivity(Register.class)
                .create()
                .resume()
                .get();

        UserID = register.findViewById(R.id.etUserID);
        Name = register.findViewById(R.id.etFname);
        Last_Name = register.findViewById(R.id.etLname);
        Password = register.findViewById(R.id.etPassword);
        PhoneNumber = register.findViewById(R.id.etPhone);
        userName = register.findViewById(R.id.etUserName);
        DOBtext = register.findViewById(R.id.tvDOB);
        btnRegister = register.findViewById(R.id.btnRegister);
        radioGroup = register.findViewById(R.id.radioGroup);
        PhoneNumber = register.findViewById(R.id.etPhone);
        dateCreated = dateOB.getTime(); // this sets the current date before we change it to the date of birth
        dateOfBirth = dateOB.getTime();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Check(){
        assertNotNull(register);
    }

    @Test
    public void CheckUser(){
        Name.setText("");
        btnRegister.performClick();
        Name.setText("Bee");
        btnRegister.performClick();
        UserID.setText("");
        btnRegister.performClick();
        UserID.setText("Bee");
        Name.setText("Shams");
        btnRegister.performClick();
        UserID.setText("Bee");
        Name.setText("Shams");
        Last_Name.setText("Phiri");
        btnRegister.performClick();
        UserID.setText("Bee");
        Name.setText("Shams");
        Last_Name.setText("Phiri");
        Password.setText("12345");
        btnRegister.performClick();
        UserID.setText("Bee");
        Name.setText("Shams");
        Last_Name.setText("Phiri");
        Password.setText("12345");
        userName.setText("Beast");
        PhoneNumber.setText("1234567892");
        radioGroup.getCheckedRadioButtonId();
        DOBtext.performClick();
        btnRegister.performClick();


    }

    @Test
    public void fix(){

        userName.setText("85274963");
        Name.setText("John");
        Last_Name.setText("Phiri");
        PhoneNumber.setText("1234567890");
        UserID.setText("1478529");
        Password.setText("");
        btnRegister.performClick();

        userName.setText("85274963");
        Name.setText("");
        Last_Name.setText("Phiri");
        PhoneNumber.setText("1234567890");
        UserID.setText("1478529");
        Password.setText("12345");
        btnRegister.performClick();

        userName.setText("85274963");
        Name.setText("John");
        Last_Name.setText("");
        PhoneNumber.setText("1234567890");
        UserID.setText("1478529");
        Password.setText("45612");
        btnRegister.performClick();

        userName.setText("85274963");
        Name.setText("John");
        Last_Name.setText("Phiri");
        PhoneNumber.setText("");
        UserID.setText("1478529");
        Password.setText("12345");
        btnRegister.performClick();

        userName.setText("85274963");
        Name.setText("John");
        Last_Name.setText("Phiri");
        PhoneNumber.setText("1234567890");
        UserID.setText("");
        Password.setText("12345");
        btnRegister.performClick();

        userName.setText("874963");
        Name.setText("John");
        Last_Name.setText("Phiri");
        PhoneNumber.setText("6789067890");
        UserID.setText("1478589");
        Password.setText("12345");
        btnRegister.performClick();
    }
    @Test
    public void Checkusers(){


        UserID.setText("Bee");
        Name.setText("Shams");
        Last_Name.setText("Phiri");
        Password.setText("12345");
        userName.setText("Beast");
        PhoneNumber.setText("1234567892");
        btnRegister.performClick();

        userName.setText("Bee");
        btnRegister.performClick();

        userName.setText("Bee");
        Name.setText("sam");
        btnRegister.performClick();

        userName.setText("Bee");
        Name.setText("sam");
        Last_Name.setText("Doe");
        btnRegister.performClick();

        userName.setText("Bee");
        Name.setText("sam");
        Last_Name.setText("Doe");
        PhoneNumber.setText("1234567890");
        btnRegister.performClick();

        userName.setText("Bee");
        Name.setText("sam");
        Last_Name.setText("Doe");
        PhoneNumber.setText("1234567890");
        UserID.setText("1234512");
        btnRegister.performClick();

        userName.setText("Bee");
        Name.setText("sam");
        Last_Name.setText("Doe");
        PhoneNumber.setText("1234567890");
        UserID.setText("1234512");
        Password.setText("12345");
        register.checkSelectedRadioButton();
        btnRegister.performClick();




    }
}