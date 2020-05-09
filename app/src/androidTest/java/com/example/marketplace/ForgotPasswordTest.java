package com.example.marketplace;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ForgotPasswordTest {
    @Rule
    public ActivityTestRule<ForgotPassword> forgotPasswordActivityTestRule = new ActivityTestRule<ForgotPassword>(ForgotPassword.class);

     private  ForgotPassword forgotPassword = null;

    @Before
    public void setUp() throws Exception {
        forgotPassword = forgotPasswordActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        forgotPassword = null;
    }

    @Test
    public void onCreate() {
        View view = forgotPassword.findViewById(R.id.loForgotPass);
        assertNotNull(view);
    }
}