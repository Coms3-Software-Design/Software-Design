package com.example.marketplace.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.marketplace.R;
import com.example.marketplace.classes.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    private Context mContext;
    private int resource;

    public TransactionAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Transaction> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String Name , Owner , Date;
        int Price;
        Name = getItem(position).getProductName();
        Owner = getItem(position).getProductOwnerName();
        Date = getItem(position).getTransactionDate();
        Price = getItem(position).getProductPrice();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(resource , parent,false);

        TextView pName =  convertView.findViewById(R.id.transHistName);
        TextView pOwner = convertView.findViewById(R.id.transHistOwner);
        TextView tDate = convertView.findViewById(R.id.transHistDate);
        TextView pPrice = convertView.findViewById(R.id.transHistPrice);

        pName.setText(Name);
        pOwner.setText(Owner);
        tDate.setText(Date);
        pPrice.setText("R"+Price);

        return convertView;
    }
}
