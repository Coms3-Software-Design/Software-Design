package com.example.marketplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class goodAndService extends AppCompatActivity {

    private RequestQueue rq;
    private final List<Product> listProds = new ArrayList<>();
    private String productsURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/Products/products.php";
    private String category, goodsType;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodsandservice);
         category = getIntent().getStringExtra("Category");
         goodsType = getIntent().getStringExtra("type");
         context = this;

         // Testing here
        String[] cats = {"Accessories","Cutlery","Electronics","Other","Shoes", "Stationery","Shams"};
//        for(int i = 0; i < 20 ; i++) {
//            int randomNum = ThreadLocalRandom.current().nextInt(0, 7);
//            String Desc = "djkalfj dslkfjdsoif osjaofsiud9 fao ijsoai ufs a98fa oisdohf sdoa oisarew0a uoijsof jsaoifj sof oisaof isa doi ufou0ewaur0 suofusdufauf9ay esiduefisafwea0";
//            Product p = new Product(1,1,5,2,50.0,"Ball",cats[randomNum],"nike","Desc");
//            listProds.add(p);
//        }
//
//        RecyclerView recyclerView =  (RecyclerView) findViewById(R.id.viewProdRecyclerView);
//        ProductsReclerViewAdapter prodAdapter = new ProductsReclerViewAdapter(this, listProds);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.setAdapter(prodAdapter);

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

                                Product product = new Product(P_ID,U_ID,C_quant,S_quant,price,P_Name,P_category,P_Brand,P_Desc);
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
