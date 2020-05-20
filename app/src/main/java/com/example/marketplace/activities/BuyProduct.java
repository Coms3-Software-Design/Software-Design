package com.example.marketplace.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marketplace.R;
import com.example.marketplace.classes.Product;
import com.example.marketplace.fragments.addReviewFragment;
import com.squareup.picasso.Picasso;

public class BuyProduct extends AppCompatActivity {
    private Product product;
    private Button btnWriteReview , btnBuyProd;
    private ImageView buyProductImage;
    private TextView productName, productPrice, productDescription, viewAllReviews;
    private addReviewFragment review;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product);
        product = getIntent().getParcelableExtra("product");

        buyProductImage = findViewById(R.id.imgBuyProduct);
        productName = findViewById(R.id.tvBuyProdProdName);
        productPrice = findViewById(R.id.tvBuyProdProdPrice);
        productDescription = findViewById(R.id.tvBuyProdDescProd);
        viewAllReviews = findViewById(R.id.tvBuyProdViewAllReviews);
        btnBuyProd = findViewById(R.id.btnBuyProd);
        btnWriteReview = findViewById(R.id.btnWriteReview);


        // This here sets the texts of the buy or sell button
        if(product.getProdType().equals("Goods")){
            btnBuyProd.setText("Buy Product");
        }else {
            btnBuyProd.setText("Hire Service");
        }

        viewAllReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnBuyProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    private void writeReview() {
        review = new addReviewFragment();
        review.setProduct(product);
        review.show(getSupportFragmentManager(),"Write a review");

    }

    /*
    * The function below gives the views the product's information for it to view
    */
    private void setStringAttr() {
        productName.setText(product.getProductName());
        productPrice.setText("R"+ product.getPricePerItem());
        productDescription.setText(product.getProductDescription());
    }
    /*
     * The function below sets views image to the image of the product
     */
    private void setImage() {
        Picasso.get().load(product.getProductPicture()).placeholder(R.drawable.ic_edit_profile)
                .error(R.drawable.ic_edit_profile).into(buyProductImage);
    }
}
