package com.example.marketplace.fragments;

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
import com.example.marketplace.adapters.GoodsCategoriesRecylerViewAdapter;
import com.example.marketplace.classes.Category;
import com.example.marketplace.R;
import com.example.marketplace.classes.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GoodsFragment extends Fragment{

    private RequestQueue rq;
    private final List<Category> listCategories = new ArrayList<>();
    private String categoriesURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/categories/categories.php";
    private User user;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View v = inflater.inflate(R.layout.fragment_goods, container, false);

        if(getArguments() != null){
           user = getArguments().getParcelable("user");
        }
        else Toast.makeText(v.getContext(),"Something is wrong, Can't set User",Toast.LENGTH_SHORT).show();
      // initializeList();
        rq = Volley.newRequestQueue(v.getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, categoriesURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i < response.length() ; i++){
                        Category category = new Category(response.getJSONObject(i).getString("Category"));
                        listCategories.add(category);
                    }

                    RecyclerView recyclerView = v.findViewById(R.id.GoodsRecyclerView);
                    GoodsCategoriesRecylerViewAdapter myAdapter = new GoodsCategoriesRecylerViewAdapter(v.getContext(),listCategories,user);
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
}