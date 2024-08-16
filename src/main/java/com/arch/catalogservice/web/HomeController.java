package com.arch.catalogservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arch.catalogservice.config.BookProperties;

@RestController
public class HomeController {

  private final BookProperties bookProperties;

  public HomeController(BookProperties bookProperties) {
    this.bookProperties = bookProperties;
  }

  @GetMapping("/")
  public String getGreeting() {
    return bookProperties.getGreeting();
  }
}
