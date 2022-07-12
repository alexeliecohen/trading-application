package com.tradingapplication.service;

import com.tradingapplication.model.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {
  public Flux<BookInfo> getBooks() {
    var books = List.of(
        new BookInfo(1, "Book one", "Author one", "12312312"),
        new BookInfo(2, "Book two", "Author two", "432434"),
        new BookInfo(3, "Book three", "Author three", "∂")
    );
    return Flux.fromIterable(books);
  }

  public Mono<BookInfo> getBookById(long bookId) {
    var book = new BookInfo(bookId,"Book one", "Author one", "12312312");
    return Mono.just(book);
  }
}
