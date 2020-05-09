package com.example.marketplace;


import android.view.View;
import static org.junit.Assert.*;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SplashScreenTest{

    @Rule
    public ActivityTestRule<SplashScreen> splashScreenActivityTestRule = new ActivityTestRule<SplashScreen>(SplashScreen.class);

    private SplashScreen sActivity = null;

    @Before
    public void setUp() throws Exception {
        sActivity = splashScreenActivityTestRule.getActivity();
    }

    @Test
    public void onCreate(){
        View view = sActivity.findViewById(R.id.loSplash);
        View view1 = sActivity.findViewById(R.id.tvCopyRight);
        assertNotNull(view);
        assertNotNull(view1);
    }

    @After
    public void tearDown() throws Exception {
        sActivity = null;
    }
}