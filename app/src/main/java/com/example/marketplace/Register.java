package com.example.marketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText Student_No,Name,Last_Name,Password,PhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void Sign_up_reg(View view){
        Student_No = (EditText)findViewById(R.id.etStdNo_Reg);
        Name = (EditText)findViewById(R.id.etFname_Reg);
        Last_Name = (EditText)findViewById(R.id.etLname_Reg);
        Password = (EditText)findViewById(R.id.etPassword_Reg);
        PhoneNumber = (EditText)findViewById(R.id.etPhone_Reg);

        String std_No = Student_No.getText().toString();
        String name = Name.getText().toString();
        String lname = Last_Name.getText().toString();
        String pass = Password.getText().toString() ;
        String phone = PhoneNumber.getText().toString();

        if(name.equals("")){
            Name.setError("Enter Your Name");
            return;
        }

        if(lname.equals("")){
            Last_Name.setError("Enter Your Last name");
            return;
        }

        if(phone.equals("")){
            PhoneNumber.setError("Enter Your Phone Number");
            return;
        }

        if(std_No.equals("")){
            Student_No.setError("Enter Your Email");
            return;
        }

        if(pass.equals("")){
            Password.setError("Enter Your Password");
            return;
        }



        Toast.makeText(Register.this,"Registration Sucessful On Demo Mode", Toast.LENGTH_LONG).show();
        startActivity(new Intent(Register.this  , Login.class));
        finish();

    }
}
