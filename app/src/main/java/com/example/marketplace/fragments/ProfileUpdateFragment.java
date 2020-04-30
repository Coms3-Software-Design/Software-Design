package com.example.marketplace.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.marketplace.Homepage;
import com.example.marketplace.Login;
import com.example.marketplace.MySingleton;
import com.example.marketplace.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

import static android.app.Activity.RESULT_OK;

public class ProfileUpdateFragment extends Fragment {

    private Button update;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    private ImageView imgView;
    private String uploadUrl = "http://lamp.ms.wits.ac.za/~s1814731/upload.php";
    private Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_profile_update,container,false);
        context = v.getContext();
        update = v.findViewById(R.id.btnUpdate);
        imgView = v.findViewById(R.id.ivPropic);
        update.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
              //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new GoodsFragment()).commit();
                startActivity(new Intent(v.getContext(), Homepage.class));
                Toast.makeText(v.getContext(), "updated", Toast.LENGTH_LONG).show();
            }


        });
        
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        return v;
    }


    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent , IMG_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path =  data.getData();


            try {
               // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver() , path);
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),path);
                imgView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("fialed" );
            }

        }
    }

//    private void uploadImage(){
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, uploadUrl, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    String Responce = jsonObject.getString("response");
//                    Toast.makeText(Login.class,Responce,Toast.LENGTH_SHORT).show();
//                    imgView.setImageResource(0);
//                    imgView.setVisibility(View.GONE);
//                    Name.setText("");
//                    Name.setVisibility(View.GONE);
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    System.out.println(response);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.println(error);
//            }
//        }
//        )
//
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//             //   params.put("name",Name.getText().toString().trim());
//             //   params.put("image",imageToString(bitmap));
//
//                return params;
//            }
//        };
//
//        MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
//    }

    private  String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100 ,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes , Base64.DEFAULT);

    }

  

}
