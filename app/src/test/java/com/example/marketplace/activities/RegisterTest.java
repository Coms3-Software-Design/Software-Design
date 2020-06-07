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
        radioGroup.getCheckedRadioButtonId();
        DOBtext.performClick();
        btnRegister.performClick();


    }
}