package com.codethen.dropwizard.sample;

import com.codethen.dropwizard.sample.controller.BookApi;
import com.codethen.dropwizard.sample.controller.BookController;
import com.codethen.dropwizard.sample.database.BooksDao;
import com.codethen.dropwizard.sample.database.BooksDaoMongoDb;
import com.codethen.dropwizard.sample.service.BookService;
import com.codethen.dropwizard.sample.service.BookServiceMongoDb;
import com.codethen.dropwizard.sample.util.MongoDbUtil;
import com.mongodb.client.MongoCollection;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.bson.Document;

/**
 * To start server, run with args: server
 */
public class MyApp extends Application<MyAppConfig> {

	public static void main(String[] args) throws Exception {
		new MyApp().run(args);
	}

	@Override
	public String getName() {
		return "my-app";
	}

	@Override
	public void initialize(Bootstrap<MyAppConfig> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets/", "/assets/"));
	}

	@Override
	public void run(MyAppConfig config, Environment env) throws Exception {

		MongoCollection<Document> products = MongoDbUtil.getConnection();
		BooksDao booksDao = new BooksDaoMongoDb(products);
		BookServiceMongoDb bookServiceMongoDb = new BookServiceMongoDb(booksDao);

		BookService bookService = new BookService();

		env.jersey().register(new BookController(bookService));
		env.jersey().register(new BookApi(bookService));
	}
}
