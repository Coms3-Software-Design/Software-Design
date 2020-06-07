package com.example.marketplace.activities;

import android.content.Intent;

import com.example.marketplace.classes.Product;
import com.example.marketplace.classes.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.marketplace.R;
import com.example.marketplace.adapters.ProductsReclerViewAdapter;
import com.example.marketplace.classes.Product;
import com.example.marketplace.classes.User;
import com.example.marketplace.helperclasses.AsyncHTTPPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ViewReviewsTest {

    ViewReviews reviews;
    BuyProduct buyProd;


    Product product = new Product(1,1814732,5,3,20,"Spoon","Cutlery","stainless steel","This spoon doesnt get dirty","goods");
    User user = new User("1814732","sam","Nkosi","Bee","12345","123","now","today","male","we",50,"");

    int ProductID = 1;


    @Before
    public void setUp() throws Exception {
        Intent intent0 = new Intent(RuntimeEnvironment.application,ViewReviews.class);
        intent0.putExtra("productID",ProductID);
        intent0.putExtra("product",product);
        reviews = Robolectric.buildActivity(ViewReviews.class,intent0).setup().get();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void check(){
        assertNotNull(reviews);
    }

}