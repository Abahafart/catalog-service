package com.arch.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class BookValidationTest {

  private static Validator validator;
  @BeforeAll
  static void setup(){
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void whenAllFieldsCorrectThenValidationSucceeds() {
    Book book = new Book("1234567890", "Title", "Author", BigDecimal.ONE);
    Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
    assertThat(constraintViolations).isEmpty();
  }

  @Test
  void whenIsbnDefinedButIncorrectThenValidationFails() {
    Book book = new Book("a234567890", "Title", "Author", BigDecimal.ONE);
    Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
    assertThat(constraintViolations).hasSize(1);
    assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");
  }
}
