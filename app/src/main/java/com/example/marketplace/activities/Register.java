package com.example.marketplace.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marketplace.R;
import com.example.marketplace.classes.User;
import com.example.marketplace.helperclasses.AsyncHTTPPost;
import com.example.marketplace.helperclasses.DateOfBirthPicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Register extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button btnRegister;
    private EditText UserID,Name,Last_Name,Password,PhoneNumber, userName;
    private RadioGroup radioGroup;
    private RadioButton checkedRadioButton = null; // we will assign this to a checked radio button but initially it's null
    private TextView DOBtext;
    private Calendar dateOB = Calendar.getInstance();
    private  Date dateOfBirth , dateCreated;
    private String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        UserID = findViewById(R.id.etUserID);
        Name = findViewById(R.id.etFname);
        Last_Name = findViewById(R.id.etLname);
        Password = findViewById(R.id.etPassword);
        PhoneNumber = findViewById(R.id.etPhone);
        userName = findViewById(R.id.etUserName);
        DOBtext = findViewById(R.id.tvDOB);
        btnRegister = findViewById(R.id.btnRegister);
        radioGroup = findViewById(R.id.radioGroup);
        dateCreated = dateOB.getTime(); // this sets the current date before we change it to the date of birth
        dateOfBirth = dateOB.getTime(); // The current date will be the default DOB if the user doesn't enter their date of birth

        /*
         * The onclick listener below is for a text
         * Which upon being clicked it brings up a date to be picked which is your date of birth
         * After choosing your the app shows a toast of your selected date of birth
         */


        DOBtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dCreated = new DateOfBirthPicker();
                dCreated.show(getSupportFragmentManager(),"Pick Date Of Birth");
            }
        });




        /*
         * btnRegister is the green button at the end of the page.
         * The onlick listner below validates the input and checks if every input needed is supplied
         */

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userID = UserID.getText().toString();
                String name = Name.getText().toString();
                String lname = Last_Name.getText().toString();
                String uName = userName.getText().toString();
                String pass = Password.getText().toString() ;
                String ContactNum = PhoneNumber.getText().toString();



                /*
                 * All these if statements check if we have valid input
                 */

                if(uName.equals("")){
                    userName.setError("Enter Your Username");
                    return;
                }

                if(name.equals("")){
                    Name.setError("Enter Your Name");
                    return;
                }

                if(lname.equals("")){
                    Last_Name.setError("Enter Your Last name");
                    return;
                }

                if(ContactNum.equals("") || ContactNum.length() != 10){
                    PhoneNumber.setError("Your ContactNum number should be 10 digits");
                    return;
                }

                if(userID.equals("")){
                    UserID.setError("Enter Your Email");
                    return;
                }

                if(pass.equals("")){
                    Password.setError("Enter Your Password");
                    return;
                }

                checkSelectedRadioButton(); // Gender Check

                // Below we create our current user object which is passed to the register method to go get registered
                User newUser = new User(userID, name , lname , uName , pass , ContactNum , DateFormat.getDateInstance().format(dateOfBirth.getTime()) , DateFormat.getDateInstance().format(dateCreated.getTime()),gender,null , 0,null);


                Register(Register.this , newUser);
            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateOB.set(Calendar.YEAR,year);
        dateOB.set(Calendar.MONTH, month);
        dateOB.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        dateOfBirth = dateOB.getTime();
        Toast.makeText(this ,"Your DOB is:  "+ DateFormat.getDateInstance().format(dateOB.getTime()),Toast.LENGTH_LONG).show();
    }

    public void checkSelectedRadioButton(){
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        checkedRadioButton = findViewById(radioButtonID);
        if(checkedRadioButton.getText() == "Female") gender = "FeMale";
        else gender = "Male";

    }

    private void Register(final Context c, User newUser){

        ContentValues cv = new ContentValues();
        cv.put("userid",newUser.getUserID());
        cv.put("name",newUser.getName());
        cv.put("surname" , newUser.getSurname());
        cv.put("username" , newUser.getUserName());
        cv.put("password" , newUser.getPassword());
        cv.put("contactnum" , newUser.getContactDetails());
        cv.put("dob", newUser.getDateOfBirth().trim());
        cv.put("datecreated" , newUser.getDateCreated().trim());
        cv.put("gender",newUser.getGender());


        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPRegister.php", cv) {

            @Override
            protected void onPostExecute(String output) {

                if(output.equals("1")){
                    Intent intent = new Intent();
                    Toast.makeText(c, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this  , Login.class));
                    Register.this.finish();

                } else
                    Toast.makeText(c, output+ " Registration Failed", Toast.LENGTH_SHORT).show();
            }
        };
        asyncHTTPPost.execute();
    }

}
