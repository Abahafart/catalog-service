package com.arch.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.arch.catalogservice.domain.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {

  @Autowired
  private WebTestClient webClient;

  @Test
  void whenPostRequestThenBookCreated() {
    Book bookExpected = Book.of("1234567890", "Title", "Author", BigDecimal.TEN, "VIVA-BOOKS");

    webClient.post()
        .uri("/books")
        .bodyValue(bookExpected)
        .exchange()
        .expectStatus().isCreated()
        .expectBody(Book.class).value(actualBook -> {
          assertThat(actualBook).isNotNull();
          assertThat(actualBook.isbn()).isEqualTo(bookExpected.isbn());
        });
  }
}
