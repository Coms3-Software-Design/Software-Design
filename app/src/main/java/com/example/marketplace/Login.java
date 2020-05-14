package com.example.marketplace;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Blob;
import java.util.Date;

import static android.graphics.Color.rgb;

public class Login extends AppCompatActivity {
    private EditText userName,Password;
    private Button signIn ;
    private TextView tvForgotPass, signUP;
    private String password;

    int loginAttempts = 3; // made it a global variable for ease of access

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText)findViewById(R.id.etStudentNo_Login);
        Password = (EditText)findViewById(R.id.etPassword_Login);
        tvForgotPass = (TextView) findViewById(R.id.tvForgotPass);
        signIn = (Button) findViewById(R.id.btnSignIn);
        signUP = (TextView) findViewById(R.id.tvSignUp);

        // TODO : Use shared preferences to give the user the ability to login in next session without typing in their credentials
        // TODO : Create The forgot password so that a user can reset their password


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(Login.this, Homepage.class));

                String usrname = userName.getText().toString();
                password = Password.getText().toString();

                // The 2 below if statements independant of each other only check for errors from input

                if(usrname.isEmpty()){
                    userName.setError("Please Enter Your Student Number");
                    return;
                }

                if(password.isEmpty()){
                    Password.setError("Please Enter Your Password");
                    return;
                }


                ContentValues cv = new ContentValues();
                cv.put("username",usrname);
                cv.put("password",password);

                Sign_In(Login.this , cv);
            }
        });

        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });


        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgotPassword.class));
            }
        });
    }

    private void Sign_In(final Context c , ContentValues cv){

            @SuppressLint("StaticFieldLeak") AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPLogin.php" , cv) {

            @Override
            public void onPostExecute(String output) {

                if(output.equals("!exists")){
                    loginAttempts--;
                    Toast.makeText(Login.this , "Sign in failed please try again, you have "+loginAttempts+ " more attempts", Toast.LENGTH_SHORT).show();
                    if(loginAttempts == 0){
                        signIn.setBackgroundColor(0);
                        signIn.setClickable(false);
                        Toast.makeText(c,"You can no longer sign in during this session, please reset password or try logging in late",Toast.LENGTH_LONG).show();
                    }
                }
                else {


                   // [{"UserID":"1596357","Name":"shameel nkosi","Surname":"nkosi","UserName":"G","ContactNum":"2255889966","Balance":"0","Bio":null,"D_O_B":"06 Apr 2020","Date_Created":"06 Apr 2020","Gender":"Male","Profile_pic":null}]
                    try {
                        // Only userID and balance is an integer


                        final JSONObject userJO = new JSONArray(output).getJSONObject(0); // JO in userJO for JSONObject
                        String userID =Integer.toString( userJO.getInt("UserID"));
                        int Balance = userJO.getInt("Balance");
                        String Name = userJO.getString("Name");
                        String Surname = userJO.getString("Surname");
                        String UserName = userJO.getString("UserName");
                        //String Password = userJO.getString("Password");
                        String Password = password;
                        String ContactNum = userJO.getString("ContactNum");
                        String Bio =  userJO.getString("Bio");
                        String D_O_B = userJO.getString("D_O_B");
                        String Date_Created = userJO.getString("Date_Created");
                        String Gender = userJO.getString("Gender");
                        String Profile_Pic =  userJO.getString("Profile_pic");


                        User user = new User(userID,Name,Surname,UserName,Password,ContactNum, D_O_B,Date_Created,Gender,Bio,Balance,Profile_Pic);

                        Toast.makeText(Login.this , "Sign as "+ userJO.getString("Name"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(c , Homepage.class);
                       intent.putExtra("user",user);
                        startActivity(intent);
                        finish();

                    }catch (JSONException e){
                        e.printStackTrace();
                    }


                }

            }
        };
        asyncHTTPPost.execute();
    }
}
