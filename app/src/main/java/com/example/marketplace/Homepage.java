package com.example.marketplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.marketplace.fragments.GoodsFragment;
import com.example.marketplace.fragments.ProfileUpdateFragment;
import com.example.marketplace.fragments.ServicesFragment;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private String usern;
    User user;
    TextView tvUserName , tvBalance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        drawer = findViewById(R.id.drawer_layout);

        usern = getIntent().getStringExtra("userName");
        selfuser(usern);


        /*
         * we changed the action bar to a toolbar which is easier to work with
         * we then have to tell android studio by specifying the support action bar
         */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Below is the action bar toggle... i.e the 3 lines on the top left of the screen
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer , toolbar , R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState(); // this will make the toggle to rotate when we open our drawer


        /*
         * below we start activity with the Goods fragment open
         * We also start the activity with the Goods selected from navigation drawer
         */
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GoodsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_goods);
        }

    }


    /*
     * Below we handle the onlick listeners for each navigation view clicked
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_goods:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new GoodsFragment()).commit();
                break;
            case R.id.nav_services:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ServicesFragment()).commit();
                break;
            case R.id.nav_editProfile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileUpdateFragment()).commit();
                break;
            case R.id.nav_transactionHistory:
                Toast.makeText(this, "Transaction History still need implementation",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_creatItem:
                Toast.makeText(this , "Create Item popup still need implementation",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_signOut:
                startActivity(new Intent(this, Login.class));
                finish();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setUser(User s){
        user = s;
        tvUserName = findViewById(R.id.tvUserName);
        tvBalance = findViewById(R.id.tvBalance);
        tvUserName.setText(s.getUserName());
        tvBalance.setText("Balance: R"+s.getBalance());

       // Toast.makeText(this,s.getSurname(),Toast.LENGTH_SHORT).show();
    }

    public void selfuser(String userNam){

        ContentValues cv = new ContentValues();
        cv.put("username",userNam);
        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPUser.php" , cv) {

            @Override
            public void onPostExecute(String output) {
                    // [{"UserID":"1596357","Name":"shameel nkosi","Surname":"nkosi","UserName":"G","ContactNum":"2255889966","Balance":"0","Bio":null,"D_O_B":"06 Apr 2020","Date_Created":"06 Apr 2020","Gender":"Male","Profile_pic":null}]
                    try {
                        // Only userID and balance is an integer

                        final JSONObject userJO = new JSONArray(output).getJSONObject(0); // JO in userJO for JSONObject
                        String userID =Integer.toString( userJO.getInt("UserID"));
                        int Balance = userJO.getInt("Balance");
                        String Name = userJO.getString("Name");
                        String Surname = userJO.getString("Surname");
                        String UserName = userJO.getString("UserName");
                        String Password = userJO.getString("Password");
                        String ContactNum = userJO.getString("ContactNum");
                        String Bio =  userJO.getString("Bio");
                        String D_O_B = userJO.getString("D_O_B");
                        String Date_Created = userJO.getString("Date_Created");
                        String Gender = userJO.getString("Gender");
                        String Profile_Pic = userJO.getString("Profile_pic");


                        setUser(new User(userID,Name,Surname,UserName,Password,ContactNum, D_O_B,Date_Created,Gender, Bio,Balance,Profile_Pic));
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
            }
        };

        asyncHTTPPost.execute();


    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){

            /*
             * Here start means Left...
             * if the drawer was on the right we would have passed the argument END
             */

            drawer.closeDrawer(GravityCompat.START);

        }
        else {

            // normal back press action
            super.onBackPressed();
        }
    }
}
