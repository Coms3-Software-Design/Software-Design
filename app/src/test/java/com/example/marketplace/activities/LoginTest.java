package com.example.marketplace.activities;

import android.widget.EditText;

import com.example.marketplace.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class LoginTest {

    private Login login;
    private EditText userName , Password;

    @Before
    public void setUp() throws Exception {
        login = Robolectric.buildActivity(Login.class)
                .create()
                .resume()
                .get();

        userName = login.findViewById(R.id.etStudentNo_Login);
        Password =  login.findViewById(R.id.etPassword_Login);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void check(){
        assertNotNull(login);
    }

    @Test
    public void checkUser(){
        userName.setText("Bee");
        login.findViewById(R.id.btnSignIn).performClick();
    }

    @Test
    public void checkUser1(){
        userName.setText("Bee");
        Password.setText("");
        login.findViewById(R.id.btnSignIn).performClick();
    }

    @Test
    public void checkUser2(){
        userName.setText("Bee");
        Password.setText("12345");
        login.findViewById(R.id.btnSignIn).performClick();
    }

    @Test
    public void checkUser4(){
        userName.setText("");
        Password.setText("");
        login.findViewById(R.id.btnSignIn).performClick();
    }
}