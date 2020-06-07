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
    BuyProduct buyProduct;
    Homepage home;

    Intent intent = new Intent(RuntimeEnvironment.application , goodAndService.class);
    Intent intent1 = new Intent(RuntimeEnvironment.application , BuyProduct.class);
    Intent intent2 = new Intent(RuntimeEnvironment.application , Homepage.class);

    @Before
    public void setUp() throws Exception {

        User user = new User("1814732","sam","Nkosi","Beast","12345","12345","today","LastNight","Male","Names",250,"1814732.jpeg");
        Product product = new Product(1,1814732,3,2,20,"Spoon","Cutlery","Stainless Steel","Good","goods");

        intent.putExtra("Category","Cutlery");
        intent.putExtra("user",user);
        intent.putExtra("type","goods");

        intent1.putExtra("product",product);
        intent1.putExtra("user",user);

        intent2.putExtra("user",user);


       // goodNService = Robolectric.buildActivity(goodAndService.class,intent).setup().get();
        goodNService = Robolectric.buildActivity(goodAndService.class , intent).setup().get();
        buyProduct = Robolectric.buildActivity(BuyProduct.class , intent1).setup().get();
        //home = Robolectric.buildActivity(Homepage.class , intent2).setup() .get();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void check(){
        assertNotNull(goodNService);
    }

    @Test
    public void checkb(){
        assertNotNull(buyProduct);
    }




}