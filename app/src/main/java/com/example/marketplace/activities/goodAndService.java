package com.example.marketplace.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.marketplace.R;
import com.example.marketplace.adapters.ProductsReclerViewAdapter;
import com.example.marketplace.classes.Product;
import com.example.marketplace.helperclasses.AsyncHTTPPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class goodAndService extends AppCompatActivity {

    private RequestQueue rq;
    private final List<Product> listProds = new ArrayList<>();
    private String productsURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/Products/products.php";
    private String category, goodsType;
    private LinearLayout l;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodsandservice);
         category = getIntent().getStringExtra("Category");
         goodsType = getIntent().getStringExtra("type");
         context = this;

         if(goodsType.equals("Services")){
             getSupportActionBar().setTitle("Services");
         }else{
             getSupportActionBar().setTitle("Goods");
         }

        ContentValues cv = new ContentValues();
        cv.put("category", category);
        cv.put("type",goodsType);


        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost(productsURL, cv) {
            @Override
            protected void onPostExecute(String output) {
                if(!output.equals("failed")){

                    JSONArray array = null;
                    try {
                        array = new JSONArray(output);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for(int i = 0; i < array.length() ; i++) {
                            try {
                                JSONObject obj = array.getJSONObject(i);
                                int P_ID = Integer.parseInt(obj.getString("Product_ID"));
                                int U_ID = Integer.parseInt(obj.getString("UserID"));
                                int C_quant = Integer.parseInt(obj.getString("Current_Quantity"));
                                int S_quant = Integer.parseInt(obj.getString("Sold_Quantity"));
                                double price = Integer.parseInt(obj.getString("Product_Price"));
                                String P_Name = obj.getString("Product_Name");
                                String P_category = obj.getString("Category");
                                String P_Brand = obj.getString("Product_Brand");
                                String P_Desc = obj.getString("Product_Description");

                                Product product = new Product(P_ID,U_ID,C_quant,S_quant,price,P_Name,P_category,P_Brand,P_Desc,goodsType);
                                listProds.add(product);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    RecyclerView recyclerView =  (RecyclerView) findViewById(R.id.viewProdRecyclerView);
                    ProductsReclerViewAdapter prodAdapter = new ProductsReclerViewAdapter(context, listProds);
                    recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                    recyclerView.setAdapter(prodAdapter);


                }
                else{
                    Toast.makeText(context , "Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        };
        asyncHTTPPost.execute();
    }
}
