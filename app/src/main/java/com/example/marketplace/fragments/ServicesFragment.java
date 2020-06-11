package com.example.marketplace.fragments;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.marketplace.adapters.ProductsReclerViewAdapter;
import com.example.marketplace.classes.Category;
import com.example.marketplace.R;
import com.example.marketplace.adapters.ServiceCategoriesRecylerViewAdapter;
import com.example.marketplace.classes.Product;
import com.example.marketplace.classes.User;
import com.example.marketplace.helperclasses.AsyncHTTPPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServicesFragment extends Fragment {

    private RequestQueue rq;
    private User  user;
    private final List<Category> listCategories = new ArrayList<>();
    private String categoriesURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/categories/categories.php";
    private String category, goodsType;
    private final List<Product> listProds = new ArrayList<>();
    private String productsURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/Products/products.php";
    private Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  return inflater.inflate(R.layout.frament_services,container,false);
        final View v = inflater.inflate(R.layout.frament_services, container, false);
        if(getArguments() != null){
            user = getArguments().getParcelable("user");
        }
        context = v.getContext();

        // initializeList();
//        rq = Volley.newRequestQueue(v.getContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, categoriesURL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    for(int i = 0; i < response.length() ; i++){
//                        Category category = new Category(response.getJSONObject(i).getString("Category"));
//                        listCategories.add(category);
//                        System.out.println(listCategories.get(i).getTitle());
//                    }
//                    RecyclerView recyclerView = v.findViewById(R.id.ServicesRecyclerView);
//                    ServiceCategoriesRecylerViewAdapter myAdapter = new ServiceCategoriesRecylerViewAdapter(v.getContext(),listCategories,user);
//                    recyclerView.setLayoutManager(new GridLayoutManager(v.getContext(), 2));
//                    recyclerView.setAdapter(myAdapter);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(v.getContext(),"Error occured",Toast.LENGTH_SHORT).show();
//            }
//        });
//        rq.add(jsonArrayRequest);


        category = "Services";
        goodsType = "Services";





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
                    RecyclerView recyclerView = v.findViewById(R.id.ServicesRecyclerView);
                    ProductsReclerViewAdapter prodAdapter = new ProductsReclerViewAdapter(context, listProds,user);
                    recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                    recyclerView.setAdapter(prodAdapter);


                }
                else{
                    Toast.makeText(context , "Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        };
        asyncHTTPPost.execute();


        return v;

    }
}
