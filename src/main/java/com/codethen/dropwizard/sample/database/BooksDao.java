package com.codethen.dropwizard.sample.database;

import com.codethen.dropwizard.sample.model.Book;

import java.util.Collection;
import java.util.List;

public interface BooksDao {

    Book getById(String id);

    Book findByTitle(String title);

    Collection<Book> getAll();

    Book addBook(Book book);

    void deleteBook(String id);

    void editBook(String id, Book book);



}
