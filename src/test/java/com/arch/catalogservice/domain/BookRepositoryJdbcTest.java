package com.arch.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.arch.catalogservice.BaseIT;
import com.arch.catalogservice.config.DataConfig;
import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class BookRepositoryJdbcTest {

  @Autowired
  private BookRepository bookRepository;

  @BeforeEach
  void setUp() {
    bookRepository.deleteAll();
  }

  @Test
  void findBookByIsbnWhenExisting() {
    String isbn = "1234567890";
    Book book = Book.of(isbn, "title", "author", BigDecimal.valueOf(23), "VIVA-BOOKS");
    bookRepository.save(book);
    Optional<Book> byIsbn = bookRepository.findByIsbn(isbn);
    assertThat(byIsbn).isPresent();
    assertThat(byIsbn.get().isbn()).isEqualTo(isbn);
  }
}
