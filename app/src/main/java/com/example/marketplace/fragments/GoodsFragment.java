package com.example.marketplace.fragments;

import android.annotation.SuppressLint;
import android.app.VoiceInteractor;
import android.content.ContentValues;
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
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.marketplace.AsyncHTTPPost;
import com.example.marketplace.CategoriesRecylerViewAdapter;
import com.example.marketplace.Category;
import com.example.marketplace.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GoodsFragment extends Fragment {
    private RequestQueue rq;
    private JSONArray cat;
    private final List<Category> listCategories = new ArrayList<>();
    private String categoriesURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/categories/categories.php";

    public GoodsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View v = inflater.inflate(R.layout.fragment_goods, container, false);

      // initializeList();
        rq = Volley.newRequestQueue(v.getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, categoriesURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                        cat = response;
                try {
                    for(int i = 0; i < response.length() ; i++){
                        Category category = new Category(response.getJSONObject(i).getString("Category"));
                        listCategories.add(category);
                        System.out.println(listCategories.get(i).getTitle());
                    }
                    RecyclerView recyclerView = v.findViewById(R.id.vRecyclerView);
                    CategoriesRecylerViewAdapter myAdapter = new CategoriesRecylerViewAdapter(v.getContext(),listCategories);
                    recyclerView.setLayoutManager(new GridLayoutManager(v.getContext(), 2));
                    recyclerView.setAdapter(myAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(v.getContext(),"Error occured",Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(jsonArrayRequest);





        return v;

    }

    public void initializeList(){
        ContentValues cv = new ContentValues();
        cv.put("cat","yeah");

        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost(categoriesURL,cv) {
            @Override
            protected void onPostExecute(String output) {
                try {
                     JSONArray categories =  new JSONArray(output);
                    for(int i = 0; i < categories.length() ; i++){
                        listCategories.add(i,new Category(categories.getJSONObject(i).getString("Category")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        asyncHTTPPost.execute();
    }

}