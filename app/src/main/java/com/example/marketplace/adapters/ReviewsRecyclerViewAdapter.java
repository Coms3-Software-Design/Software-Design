package com.example.marketplace.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.R;
import com.example.marketplace.classes.Review;

import java.util.List;

public class ReviewsRecyclerViewAdapter extends RecyclerView.Adapter<ReviewsRecyclerViewAdapter.myViewHolder> {

    Context context;
    List<Review> reviewList;

    ReviewsRecyclerViewAdapter(Context context , List<Review> reviewList){
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.review_card,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.name.setText(reviewList.get(position).getReviewer());


    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView name , review;
        RatingBar ratingBar;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvReviewer);
            review = itemView.findViewById(R.id.tvViewReviewReview);
            ratingBar = itemView.findViewById(R.id.rbRevieRating);

        }
    }
}
