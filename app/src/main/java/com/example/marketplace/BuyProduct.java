package com.example.marketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.TagLostException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class BuyProduct extends AppCompatActivity {
    private Product product;
    private Button btnWriteReview , btnBuyProd;
    private ImageView buyProductImage;
    private  TextView productName, productPrice, productDescription, viewAllReviews;


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

            }
        });


        setImage();
        setStringAttr();

    }

    private void setStringAttr() {
        productName.setText(product.getProductName());
        productPrice.setText("R"+ product.getPricePerItem());
        productDescription.setText(product.getProductDescription());
    }

    private void setImage() {
        Picasso.get().load(product.getProductPicture()).placeholder(R.drawable.ic_edit_profile)
                .error(R.drawable.ic_edit_profile).into(buyProductImage);
    }
}
