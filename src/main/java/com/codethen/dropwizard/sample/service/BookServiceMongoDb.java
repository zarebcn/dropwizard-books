package com.codethen.dropwizard.sample.service;

import com.codethen.dropwizard.sample.database.BooksDao;
import com.codethen.dropwizard.sample.model.Book;

import java.util.Collection;

public class BookServiceMongoDb {

    private BooksDao booksDao;

    public BookServiceMongoDb(BooksDao booksDao) {

        this.booksDao = booksDao;
    }

    public Book getById(String id) {

        return booksDao.getById(id);
    }

    public Book findByTitle(String title) {

        return booksDao.findByTitle(title);
    }

    public Collection<Book> getAll() {

        return booksDao.getAll();
    }

    public Book add(Book book) {

        return booksDao.addBook(book);
    }

    public void delete(String id) {

        booksDao.deleteBook(id);
    }

    public void edit(String id, Book book) {

        booksDao.editBook(id, book);
    }
}
