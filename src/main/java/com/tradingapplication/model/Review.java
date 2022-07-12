package com.tradingapplication.model;

public class Review {
  private long reviewId;
  private long bookId;
  private double ratings;
  private String review;

  public Review() {
  }

  public Review(long reviewId, long bookId, double ratings, String review) {
    this.reviewId = reviewId;
    this.bookId = bookId;
    this.ratings = ratings;
    this.review = review;
  }

  public long getReviewId() {
    return reviewId;
  }

  public void setReviewId(long reviewId) {
    this.reviewId = reviewId;
  }

  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }

  public double getRatings() {
    return ratings;
  }

  public void setRatings(double ratings) {
    this.ratings = ratings;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }
}
