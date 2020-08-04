package com.example.marketplace.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.marketplace.R;
import com.example.marketplace.classes.Product;
import com.example.marketplace.classes.User;
import com.example.marketplace.fragments.addReviewFragment;
import com.example.marketplace.fragments.buyProductDFragment;
import com.example.marketplace.helperclasses.AsyncHTTPPost;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BuyProduct extends AppCompatActivity {
    private Product product;
    private User user;
    private addReviewFragment review;
    private buyProductDFragment buyProd;
    public Button btnWriteReview, btnBuyProd;
    private ImageView buyProductImage;
    public TextView productName, productPrice, productDescription, viewAllReviews, availability,dilivery;
    private Context context;
    private RatingBar productRating;

    private String reviewURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPReviews.php";
    private String resetUserURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPReturnUser.php";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product);
        product = getIntent().getParcelableExtra("product");
        user = getIntent().getParcelableExtra("user");
        buyProductImage = findViewById(R.id.imgBuyProduct);
        productName = findViewById(R.id.tvBuyProdProdName);
        productPrice = findViewById(R.id.tvBuyProdProdPrice);
        productDescription = findViewById(R.id.tvBuyProdDescProd);
        viewAllReviews = findViewById(R.id.tvBuyProdViewAllReviews);
        btnBuyProd = findViewById(R.id.btnBuyProd);
        btnWriteReview = findViewById(R.id.btnWriteReview);
        productRating = findViewById(R.id.rbProdRating);
        availability = findViewById(R.id.pm);
        dilivery = findViewById(R.id.tvDilivery);
        context = this;

        // Set the Rating bar to correspond to the rating of the product
        setProductRating();

        // This here sets the texts of the buy or sell button
        if (product.getProdType().equals("Goods")) {
            btnBuyProd.setText("Buy Product");
        } else {
            btnBuyProd.setText("Hire Service");
        }

        if(product.getCurrentQuantity()<1){
            availability.setText("Out Of Stock");
            availability.setTextColor(R.color.ourRed);
        }
        viewAllReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyProduct.this, ViewReviews.class);
                intent.putExtra("productID", product.getProductID());
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });


        btnBuyProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(product.getCurrentQuantity()>0) {
                    buyProduct();
                }
                else{
                    Toast.makeText(context,"Out of stock",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnWriteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeReview();
            }
        });


        setImage();
        setStringAttr();

    }


    /*
    Sets the products rating bar to an appropriate rating status
     */
    private void setProductRating() {
        ContentValues cv = new ContentValues();
        cv.put("ProductID", product.getProductID());
        @SuppressLint("StaticFieldLeak")
        AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost(reviewURL, cv) {

            @Override
            protected void onPostExecute(String output) {

                float rating = 0;

                try {
                    JSONArray array = new JSONArray(output);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        rating += (float) object.getDouble("Review_Rating");
                    }
                    if (array.length() != 0) {
                        rating = rating / array.length();
                    }

                    productRating.setRating(rating);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        asyncHTTPPost.execute();
    }


    /*
    Opens a Dialog Fragment
    Which Allows you to add a review and rate a product
     */
    private void writeReview() {
        review = new addReviewFragment();
        review.setUser(user);
        review.setProduct(product);
        review.show(getSupportFragmentManager(), "");

    }


    /*
     * Opens a dialog fragment
     * which allows you to make a transaction if you have enough money
     * or  tells you your balance is insufficient otherwise
     */
    private void buyProduct() {
        buyProd = new buyProductDFragment();
        buyProd.setUser(user);
        buyProd.setProduct(product);
        buyProd.show(getSupportFragmentManager(), "");
    }

    /*
     * The function below gives the views the product's information for it to view
     */
    private void setStringAttr() {
        if (!product.getProdType().equals("Goods")) {
            dilivery.setText("CONTACT US!");
        }
        productName.setText(product.getProductName());
        productPrice.setText("R" + product.getPricePerItem());
        productDescription.setText(product.getProductDescription());
    }


    /*
     * The function below sets views image to the image of the product
     */
    private void setImage() {
        Glide.with(context).load(product.getProductPicture()).into(buyProductImage);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onPostResume() {
        if(product.getCurrentQuantity()<1){
            availability.setText("Out Of Stock");
            availability.setTextColor(R.color.ourRed);
        }
        setProductRating();
        super.onPostResume();
    }


    @Override
    public void onBackPressed() {
        resetUser();
        Intent intent = new Intent(context,goodAndService.class);
        intent.putExtra("Category",product.getCategory());
        intent.putExtra("type",product.getProdType());
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }

    /*
     * Updates the Logged in users details to match with the ones that are in the database
     * This is needed after a the user buys the product and their balance has been updated
     */
    private void resetUser() {
        ContentValues cv = new ContentValues();
        cv.put("username", user.getUserName());

        @SuppressLint("StaticFieldLeak")
        AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost(resetUserURL, cv) {

            @Override
            public void onPostExecute(String output) {

                if (output.equals("!exists")) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();

                } else {


                    // [{"UserID":"1596357","Name":"shameel nkosi","Surname":"nkosi","UserName":"G","ContactNum":"2255889966","Balance":"0","Bio":null,"D_O_B":"06 Apr 2020","Date_Created":"06 Apr 2020","Gender":"Male","Profile_pic":null}]
                    try {
                        // Only userID and balance is an integer


                        final JSONObject userJO = new JSONArray(output).getJSONObject(0); // JO in userJO for JSONObject
                        String userID = Integer.toString(userJO.getInt("UserID"));
                        int Balance = userJO.getInt("Balance");
                        String Name = userJO.getString("Name");
                        String Surname = userJO.getString("Surname");
                        String UserName = userJO.getString("UserName");
                        //String Password = userJO.getString("Password");
                        String Password = user.getPassword();
                        String ContactNum = userJO.getString("ContactNum");
                        String Bio = userJO.getString("Bio");
                        String D_O_B = userJO.getString("D_O_B");
                        String Date_Created = userJO.getString("Date_Created");
                        String Gender = userJO.getString("Gender");
                        String Profile_Pic = userJO.getString("Profile_pic");


                        user = new User(userID, Name, Surname, UserName, Password, ContactNum, D_O_B, Date_Created, Gender, Bio, Balance, Profile_Pic);


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }


                }
            }
        };
        asyncHTTPPost.execute();
    }
}
