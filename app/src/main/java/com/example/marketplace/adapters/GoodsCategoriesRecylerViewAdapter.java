package com.example.marketplace.adapters;

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

import com.example.marketplace.classes.Category;
import com.example.marketplace.R;
import com.example.marketplace.activities.goodAndService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class    GoodsCategoriesRecylerViewAdapter extends RecyclerView.Adapter<GoodsCategoriesRecylerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Category> mCategories;
    private String imgUrl = "https://lamp.ms.wits.ac.za/~s1814731/MPphpfiles/categories/";


    public GoodsCategoriesRecylerViewAdapter(Context mContext , List<Category> mCategories){
        this.mContext = mContext;
        this.mCategories = mCategories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view;
       LayoutInflater mInflater = LayoutInflater.from(mContext);
       view = mInflater.inflate(R.layout.catcardview,parent,false);

       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {

        holder.tvCatName.setText(mCategories.get(position).getTitle());
        Picasso.get().load(imgUrl.concat(mCategories.get(position).getTitle()).concat(".jpeg")).placeholder(R.drawable.catdefault)
                .error(R.drawable.catdefault)
                .into(holder.imgCat);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , goodAndService.class);
                intent.putExtra("Category",mCategories.get(position).getTitle());
                intent.putExtra("type","Goods");
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvCatName;
        ImageView imgCat;
        CardView cardView;
        public MyViewHolder(View itemView){
            super(itemView);

            tvCatName = (TextView) itemView.findViewById(R.id.tvCategoryName );
            imgCat = (ImageView) itemView.findViewById(R.id.ivCategoryImage);
            cardView = (CardView) itemView.findViewById(R.id.viewCatCard);

        }
    }
}
