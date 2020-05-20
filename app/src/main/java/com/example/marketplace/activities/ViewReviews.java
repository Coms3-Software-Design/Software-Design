package com.example.marketplace.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.marketplace.R;
import com.example.marketplace.adapters.ReviewsRecyclerViewAdapter;
import com.example.marketplace.classes.Product;
import com.example.marketplace.classes.Review;
import com.example.marketplace.helperclasses.AsyncHTTPPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewReviews extends AppCompatActivity {
    private RequestQueue rq;
    private final List<Review> ReviewList = new ArrayList<>();
    private String reviewURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPReviews.php";
    private Context context;
    private int productID;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reviews);
        context = this;
        productID = getIntent().getIntExtra("productID",-1);
        product = getIntent().getParcelableExtra("product");

        if(productID != -1){
        getSetReviews();}
        else
        {
            Toast.makeText(this, "Couldn't get Product ID",Toast.LENGTH_SHORT).show();
        }

        getSupportActionBar().setTitle("Review");
        getSupportActionBar().setSubtitle("Reviews for " + product.getProductName());

    }

    private void getSetReviews() {

        ContentValues cv = new ContentValues();
        cv.put("ProductID",productID);


        @SuppressLint("StaticFieldLeak")
        AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost(reviewURL, cv) {

            @Override
            protected void onPostExecute(String output) {



                try {
                    JSONArray array = new JSONArray(output);
                    for(int i = 0; i <array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        String RName = object.getString("Reviewers_Name");
                        String ReView = object.getString("Review");
                        float rating = (float) object.getDouble("Review_Rating");

                         Review review = new Review(RName,ReView,rating);
                         System.out.println(review);
                         ReviewList.add(review);
                    }
                    if(ReviewList.isEmpty()){
                        Toast.makeText(context,"No Reviews on this item, Be the first to add a review",Toast.LENGTH_LONG).show();
                    }
                    RecyclerView recyclerView =(RecyclerView) findViewById(R.id.reviewsRecyclerView);
                    ReviewsRecyclerViewAdapter myAdapter = new ReviewsRecyclerViewAdapter(context, ReviewList);
                     recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(myAdapter);

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        };

        asyncHTTPPost.execute();
    }
}
