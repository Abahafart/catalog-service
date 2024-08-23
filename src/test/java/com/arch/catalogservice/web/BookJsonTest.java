package com.arch.catalogservice.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import com.arch.catalogservice.domain.Book;

@JsonTest
class BookJsonTest {

  @Autowired
  private JacksonTester<Book> json;

  @Test
  void testSerialize() throws IOException {
    Book book = Book.of("1234567890", "Title", "Author", new BigDecimal(19), "VIVA-BOOKS");
    JsonContent<Book> jsonContent = json.write(book);
    assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
    assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
    assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
    assertThat(jsonContent).extractingJsonPathStringValue("@.publisher").isEqualTo(book.publisher());
    assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price().intValue());
  }

  @Test
  void testDeserialize() throws IOException {
    String content =
        """
        {
        "id": "5",
          "isbn": "1234567890",
          "title": "Title",
          "author": "Author",
          "price": 12,
          "publisher": "VIVA-BOOKS",
          "version": 0
        }
    """;
    assertThat(json.parse(content))
        .usingRecursiveComparison()
        .isEqualTo(new Book(5L,"1234567890", "Title", "Author", new BigDecimal(12), "VIVA-BOOKS", null, null, 0));
  }
}
