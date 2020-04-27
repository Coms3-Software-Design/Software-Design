package com.example.marketplace.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.marketplace.R;

public class ProfileUpdateFragment extends Fragment {

    private Button update;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_profile_update,container,false);
        update = v.findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
              //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new GoodsFragment()).commit();
                Toast.makeText(v.getContext(), "updated", Toast.LENGTH_LONG).show();
            }


        });
        return v;
    }

}
