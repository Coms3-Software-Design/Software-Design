package com.example.marketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    TextView Register;
    Button Login;
    Animation frombottom,fromtop,fromside;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Register = findViewById(R.id.tvSplashSignup);
        Login = findViewById(R.id.btnSplashLogin);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromside = AnimationUtils.loadAnimation(this,R.anim.fromside);

        fromtop.setDuration(3000);
        frombottom.setDuration(2000);
        fromside.setDuration(2500);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(SplashScreen.this, Login.class);
                startActivity(login);
                finish();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(SplashScreen.this, Register.class);
                startActivity(login);
            }
        });
    }
}
