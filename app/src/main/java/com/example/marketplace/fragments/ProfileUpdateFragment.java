package com.example.marketplace.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.marketplace.helperclasses.AsyncHTTPPost;
import com.example.marketplace.activities.Homepage;
import com.example.marketplace.helperclasses.MySingleton;
import com.example.marketplace.R;
import com.example.marketplace.classes.User;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class ProfileUpdateFragment extends AppCompatDialogFragment {


    public EditText Bio, fName, lName, nPass, oPass, pNumber;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    private ImageView imgView;
    private String uploadUrl = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/uploads/upload.php";
    private String imgURLPrefix = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/uploads/";
    private Context context;
    public Button changePass;
    private User user;
    private Boolean editpass = false;
    boolean changedIMG = false;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.fragment_profile_update, null);
        context = v.getContext();
        imgView = v.findViewById(R.id.ivPropic);
        Bio = v.findViewById(R.id.etBio);
        fName = v.findViewById(R.id.etFname);
        lName = v.findViewById(R.id.etLname);
        nPass = v.findViewById(R.id.etPassword);
        oPass = v.findViewById(R.id.etoldPassword);
        pNumber = v.findViewById(R.id.etPhone);
        changePass = v.findViewById(R.id.btnChangePass);

        Toast.makeText(context, "Click on the image to change your profile pic", Toast.LENGTH_LONG).show();

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nPass.setVisibility(View.VISIBLE);
                oPass.setVisibility(View.VISIBLE);
                editpass = true;
            }
        });

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        Bio.setText(user.getBio());
        if (Bio.getText().toString().equals("null")) {
            Bio.setText("");
            Bio.setHint("Please Enter Bio");
        }

        fName.setText(user.getName());
        lName.setText(user.getSurname());
        pNumber.setText(user.getContactDetails());

        setIMG(imgURLPrefix.concat(user.getUserID()).concat(".jpg").concat("?=" + System.currentTimeMillis()));

        builder.setView(v)
                .setTitle("Profile Edit")
                .setNegativeButton("cancel", null)
                .setPositiveButton("update",null);

        final AlertDialog mAlertDialog = builder.create();

        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        updateDetails();
                        if(changedIMG) uploadImage();
                    }
                });
            }
        });
        return  mAlertDialog;
    }

    public void updateDetails() {
        String bio = Bio.getText().toString().trim();
        String name = fName.getText().toString().trim();
        String sName = lName.getText().toString().trim();
        String pNum = pNumber.getText().toString().trim();
        String passwrd = user.getPassword();

        // Below we check if the customer has errors from any input

        if(bio.isEmpty()){
            Bio.setError("Tell us a little bit about yourself");
            return;
        }

        if(name.equals("")){
            fName.setError("Your name cannot be left blank");
            return;
        }

        if(sName.equals("")){
            lName.setError("Your last name cannot be left blank");
            return;
        }

        if(pNum.equals("") || pNum.length()!=10){
            pNumber.setError("Please Enter a 10 digit phone number");
            return;
        }

        // Below We Check if the user wants to change their password then work accordingly
        if(editpass){
            String oldPass = oPass.getText().toString();
            String newPass = nPass.getText().toString();

            // we ensure that the user puts in a valid password for authentication and then we can update afterwards
            if(oldPass.equals("") || !oldPass.equals(user.getPassword())){
                oPass.setError("Please Enter a Password Matching your current pass");
                return;
            }
            if(newPass.equals("")){
                nPass.setError("Please Enter a new Password");
                return;
            }
            passwrd = newPass;

        }

       // Toast.makeText(context,bio+name+sName+pNum+passwrd,Toast.LENGTH_LONG).show();
        user.setPassword(passwrd);
        user.setBio(bio);
        user.setName(name);
        user.setSurname(sName);
        user.setContactDetails(pNum);

        ContentValues cv = new ContentValues();
        cv.put("userID",user.getUserID());
        cv.put("name",name);
        cv.put("surname",sName);
        cv.put("pNum",pNum);
        cv.put("bio",bio);
        cv.put("password",passwrd);

        @SuppressLint("StaticFieldLeak")AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPUpdateProfile.php" , cv) {

            @Override
            protected void onPostExecute(String output) {
                if(output.equals("ok")){
                    Toast.makeText(context,"update Successfull",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context , Homepage.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(context, "Your Number could be similar to someone elses make sure you have the right number",Toast.LENGTH_LONG).show();

                }
            }
        };
        asyncHTTPPost.execute();
    }

    private void setIMG(String uri) {
        Glide.with(context).load(uri).into(imgView);
    }


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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, uploadUrl, new Response.Listener<String>() {
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
                params.put("name", user.getUserID().trim());
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
