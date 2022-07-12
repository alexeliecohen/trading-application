package service;

import com.tradingapplication.exception.BookException;
import com.tradingapplication.service.BookInfoService;
import com.tradingapplication.service.BookService;
import com.tradingapplication.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class BookServiceMockTest {

  @InjectMocks
  private BookService bookService;

  @Mock
  private BookInfoService bookInfoService;

  @Mock
  private ReviewService reviewService;

  @Test
  void getBooksMock() {
    Mockito.when(bookInfoService.getBooks())
        .thenCallRealMethod();

    Mockito.when(reviewService.getReviews(Mockito.anyLong()))
        .thenCallRealMethod();

    var books = bookService.getBooks();

    StepVerifier.create(books)
        .expectNextCount(3)
        .verifyComplete();



  }
  @Test
  void getBooksMockOnError() {
    System.out.println("err");
    Mockito.when(bookInfoService.getBooks())
        .thenCallRealMethod();

    Mockito.when(reviewService.getReviews(Mockito.anyLong()))
        .thenThrow(new IllegalStateException("exception using test"));

    var books = bookService.getBooks();

    StepVerifier.create(books)
        .expectError(BookException.class)
        .verify();
  }
  @Test
  void getBooksMockOnErrorRetry() {
    Mockito.when(bookInfoService.getBooks())
        .thenCallRealMethod();

    Mockito.when(reviewService.getReviews(Mockito.anyLong()))
        .thenThrow(new IllegalStateException("exception using test"));

    var books = bookService.getBooksRetry();

    StepVerifier.create(books)
        .expectError(BookException.class)
        .verify();
  }
  @Test
  void getBooksMockOnErrorRetryWhen() {
    Mockito.when(bookInfoService.getBooks())
        .thenCallRealMethod();

    Mockito.when(reviewService.getReviews(Mockito.anyLong()))
        .thenThrow(new IllegalStateException("exception using test"));

    var books = bookService.getBooksRetryWhen();

    StepVerifier.create(books)
        .expectError(BookException.class)
        .verify();
  }



}