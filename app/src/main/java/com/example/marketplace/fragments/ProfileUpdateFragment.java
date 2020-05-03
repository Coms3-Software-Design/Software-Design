package com.example.marketplace.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.marketplace.Homepage;
import com.example.marketplace.Login;
import com.example.marketplace.MySingleton;
import com.example.marketplace.R;
import com.example.marketplace.User;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

import static android.app.Activity.RESULT_OK;

public class ProfileUpdateFragment extends AppCompatDialogFragment {



    private EditText Bio , fName , lName , nPass , oPass, pNumber ;
    private RadioGroup genderGroup;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    private ImageView imgView;
    private String uploadUrl = "http://lamp.ms.wits.ac.za/~s1814731/upload.php";
    private Context context;
    private Button changePass , changeGender;
    private User user;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v =  inflater.inflate(R.layout.fragment_profile_update,null);
        context = v.getContext();
        imgView = v.findViewById(R.id.ivPropic);
        Bio = v.findViewById(R.id.etBio);
        fName = v.findViewById(R.id.etFname);
        lName = v.findViewById(R.id.etLname);
        nPass = v.findViewById(R.id.etPassword);
        oPass = v.findViewById(R.id.etoldPassword);
        pNumber = v.findViewById(R.id.etPhone);
        changePass = v.findViewById(R.id.btnChangePass);
        changeGender = v.findViewById(R.id.btnChangeGender);
        genderGroup = v.findViewById(R.id.radioGroup);


        changeGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderGroup.setVisibility(View.VISIBLE);
            }
        });

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nPass.setVisibility(View.VISIBLE);
                oPass.setVisibility(View.VISIBLE);
            }
        });

//        Bio.setText(user.getBio());
//        fName.setText(user.getName());
//        lName.setText(user.getSurname());
//        pNumber.setText(user.getContactDetails());
//
//        int radioButtonID = genderGroup.getCheckedRadioButtonId();
//        RadioButton R = (RadioButton) v.findViewById(radioButtonID);
//        if(R.getText() == "Female" && user.getGender().equals("FeMale")){
//
//        }
//        else{
//            R.setChecked(false);
//        }






        //imgView.setImageURI(Uri.parse());
        setIMG("http://lamp.ms.wits.ac.za/~s1814731/Sami.jpg");


        builder.setView(v)
        .setTitle("Profile Edit")
        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

    private void setIMG(String uri) {

        Picasso.with(context).load(uri).placeholder(R.drawable.ic_edit_profile)
                .error(R.drawable.ic_edit_profile)
                .into(imgView,new com.squareup.picasso.Callback(){

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
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

    private void uploadImage(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, uploadUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Responce = jsonObject.getString("response");
                    Toast.makeText(context,Responce,Toast.LENGTH_SHORT).show();
                    imgView.setImageResource(0);
                    imgView.setVisibility(View.GONE);
                   // Name.setText("");
                    //Name.setVisibility(View.GONE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }
        )

        {
            @Override
            protected Map<String, String > getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
             //   params.put("name",Name.getText().toString().trim());
             //   params.put("image",imageToString(bitmap));

                return params;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private  String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100 ,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes , Base64.DEFAULT);

    }

  

}
