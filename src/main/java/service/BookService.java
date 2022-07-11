package service;

import exception.BookException;

import java.lang.reflect.Executable;
import java.time.Duration;
import java.util.List;

import model.Book;
import model.Review;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;


public class BookService {
  private final BookInfoService bookInfoService;
  private final ReviewService reviewService;

  public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
    this.bookInfoService = bookInfoService;
    this.reviewService = reviewService;
  }

  public Flux<Book> getBooks() {
    var allBooks = bookInfoService.getBooks();
    return allBooks
        .flatMap(bookInfo -> {
          Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getId()).collectList();
          return reviews
              .map(review -> new Book(bookInfo, review));
        })
        .onErrorMap(throwable -> {
          System.err.println("Exception is :" + throwable);
          return new BookException("Eception Occurecd while fetching books!");
        })
        .log();
  }


  public Flux<Book> getBooksRetry() {
    var allBooks = bookInfoService.getBooks();
    return allBooks
        .flatMap(bookInfo -> {
          Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getId()).collectList();
          return reviews
              .map(review -> new Book(bookInfo, review));
        })
        .onErrorMap(throwable -> {
          System.err.println("Exception is :" + throwable);
          return new BookException("Eception Occurecd while fetching books!");
        })
        .retry(3)
        .log();
  }


  public Flux<Book> getBooksRetryWhen() {
//    var retrySpec = Retry.backoff(3, Duration.ofMillis(1000))
//        .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) -> {
//          return Exceptions.propagate(retrySignal.failure());
//        }))
//        .filter(throwable -> throwable instanceof BookException);
    var allBooks = bookInfoService.getBooks();
    return allBooks
        .flatMap(bookInfo -> {
          Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getId()).collectList();
          return reviews
              .map(review -> new Book(bookInfo, review));
        })
        .onErrorMap(throwable -> {
          System.err.println("Exception is :" + throwable);
          return new BookException("Eception Occurecd while fetching books!");
        })
        .retryWhen(getRetryBackoffSpec())
        .log();
  }


  private RetryBackoffSpec getRetryBackoffSpec() {
    return Retry.backoff(3, Duration.ofMillis(1000))
        .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) -> {
          return Exceptions.propagate(retrySignal.failure());
        }))
        .filter(throwable -> throwable instanceof BookException);
  }

  public Mono<Book> getBookById(int id) {
    var book = bookInfoService.getBookById(id);
    var review = reviewService
        .getReviews(id)
        .collectList();
    return book
        .zipWith(review, Book::new);
  }
}
