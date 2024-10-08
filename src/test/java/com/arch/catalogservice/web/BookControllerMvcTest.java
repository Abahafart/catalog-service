package com.arch.catalogservice.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.arch.catalogservice.domain.BookNotFoundException;
import com.arch.catalogservice.domain.BookService;

@WebMvcTest(BookController.class)
class BookControllerMvcTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService bookService;

  @Test
  void whenGetBookNotExistingThenShouldReturn404() throws Exception {
    String isbn = "1234567890";
    given(bookService.viewBookDetails(isbn))
        .willThrow(BookNotFoundException.class);
    mockMvc
        .perform(get("/books/"+isbn))
        .andExpect(status().isNotFound());
  }
}
