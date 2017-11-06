package com.codethen.dropwizard.sample.database;

import com.codethen.dropwizard.sample.model.Book;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class BooksDaoMongoDb implements BooksDao {

   private MongoCollection<Document> products;
   private int nextId;

   public BooksDaoMongoDb (MongoCollection<Document> products) {
       this.products = products;
       nextId = 5;
   }


    @Override
    public Book getById(String id) {

        Bson query = Filters.eq("_id", new ObjectId(id));
        MongoCursor<Document> cursor = products.find(query).iterator();
        Book book = new Book();

        while (cursor.hasNext()) {

            Document doc = cursor.next();
            int bookId = (Integer) doc.get("id");
            String title = (String) doc.get("title");
            String author = (String) doc.get("author");
            int numPages = (Integer) doc.get("numPages");
            book = new Book(bookId, title, author, numPages);
        }
        return book;
    }

    @Override
    public Book findByTitle(String bookTitle) {

        Bson query = Filters.eq("title", bookTitle);
        MongoCursor<Document> cursor = products.find(query).iterator();
        Book book = new Book();

        while (cursor.hasNext()) {

            Document doc = cursor.next();
            int bookId = (Integer) doc.get("id");
            String title = (String) doc.get("title");
            String author = (String) doc.get("author");
            int numPages = (Integer) doc.get("numPages");
            book = new Book(bookId, title, author, numPages);
        }
        return book;
    }

    @Override
    public Collection<Book> getAll() {

        MongoCursor<Document> cursor = products.find().iterator();
        Book book = new Book();
        Collection<Book> books = new ArrayList<>();

        while (cursor.hasNext()) {

            Document doc = cursor.next();
            int bookId = (Integer) doc.get("id");
            String title = (String) doc.get("title");
            String author = (String) doc.get("author");
            int numPages = (Integer) doc.get("numPages");
            book = new Book(bookId, title, author, numPages);
            books.add(book);
        }
        return books;
    }

    @Override
    public Book addBook(Book book) {

        Document document = new Document("id", nextId).append("title", book.getTitle()).append("author", book.getAuthor())
                .append("numPages", book.getNumPages());
        nextId++;
        products.insertOne(document);
        return book;
    }

    @Override
    public void deleteBook(String id) {

        products.deleteOne(eq("_id", new ObjectId(id)));
    }

    @Override
    public void editBook(String id, Book book) {

        products.updateOne(eq("_id", new ObjectId(id)), combine(set("id", book.getId()),
                set("title", book.getTitle()), set("author", book.getAuthor()), set("numPages", book.getNumPages())));
    }
}
