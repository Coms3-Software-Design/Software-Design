package com.example.marketplace.activities;

import android.content.Intent;

import com.example.marketplace.BuildConfig;
import com.example.marketplace.classes.Product;
import com.example.marketplace.classes.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)

public class goodAndServiceTest {

    goodAndService goodNService;
    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent(RuntimeEnvironment.application , goodAndService.class);
        User user = new User("1814732","sam","Nkosi","Beast","12345","12345","today","LastNight","Male","Names",250,"1814732.jpeg");
        Product product = new Product(1,1814732,3,2,20,"Spoon","Cutlery","Stainless Steel","Good","goods");

        intent.putExtra("Category","Cutlery");
        intent.putExtra("user",user);
        intent.putExtra("type","goods");

       // goodNService = Robolectric.buildActivity(goodAndService.class,intent).setup().get();
        goodNService = Robolectric.buildActivity(goodAndService.class , intent).get();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void check(){
        assertNotNull(goodNService);
    }
}