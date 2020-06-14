package com.example.marketplace.fragments;

import com.example.marketplace.classes.User;

import org.bouncycastle.crypto.tls.PRFAlgorithm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;


public class ProfileUpdateFragmentTest {

    User user;
    ProfileUpdateFragment profile;

    @Before
    public void setUp() throws Exception {
        user = new User("1814732", "Sam","Johns","Bee","12345","0123456789","then","now","Male","I love", 20,"1814732.jpeg");
        profile = new ProfileUpdateFragment();
        profile.setUser(user);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void setUser() {
    //    profile.changePass.performClick();
        profile.setUser(user);
        assertNotNull(profile);
    }
}