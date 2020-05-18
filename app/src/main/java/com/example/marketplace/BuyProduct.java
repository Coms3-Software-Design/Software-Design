package com.example.marketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.TagLostException;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BuyProduct extends AppCompatActivity {
    private Product product;
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

        viewAllReviews.setOnClickListener(new View.OnClickListener() {
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
