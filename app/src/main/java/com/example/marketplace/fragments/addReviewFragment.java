package com.example.marketplace.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.marketplace.R;
import com.example.marketplace.classes.Product;

public class addReviewFragment extends AppCompatDialogFragment {

    Product product;
    private RatingBar ratingBar;
    private EditText review;
    private Context context;


    public void setProduct(Product product){
        this.product = product;
    }


    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState){

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.fragment_add_review,null);

        ratingBar = v.findViewById(R.id.rbRateProd);
        review = v.findViewById(R.id.etWriteReview);
        context = v.getContext();


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(context, "Rating:"+ratingBar.getRating(),Toast.LENGTH_SHORT).show();
            }
        });



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

                        rateAndReview();

                    }


                });
            }
        });
        return  mAlertDialog;

    }

    private void rateAndReview() {
        if(review.getText().toString().trim().isEmpty()){
            review.setError("Please write your review.");
            return;
        }

        double rating = ratingBar.getRating();
        String Review = review.getText().toString().trim();
        int id = product.getProductID();
        ContentValues cv = new ContentValues();

        cv.put("Rating", rating);
        cv.put("Review",Review);
        cv.put("ProductID",id);






    }
}


