package com.example.marketplace.activities;

import android.content.Intent;

import com.example.marketplace.classes.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)

public class HomepageTest {

    Homepage home;
    User user;

    @Before
    public void setUp() throws Exception {
        user = new User("1814732","s","d","c","P","0","27 aug","today","male","Hey man",25,"http://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/uploads/");
        Intent intent = new Intent(RuntimeEnvironment.systemContext , Homepage.class);
        intent.putExtra("user",user);
        home = Robolectric.buildActivity(Homepage.class,intent)
                .setup()
                .get();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void check(){
        assertNotNull(home);
    }



}