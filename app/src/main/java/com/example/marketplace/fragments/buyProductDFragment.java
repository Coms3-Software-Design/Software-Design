package com.example.marketplace.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.marketplace.R;
import com.example.marketplace.classes.Product;
import com.example.marketplace.classes.User;
import com.example.marketplace.helperclasses.AsyncHTTPPost;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class buyProductDFragment extends DialogFragment {

    private User user;
    private Product product;
    private TextView tvOnBuy;
    private String buyURL="https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPBuy.php";
    private Context context;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.fragmaent_onbuyprod,null);

        tvOnBuy = v.findViewById(R.id.tvOnBuyProd);
        context = v.getContext();

        String title = product.getProdType().equals("Goods")?"Buy Product": "Hire Service";
        if(user.getBalance() >= product.getPricePerItem()){
            tvOnBuy.setText("Are You Sure you want to purchase the "+product.getProductName());
        }
        else{
            tvOnBuy.setText("Your balance is insufficient you cannot continue with the purchase!");
        }


        builder.setView(v).
                setTitle(title)
                .setNegativeButton("cancel",null)
                .setPositiveButton("Ok",null);

        final AlertDialog mAlertDialog = builder.create();

        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if(user.getBalance() >= product.getPricePerItem()) {
                            user.setBalance(user.getBalance() - product.getPricePerItem());
                            if(product.getProdType().equals("Goods")) {
                                product.setCurrentQuantity(product.getCurrentQuantity() - 1);
                            }
                            proceedToBuy(mAlertDialog);
                        }
                        else {
                            mAlertDialog.dismiss();
                        }
                    }



                });
            }
        });
        return  mAlertDialog;

    }

    private void proceedToBuy(final AlertDialog dialog) {
        String date = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        ContentValues cv = new ContentValues();
        cv.put("ProductID",product.getProductID());
        cv.put("ProductName",product.getProductName());
        cv.put("Buyer",user.getUserID());
        cv.put("TransDate",date);
        cv.put("Balance",user.getBalance());
        cv.put("Quantity",product.getCurrentQuantity());

        @SuppressLint("StaticFieldLeak")
        AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost(buyURL, cv) {
            @Override
            protected void onPostExecute(String output) {
                if(output.equals("1")){
                    dialog.dismiss();
                }
                else{
                    Toast.makeText(context,output,Toast.LENGTH_SHORT).show();
                    user.setBalance(user.getBalance() + product.getPricePerItem());
                    if(product.getProdType().equals("Goods")) {
                        product.setCurrentQuantity(product.getCurrentQuantity() + 1);
                    }
                }

            }
        };
        asyncHTTPPost.execute();

    }

    public void setUser(User user){
        this.user = user;
    }

    public void setProduct(Product product){
        this.product = product;
    }
}
