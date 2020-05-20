package com.example.marketplace.classes;

import android.widget.RatingBar;

public class Review {

    private String Reviewer;
    private String review;
    private float rating;

    public Review(String Reviewer, String review, float rate){
        this.Reviewer = Reviewer;
        this.review = review;
//        this.ratingBar.setNumStars(5);
        this.rating = rate;
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



    public float getRatings(){
        return rating;
    }


}
