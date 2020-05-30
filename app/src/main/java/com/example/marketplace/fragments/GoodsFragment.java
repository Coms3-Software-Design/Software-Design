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
import com.example.marketplace.adapters.GoodsCategoriesRecylerViewAdapter;
import com.example.marketplace.classes.Category;
import com.example.marketplace.R;
import com.example.marketplace.classes.User;
import com.example.marketplace.helperclasses.AsyncHTTPPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GoodsFragment extends Fragment{

    private RequestQueue rq;
    private final List<Category> listCategories = new ArrayList<>();
    private String categoriesURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/categories/categories.php";
    private User user;
    private Context context;
    private String resetUserURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPReturnUser.php";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View v = inflater.inflate(R.layout.fragment_goods, container, false);
       context = v.getContext();

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

    @Override
    public void onResume() {
        resetUser();
        super.onResume();
    }

    /*
     * Updates the Logged in users details to match with the ones that are in the database
     * This is needed after a the user buys the product and their balance has been updated
     */
    private void resetUser() {
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
}