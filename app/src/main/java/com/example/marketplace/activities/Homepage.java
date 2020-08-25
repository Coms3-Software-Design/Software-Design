package com.example.marketplace.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.marketplace.R;
import com.example.marketplace.classes.User;
import com.example.marketplace.fragments.AddProductFragment;
import com.example.marketplace.fragments.GoodsFragment;
import com.example.marketplace.fragments.ProfileUpdateFragment;
import com.example.marketplace.fragments.ServicesFragment;
import com.example.marketplace.fragments.TrasnactionsFragment;
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
    private TrasnactionsFragment transaction;
    private AddProductFragment addProd;
    private Context context;
    private Toolbar toolbar;
    public  Menu menu;
    public View headerView;
    private String imgURLPrefix = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/uploads/";
    private String resetUserURL = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPReturnUser.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

         user = getIntent().getParcelableExtra("user");
         drawer = findViewById(R.id.drawer_layout);
         context = this;

        /*
         * we changed the action bar to a toolbar which is easier to work with
         * we then have to tell android studio by specifying the support action bar
         */
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("Balance: R"+user.getBalance());

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerView = navigationView.getHeaderView(0);
        menu = navigationView.getMenu();


        prPic = headerView.findViewById(R.id.ivpic);
        tvUserName = headerView.findViewById(R.id.tvUse);
        tvBalance = headerView.findViewById(R.id.tvBal);

        tvUserName.setText(user.getUserName());
        tvBalance.setText("Balance: R"+user.getBalance());
        setIMG(imgURLPrefix.concat(user.getUserID()).concat(".jpg").concat("?=" + System.currentTimeMillis()));


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

        Glide.with(context).load(uri).into(prPic);

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
                break;

            case R.id.nav_transactionHistory:
                viewTrasHis(user);
                break;

            case R.id.nav_creatItem:
              //  Toast.makeText(this , "Create Item popup still need implementation",Toast.LENGTH_LONG).show();
                addProduct(user);
                break;

            case R.id.nav_signOut:
                startActivity(new Intent(this, Login.class));
                finish();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void viewTrasHis(User user) {
        transaction = new TrasnactionsFragment();
        transaction.setUser(user);
        transaction.show(getSupportFragmentManager(),"Your Transaction History");

    }

    public void editProfile(User u){

        profile = new ProfileUpdateFragment();
        profile.setUser(u);
        profile.show(getSupportFragmentManager(),"Profile Edit");
    }

    public void addProduct(User u){
        addProd = new AddProductFragment();
        addProd.setUser(u);
        addProd.show(getSupportFragmentManager() , "Add Prodcut");

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
