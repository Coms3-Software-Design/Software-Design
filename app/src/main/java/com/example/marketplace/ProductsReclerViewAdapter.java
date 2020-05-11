package com.example.marketplace;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsReclerViewAdapter extends RecyclerView.Adapter<ProductsReclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Product> mProducts;

    public ProductsReclerViewAdapter(Context mContext , List<Product> mProducts){

        this.mContext = mContext;
        this.mProducts = mProducts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.prod_cardview,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvProdName.setText(mProducts.get(position).getProductName());
        //int price = Integer.toString(mProducts.get(position).getPricePerItem());
        holder.tvProdPrice.setText(String.valueOf(mProducts.get(position).getPricePerItem()));
        Picasso.get().load(mProducts.get(position).getProductPicture()).placeholder(R.drawable.tech2)
                .error(R.drawable.tech2)
                .into(holder.imgProd);

        holder.viewProdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvProdName , tvProdPrice;
        ImageView imgProd;
        CardView viewProdCard;
        public MyViewHolder(View itemView){
            super(itemView);

            tvProdName = itemView.findViewById(R.id.tvProdName);
            tvProdPrice = itemView.findViewById(R.id.tvProdPrice);
            imgProd = itemView.findViewById(R.id.ivProdImage);
            viewProdCard = itemView.findViewById(R.id.viewProdCard);

        }
    }


}
