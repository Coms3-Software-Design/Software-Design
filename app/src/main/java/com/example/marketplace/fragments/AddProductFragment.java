package com.example.marketplace.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.camera2.TotalCaptureResult;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.marketplace.R;

import com.example.marketplace.classes.Category;
import com.example.marketplace.classes.User;
import com.example.marketplace.helperclasses.AsyncHTTPPost;
import com.example.marketplace.helperclasses.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static android.app.Activity.RESULT_OK;


public class AddProductFragment extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {

    private User user;
    private EditText prodName, prodBrand
            ,prodDesc , prodPrice , prodQuant;
    private Spinner categories, productType;
    private ImageView imgView;
    private Bitmap bitmap;
    private final int IMG_REQUEST = 0;
    private Context context;
    //private String categoriesURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/categories/categories.php";
    private String itemUpload = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/Products/creatProd.php";
    private String uploadURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/Products/upload.php";
    private String prodCategory = "";
    private ArrayList<String> listCategories = new ArrayList<>();
    private final List<String> list = new ArrayList<>();
    private Boolean changedIMG = false;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        context = getContext();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.fragment_addprod , null);

        prodName = v.findViewById(R.id.etProdName);
        prodBrand = v.findViewById(R.id.etProdbrand);
        prodDesc = v.findViewById(R.id.etProdDesc);
        prodPrice = v.findViewById(R.id.etProdPrice);
        prodQuant = v.findViewById(R.id.etProdQuant);
        categories = v.findViewById(R.id.snCategory);
        imgView = v.findViewById(R.id.imgNewProd);

        //Below we populate the product type and the categories spinners respectively
        String[] str = {"Accessories","Cutlery","Electronics","Other","Services","Shoes","Stationery"};

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(context , android.R.layout.simple_list_item_1, str);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapter);
        categories.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        builder.setView(v).setTitle("create product")
                .setPositiveButton("Create" , null )
                .setNegativeButton("Cancel", null);

        final AlertDialog createProd = builder.create();

        createProd.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button b = createProd.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        createItem(createProd);
                    }



                });
            }
        });
        return createProd;
    }

    public void setUser(User user){
        this.user = user;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String a = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext() , a , Toast.LENGTH_SHORT).show();
        prodCategory = a;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void createItem(final AlertDialog createProd){



        if(prodName.getText().toString().trim().equals("")){
            prodName.setError("Enter Product Name");
            return;
        }
        if(prodBrand.getText().toString().trim().equals("")){
            prodBrand.setError("Enter Product Brand");
            return;
        }
        if(prodDesc.getText().toString().trim().equals("")){
            prodDesc.setError("Enter Product Description");
            return;
        }
        if(prodPrice.getText().toString().trim().equals("")){
            prodPrice.setError("Enter Product price");
            return;
        }
        if(prodQuant.getText().toString().trim().equals("")){
            prodName.setError("Enter Product Quantity");
            return;
        }

        if(!changedIMG){
            Toast.makeText(context ,"Please click on the image to select an image",Toast.LENGTH_LONG).show();
            return;
        }


        String pName = prodName.getText().toString().trim();
        String pBrand = prodBrand.getText().toString().trim();
        String pDesc = prodDesc.getText().toString().trim();
        double pPrice = Double.parseDouble(prodPrice.getText().toString());
        int pQuant = Integer.parseInt(prodQuant.getText().toString());

        ContentValues cv = new ContentValues();

        cv.put("pName" , pName);
        cv.put("user" , user.getUserID());
        cv.put("pBrand",pBrand);
        cv.put("pPrice",pPrice);
        cv.put("pDesc" , pDesc);
        cv.put("pCat" , prodCategory);

        if(!prodCategory.equals("Services")){
            cv.put("pType","goods");
        }
        else {
            cv.put("pType","services");
            pQuant = 10000000;
        }

        cv.put("pQuant" , pQuant);

        @SuppressLint("StaticFieldLeak")
        AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost(itemUpload , cv) {

            @Override
            protected void onPostExecute(String output) {

                Toast.makeText(context , output , Toast.LENGTH_LONG).show();
                if(output.equals("1")){
                    uploadImage();
                    createProd.dismiss();
                }

            }
        };
        asyncHTTPPost.execute();



    }

//    private String[] populateCategoriesSpinner(){
//
//        ContentValues cv = new ContentValues();
//        final String[][] temp = new String[1][1];
//        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost(categoriesURL, cv) {
//            @Override
//            protected void onPostExecute(String output){
//                if(!output.equals("failed")){
//
//                    JSONArray array = null;
//                    try {
//                        array = new JSONArray(output);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    list.add("Category");
//                    for(int i = 0; i < array.length() ; i++) {
//                        try {
//                            if(array.getJSONObject(i).getString("Category").equals("Services")) continue;
//                            list.add(array.getJSONObject(i).getString("Category"));
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    String[] str = new String[list.size()];
//                    for(int i = 0; i < str.length ; i ++ ){
//                        str[i] = list.get(i);
//                    }
//                    ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(context , android.R.layout.simple_list_item_1, str);
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    categories.setAdapter(adapter);
//                    temp[0] = str;
//                }
//                else{
//                    Toast.makeText(context , "Something went wrong",Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
//        asyncHTTPPost.execute();
//
//        return temp[0];
//    }


    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();


            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), path);
                imgView.setImageBitmap(bitmap);
                changedIMG = true;

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("failed");
            }

        }
    }

    private void uploadImage() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, uploadURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Responce = jsonObject.getString("response");
                    Toast.makeText(context, Responce, Toast.LENGTH_SHORT).show();
                    imgView.setImageResource(0);

                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                Toast.makeText(context,"" + error, Toast.LENGTH_SHORT).show();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("image", imageToString(bitmap));
                return params;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);

    }

}

//        +---------------------+----------------+------+-----+---------+----------------+
//        | Field               | Type           | Null | Key | Default | Extra          |
//        +---------------------+----------------+------+-----+---------+----------------+
//        | Product_ID          | int(255)       | NO   | PRI | NULL    | auto_increment |
//        | UserID              | int(13)        | YES  | MUL | NULL    |                |
//        | Category            | varchar(255)   | YES  | MUL | NULL    |                |
//        | Product_Name        | varchar(255)   | NO   |     | NULL    |                |
//        | Product_Brand       | varchar(255)   | YES  |     | NULL    |                |
//        | Product_Description | varchar(5000)  | YES  |     | NULL    |                |
//        | Product_Price       | int(255)       | NO   |     | 0       |                |
//        | Current_Quantity    | int(255)       | NO   |     | 0       |                |
//        | Sold_Quantity       | int(255)       | NO   |     | 0       |                |
//        | Product_Pic         | varchar(10000) | YES  |     | NULL    |                |
//        | Product_type        | varchar(20)    | YES  |     | NULL    |                |
//        +---------------------+----------------+------+-----+---------+----------------+
