package com.example.marketplace.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.marketplace.R;
import com.example.marketplace.classes.User;
import com.example.marketplace.fragments.GoodsFragment;
import com.example.marketplace.fragments.ProfileUpdateFragment;
import com.example.marketplace.fragments.ServicesFragment;
import com.example.marketplace.helperclasses.AsyncHTTPPost;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawer;

    private User user;
    private TextView tvUserName , tvBalance;
    private ImageView prPic;
    private NavigationView navigationView;
    private ProfileUpdateFragment profile;

    private Toolbar toolbar;
    private String imgURLPrefix = "http://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/uploads/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        user = getIntent().getParcelableExtra("user");
        Initialise(user);




        /*
         * we changed the action bar to a toolbar which is easier to work with
         * we then have to tell android studio by specifying the support action bar
         */
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
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
            Bundle args = new Bundle();
            args.putParcelable("user",user);
            GoodsFragment goodsFragment;
            // ServicesFragment servicesFragment;
            goodsFragment = new GoodsFragment();


            goodsFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, goodsFragment).commit();
            navigationView.setCheckedItem(R.id.nav_goods);
        }


    }

    private void setIMG(String uri) {

        Picasso.get().load(uri).placeholder(R.drawable.ic_edit_profile)
                .error(R.drawable.ic_edit_profile).into(prPic);

    }



    /*
     * Below we handle the onlick listeners for each navigation view clicked
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Bundle args = new Bundle();
        args.putParcelable("user",user);
        GoodsFragment goodsFragment;
        goodsFragment = new GoodsFragment();
        goodsFragment.setArguments(args);

        ServicesFragment servicesFragment = new ServicesFragment();
        servicesFragment.setArguments(args);
        switch (item.getItemId())
        {
            case R.id.nav_goods:



                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,goodsFragment).commit();
                break;
            case R.id.nav_services:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,servicesFragment).commit();
                break;
            case R.id.nav_editProfile:
                editProfile(user);
                Initialise(user);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileUpdateFragment()).commit();
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

    public void setUser(final User s){
        tvUserName = findViewById(R.id.tvUse);
        tvBalance = findViewById(R.id.tvBal);
        prPic = findViewById(R.id.ivpic);

        tvUserName.setText(user.getUserName());
        tvBalance.setText("Balance: R"+s.getBalance());
        setIMG(imgURLPrefix.concat(user.getUserID()).concat(".jpg").concat("?=" + System.currentTimeMillis()));


    }

    public void editProfile(User u){

        profile = new ProfileUpdateFragment();
        profile.setUser(u);
        profile.show(getSupportFragmentManager(),"Profile Edit");
    }


    public void Initialise( final User user){

        ContentValues cv = new ContentValues();
        cv.put("username",user.getUserName());
        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPUser.php" , cv) {

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
                    String Profile_Pic =  userJO.getString("Profile_pic");



                    setUser(new User(userID,Name,Surname,UserName,Password,ContactNum, D_O_B,Date_Created,Gender,Bio,Balance,Profile_Pic));

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
            startActivity(new Intent(this, Login.class));
            finish();
            super.onBackPressed();
        }
    }



}
