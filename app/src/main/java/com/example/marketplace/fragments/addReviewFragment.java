package com.example.marketplace.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.marketplace.R;
import com.example.marketplace.classes.Product;

public class addReviewFragment extends AppCompatDialogFragment {

    Product product;



    public void setProduct(Product product){
        this.product = product;
    }


    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState){

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.fragment_add_review,null);






        builder.setView(v)
                .setTitle("Write A review")
                .setNegativeButton("cancel",null)
                .setPositiveButton("OK",null);

        final AlertDialog mAlertDialog = builder.create();

        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                    }
                });
            }
        });
        return  mAlertDialog;

    }
}


