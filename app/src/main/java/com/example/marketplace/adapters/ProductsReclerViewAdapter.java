package com.example.marketplace.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marketplace.activities.BuyProduct;
import com.example.marketplace.classes.Product;
import com.example.marketplace.R;
import com.example.marketplace.classes.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.squareup.picasso.Picasso.*;

public class ProductsReclerViewAdapter extends RecyclerView.Adapter<ProductsReclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Product> mProducts;
    private String picUrl = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/Products/";
    private User user;

    public ProductsReclerViewAdapter(Context mContext, List<Product> mProducts, User user){

        this.mContext = mContext;
        this.mProducts = mProducts;
        this.user = user;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        mProducts.get(position).setProductPicture(picUrl.concat(String.valueOf(mProducts.get(position).getProductID())).concat(".jpeg"));
        holder.tvProdName.setText(mProducts.get(position).getProductName());
        holder.tvProdPrice.setText("R"+ mProducts.get(position).getPricePerItem());



        Picasso.get().load(mProducts.get(position).getProductPicture()).placeholder(R.drawable.tech2)
                .error(R.drawable.tech2)
                .into(holder.imgProd);

//        Glide.with(mContext).load(mProducts.get(position).getProductPicture()).into(holder.imgProd);

        holder.viewProdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , BuyProduct.class);
                intent.putExtra("product",mProducts.get(position));
                intent.putExtra("user",user);
                mContext.startActivity(intent);
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
