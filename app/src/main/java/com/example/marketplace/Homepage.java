package com.example.marketplace;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Homepage extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        /*
         * we changed the action bar to a toolbar which is easier to work with
         * we then have to tell android studio by specifying the support action bar
         */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        // Below is the action bar toggle... i.e the 3 lines on the top left of the screen
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer , toolbar , R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState(); // this will make the toggle to rotate when we open our drawer

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
