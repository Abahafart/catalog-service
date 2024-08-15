package com.arch.catalogservice.domain;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> viewBookList() {
    return bookRepository.findAll();
  }

  public Book viewBookDetails(String isbn) {
    return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
  }

  public Book addBookToCatalog(Book book) {
    if (bookRepository.existsByIsbn(book.isbn())) {
      throw new BookAlreadyExistsException(book.isbn());
    }
    return bookRepository.save(book);
  }

  public void removeBookFromCatalog(String isbn) {
    bookRepository.deleteByIsbn(isbn);
  }

  public Book editBookDetails(String isbn, Book book) {
    return bookRepository
        .findByIsbn(isbn)
        .map(
            existingBook -> {
              Book toUpdate =
                  new Book(
                      existingBook.isbn(),
                      existingBook.title(),
                      existingBook.author(),
                      existingBook.price());
              return bookRepository.save(toUpdate);
            })
        .orElseGet(() -> bookRepository.save(book));
  }
}
