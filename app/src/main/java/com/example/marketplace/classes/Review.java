package com.example.marketplace.classes;

import android.widget.RatingBar;

public class Review {

    private String Reviewer;
    private String review;
    private RatingBar ratingBar;

    Review(String Reviewer , String review ,RatingBar ratingBar){
        this.Reviewer = Reviewer;
        this.review = review;
        this.ratingBar = ratingBar;
    }

    public String getReviewer() {
        return Reviewer;
    }

    public void setReviewer(String reviewer) {
        Reviewer = reviewer;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(RatingBar ratingBar) {
        this.ratingBar = ratingBar;
    }
}
