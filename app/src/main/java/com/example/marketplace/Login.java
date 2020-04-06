package com.example.marketplace;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.rgb;

public class Login extends AppCompatActivity {
    EditText userName,Password;
    Button signIn;
    TextView tvForgotPass;
    int loginAttempts = 3; // made it a global variable for ease of access

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText)findViewById(R.id.etStudentNo_Login);
        Password = (EditText)findViewById(R.id.etPassword_Login);
        tvForgotPass = (TextView) findViewById(R.id.tvForgotPass);
        signIn = (Button) findViewById(R.id.btnSignIn);

        // TODO : Use shared preferences to give the user the ability to login in next session without typing in their credentials
        // TODO : Create The forgot password so that a user can reset their password


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrname = userName.getText().toString();
                String password = Password.getText().toString();


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


        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgotPassword.class));
            }
        });
    }

    public void Sign_In(final Context c , ContentValues cv){

            @SuppressLint("StaticFieldLeak") AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPLogin.php" , cv) {

            @Override
            public void onPostExecute(String output) {

                if(output.equals("exists")){
                    Toast.makeText(Login.this , "Sign in succefull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(c , Homepage.class));
                    finish();
                }
                else {
                        loginAttempts--;
                        Toast.makeText(Login.this , "Sign in failed please try again, you have "+loginAttempts+ " more attempts", Toast.LENGTH_SHORT).show();
                        if(loginAttempts == 0){
                            signIn.setBackgroundColor(0);
                            signIn.setClickable(false);
                            Toast.makeText(c,"You can no longer sign in during this session, please reset password or try logging in late",Toast.LENGTH_LONG).show();
                        }
                }

            }
        };

        asyncHTTPPost.execute();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Sign_Up_Login(View view){
        TextView textView  = (TextView)findViewById(R.id.register_ID);
        startActivity(new Intent(Login.this, Register.class));
    }


}
