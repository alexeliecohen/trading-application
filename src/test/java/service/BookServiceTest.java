package service;
import static reactor.test.StepVerifier.create;

import com.tradingapplication.service.BookInfoService;
import com.tradingapplication.service.BookService;
import com.tradingapplication.service.ReviewService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

  private final BookInfoService bookInfoService
      = new BookInfoService();

  private final ReviewService reviewService
      = new ReviewService();

  private final BookService bookService =
      new BookService(bookInfoService,reviewService);

  @Test
  void getBooks() {
    var books = bookService.getBooks();
    create(books)

        .assertNext(book -> {
          assertEquals("Book one",book.getBookInfo().getTitle());
          assertEquals(1,book.getBookInfo().getId());
          assertEquals("Author one",book.getBookInfo().getAuthor());
          assertEquals("12312312",book.getBookInfo().getISBN());
          assertEquals(3, book.getReviews().size());
        })
        .assertNext(book -> {
          assertEquals("Book two",book.getBookInfo().getTitle());
          assertEquals(2,book.getBookInfo().getId());
          assertEquals("Author two",book.getBookInfo().getAuthor());
          assertEquals("432434",book.getBookInfo().getISBN());
          assertEquals(3, book.getReviews().size());
        })
        .assertNext(book -> {
          assertEquals("Book three", book.getBookInfo().getTitle());
          assertEquals(3, book.getBookInfo().getId());
          assertEquals("Author three", book.getBookInfo().getAuthor());
          assertEquals("573737478", book.getBookInfo().getISBN());
          assertEquals(3, book.getReviews().size());
        })

        .verifyComplete();


  }

  @Test
  void getBookById() {
    var book  = bookService.getBookById(1);

    create(book)
        .assertNext(b -> {
          assertEquals("Book one",b.getBookInfo().getTitle());
          assertEquals(3,b.getReviews().size());
        })
        .verifyComplete();
  }
}