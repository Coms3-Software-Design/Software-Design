package com.example.marketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView t;
        t = findViewById(R.id.textview1);
        t.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                t.setAllCaps(true);
                t.setText("Text has changed");


                Toast.makeText(MainActivity.this,"Hey man you are here",Toast.LENGTH_LONG).show();
                View l = findViewById(R.id.back);
                l.setBackground(getDrawable(R.drawable.kali));
            }
        });
    }
}
