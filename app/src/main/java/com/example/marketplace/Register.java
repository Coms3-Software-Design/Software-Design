package com.example.marketplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.IpSecManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Register extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button btnRegister;
    EditText UserID,Name,Last_Name,Password,PhoneNumber, userName;
    RadioGroup radioGroup;
    RadioButton checkedRadioButton = null; // we will assign this to a checked radio button but initially it's null
    TextView DOBtext;
    Calendar dateOB = Calendar.getInstance();
    Date dateOfBirth , dateCreated;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        UserID = (EditText)findViewById(R.id.etUserID);
        Name = (EditText)findViewById(R.id.etFname);
        Last_Name = (EditText)findViewById(R.id.etLname);
        Password = (EditText)findViewById(R.id.etPassword);
        PhoneNumber = (EditText)findViewById(R.id.etPhone);
        userName = (EditText) findViewById(R.id.etUserName);
        DOBtext = (TextView) findViewById(R.id.tvDOB);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        dateCreated = dateOB.getTime(); // this sets the current date before we change it to the date of birth



        /*
         * The onclick listener below is for a text
         * Which upon being clicked it brings up a date to be picked which is your date of birth
         * After choosing your the app shows a toast of your selected date of birth
         */
        checkSelectedRadioButton();

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
                checkSelectedRadioButton();
                User newUser = new User(Integer.parseInt(userID), name , lname , uName , pass , Integer.parseInt(ContactNum) , dateOfBirth , dateCreated,gender,null , 0);
                Register(Register.this , newUser);
//                Toast.makeText(Register.this,"Registration Sucessful On Demo Mode", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(Register.this  , Login.class));
//                finish();

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
        checkedRadioButton = (RadioButton) findViewById(radioButtonID);
        gender = (checkedRadioButton.getText() == "Male")? "Male":"Female";

    }

    private void Register(final Context c, final User newUser){


//        $userID = mysqli_real_escape_string($link,$_REQUEST["userid"]);
//        $name = mysqli_real_escape_string($link,$_REQUEST["name"]);
//        $surname = mysqli_real_escape_string($link,$_REQUEST["surname"]);
//        $userName = mysqli_real_escape_string($link,$_REQUEST["username"]);
//        $pass = mysqli_real_escape_string($link,$_REQUEST["password"]);
//        $contactNum = mysqli_real_escape_string($link,$_REQUEST["contactnum"]);
//        $D_O_B = mysqli_real_escape_string($link,$_REQUEST["dob"]);
//        $dc = mysqli_real_escape_string($link,$_REQUEST["datecreated"]);
//        $gender = mysqli_real_escape_string($link,$_REQUEST["gender"]);

        ContentValues cv = new ContentValues();
        cv.put("userid",newUser.getUserID());
        cv.put("name",newUser.getName());
        cv.put("surname" , newUser.getSurname());
        cv.put("username" , newUser.getUserName());
        cv.put("password" , newUser.getPassword());
        cv.put("contactnum" , newUser.getContactDetails());
        cv.put("dob",newUser.getDateOfBirth().toString());
        cv.put("datecreated" , newUser.getDateCreated().toString());
        cv.put("gender",newUser.getGender());

        System.out.println(newUser.getUserID() + newUser.getName()+newUser.getSurname()+newUser.getPassword()+newUser.getContactDetails()+newUser.getDateOfBirth().toString()+newUser.getDateCreated().toString()+newUser.getGender());

        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MpRegister.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                if(output.equals("1")){
                    Intent intent = new Intent();
                    Toast.makeText(c, "Registration successful", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Register.this  , Login.class));
                    Register.this.finish();

                } else
                    System.out.println(output);
                    Toast.makeText(c, "Registration Failed", Toast.LENGTH_SHORT).show();


            }
        };
        asyncHTTPPost.execute();




    }

}
