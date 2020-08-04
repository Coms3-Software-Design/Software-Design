package com.example.marketplace.fragments;



import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.marketplace.R;
import com.example.marketplace.adapters.TransactionAdapter;
import com.example.marketplace.classes.Transaction;
import com.example.marketplace.classes.User;
import com.example.marketplace.helperclasses.AsyncHTTPPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;


public class TrasnactionsFragment extends AppCompatDialogFragment {


    private  User user;
    private ListView listView;
    private TextView histTotal, histQuant;
    private String transUrl = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/MPTransHistory.php";
    private Context context;

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_trans_hist , null);

        context = v.getContext();
        listView = v.findViewById(R.id.transHistList);
        histTotal = v.findViewById(R.id.transHistTotal);
        histQuant = v.findViewById(R.id.transHistQuant);

        builder.setView(v)
                .setTitle("Your Transaction History")
                .setPositiveButton("Okay",null);
        final AlertDialog mAlertDialog = builder.create();

        ContentValues cv = new ContentValues();
        cv.put("userName",user.getUserID());

        @SuppressLint("StaticFieldLeak")
        AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost(transUrl,cv) {

            @Override
            protected void onPostExecute(String output) {
                ArrayList<Transaction> list = new ArrayList<>();

                JSONArray array = null;
                try {
                    int total = 0;
                    array = new JSONArray(output);
                    for (int i = 0 ; i < array.length() ; i++){


                        JSONObject obj = array.getJSONObject(i);
                        //int transactionID , productID, boughtByID,ownerID,productPrice;
                        //String productName, productOwnerName,date;


                        int transactionID = Integer.parseInt((String) obj.get("TransactionID"));
                        int productPrice = Integer.parseInt((String) obj.get("Product_Price"));
                        int productID = Integer.parseInt((String) obj.get("Product_ID"));
                        int ownerID = Integer.parseInt((String) obj.get("UserID"));
                        int boughtByID = Integer.parseInt(obj.getString("Buyer"));
                        String productName = obj.getString("Product_Name");
                        String productOwnerName = obj.getString("Name");
                        String date = obj.getString("Transaction_Date");

                        total += productPrice;

                        Transaction t =  new Transaction(transactionID,productID, productName,ownerID,productOwnerName,boughtByID,date,productPrice);

                        Toast.makeText(context , t.getTransactionDate(),Toast.LENGTH_SHORT);
                        list.add(t);
                    }
                    //list.add(new Transaction(1,1,"Shoe",5,"Shameel",9,"28 July, 2020",784));
                    TransactionAdapter adapter  =  new TransactionAdapter( context , R.layout.trasctions , list);
                    listView.setAdapter(adapter);
                    histQuant.setText("" + array.length());
                    histTotal.setText("R" + total);
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println(e);
                }


            }
        };
        asyncHTTPPost.execute();


        return mAlertDialog;

    }

    public void setUser(User user){
        this.user = user;
    }
}
