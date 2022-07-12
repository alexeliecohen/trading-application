package com.tradingapplication.model;

public class BookInfo {
  private long id;
  private String title;
  private String author;
  private String ISBN;

  public BookInfo() {
  }

  public BookInfo(long id, String title, String author, String ISBN) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.ISBN = ISBN;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getISBN() {
    return ISBN;
  }

  public void setISBN(String ISBN) {
    this.ISBN = ISBN;
  }
}
