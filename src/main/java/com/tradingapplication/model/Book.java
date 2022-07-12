package com.tradingapplication.model;

import java.util.List;

public class Book {
  private BookInfo bookInfo;
  private List<Review> reviews;

  public Book() {
  }

  public Book(BookInfo bookInfo, List<Review> reviews) {
    this.bookInfo = bookInfo;
    this.reviews = reviews;
  }

  public BookInfo getBookInfo() {
    return bookInfo;
  }

  public void setBookInfo(BookInfo bookInfo) {
    this.bookInfo = bookInfo;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }
}
