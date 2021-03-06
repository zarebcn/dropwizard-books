package com.codethen.dropwizard.sample.service;

import com.codethen.dropwizard.sample.model.Book;

		import java.util.Collection;
		import java.util.HashMap;
		import java.util.Map;
		import java.util.stream.Collectors;

public class BookService {

	private Map<Integer, Book> books;

	public BookService() {

		books = new HashMap<>();
		books.put(1,  new Book(1,  "Head first Java", "Kathy Sierra, Bert Bates", 720) );
		books.put(2,  new Book(2,  "Refactoring", "Martin Fowler", 464) );
		books.put(3,  new Book(3,  "Head first design patterns", "Eric Freeman, Beth Robson", 694) );
		books.put(4, new Book(4, "Clean code", "Robert C. Martin", 288) );
	}

	public Book getById(int id) {

		return books.get(id);
	}

	public Collection<Book> findByTitle(String search) {

		final Collection<Book> result;

		if (search != null) {
			result = this.books.values().stream()
					.filter(book -> book.getTitle().toLowerCase().contains(search.toLowerCase()))
					.collect(Collectors.toList());
		} else {
			result = this.books.values();
		}

		return result;
	}
}
