package com.example.marketplace.activities;

import android.content.Intent;


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
    BuyProduct buyProduct , buyProduct2;
    Homepage home;

    Intent intent = new Intent(RuntimeEnvironment.application , goodAndService.class);
    Intent intent1 = new Intent(RuntimeEnvironment.application , BuyProduct.class);
    Intent intent3 = new Intent(RuntimeEnvironment.application , BuyProduct.class);
    Intent intent2 = new Intent(RuntimeEnvironment.application , Homepage.class);

    @Before
    public void setUp() throws Exception {

        User user = new User("1814732","sam","Nkosi","Beast","12345","12345","today","LastNight","Male","Names",250,"1814732.jpeg");
        Product product = new Product(1,1814732,3,2,20,"Spoon","Services","Stainless Steel","Good","Goods");
        Product product2 = new Product(1,1814732,0,2,20,"Spoon","Services","Stainless Steel","Good","Services");

        intent.putExtra("Category","Cutlery");
        intent.putExtra("user",user);
        intent.putExtra("type","goods");

        intent1.putExtra("product",product);
        intent1.putExtra("user",user);
        intent3.putExtra("product",product2);
        intent3.putExtra("user",user);

        intent2.putExtra("user",user);


        goodNService = Robolectric.buildActivity(goodAndService.class , intent).create().resume().get();

        buyProduct = Robolectric.buildActivity(BuyProduct.class , intent1).create().resume().get();
        buyProduct2 = Robolectric.buildActivity(BuyProduct.class , intent3).create().resume().get();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void check(){
        goodNService.resetUser();
        assertNotNull(goodNService);
    }

    @Test
    public void checkb(){
        assertNotNull(buyProduct);
        buyProduct.onBackPressed();
        buyProduct.btnBuyProd.performClick();
        buyProduct.btnWriteReview.performClick();
        buyProduct.viewAllReviews.performClick();
        buyProduct.onPostResume();
        assertNotNull(buyProduct2);
        buyProduct2.btnBuyProd.performClick();
        buyProduct2.btnWriteReview.performClick();
        buyProduct2.viewAllReviews.performClick();
        buyProduct2.onPostResume();
    }





}