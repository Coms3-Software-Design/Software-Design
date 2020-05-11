package com.example.marketplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class goodAndService extends AppCompatActivity {

    private RequestQueue rq;
    private final List<Product> listProds = new ArrayList<>();
    private String categoriesURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/products/products.php";
    private String category, goodsType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodsandservice);
         category = getIntent().getStringExtra("Category");
         goodsType = getIntent().getStringExtra("type");


         // Testing here
        String[] cats = {"Accessories","Cutlery","Electronics","Other","Shoes", "Stationery","Shams"};
        for(int i = 0; i < 20 ; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 7);
            String Desc = "djkalfj dslkfjdsoif osjaofsiud9 fao ijsoai ufs a98fa oisdohf sdoa oisarew0a uoijsof jsaoifj sof oisaof isa doi ufou0ewaur0 suofusdufauf9ay esiduefisafwea0";
            Product p = new Product(1,1,5,2,50,"Ball",cats[randomNum],"nike","Desc","");
            listProds.add(p);
        }

        RecyclerView recyclerView =  (RecyclerView) findViewById(R.id.viewProdRecyclerView);
        ProductsReclerViewAdapter prodAdapter = new ProductsReclerViewAdapter(this, listProds);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(prodAdapter);
    }
}
