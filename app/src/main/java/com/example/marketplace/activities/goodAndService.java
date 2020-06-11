package com.example.marketplace.activities;

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

public class goodAndService extends AppCompatActivity {

    private final List<Product> listProds = new ArrayList<>();
    private String productsURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/Products/products.php";
    private String category, goodsType;
    private User user;
    private String resetUserURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPReturnUser.php";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodsandservice);
        context = this;
         category = getIntent().getStringExtra("Category");
         goodsType = getIntent().getStringExtra("type");
         user = getIntent().getParcelableExtra("user");


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
                    RecyclerView recyclerView = findViewById(R.id.viewProdRecyclerView);
                    ProductsReclerViewAdapter prodAdapter = new ProductsReclerViewAdapter(context, listProds,user);
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


    public void resetUser() {
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

    @Override
    public void onBackPressed() {
        resetUser();
        Intent intent = new Intent(context,Homepage.class);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }
}
