package com.example.marketplace;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.rgb;
import static android.graphics.Color.toArgb;

public class Login extends AppCompatActivity {
    EditText Student_No,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Sign_Up_Login(View view){
        TextView textView  = (TextView)findViewById(R.id.register_ID);
        startActivity(new Intent(Login.this, Register.class));
    }

    public void Sign_In(View view){
        Student_No = (EditText)findViewById(R.id.etStudentNo_Login);
        Password = (EditText)findViewById(R.id.etPassword_Login);

        String std_no = Student_No.getText().toString();
        String password = Password.getText().toString();

        if(std_no.isEmpty()){
            Student_No.setError("Please Enter Your Student Number");
            return;
        }

        if(password.isEmpty()){
            Password.setError("Please Enter Your Password");
            return;
        }
        Toast.makeText(Login.this,"Signing in Sucessful On Demo Mode", Toast.LENGTH_LONG).show();
        startActivity(new Intent(Login.this,Homepage.class));
        finish();
    }
}
