package com.arch.catalogservice.demo;

import java.math.BigDecimal;

import java.util.List;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.arch.catalogservice.domain.Book;
import com.arch.catalogservice.domain.BookRepository;

@Component
@Profile("testdata")
public class BookDataLoader {

  private final BookRepository bookRepository;

  public BookDataLoader(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void loadTestData() {
    bookRepository.deleteAll();
    Book book1 =
        Book.of("1234567891", "Northern Lights", "Lyra Silverstar", BigDecimal.valueOf(9.90));
    Book book2 = Book.of("1234567892", "Polar Journey",
        "Iorek Polarson", BigDecimal.valueOf(12.90));
    bookRepository.saveAll(List.of(book1, book2));
  }
}
