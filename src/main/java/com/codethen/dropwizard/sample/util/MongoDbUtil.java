package com.codethen.dropwizard.sample.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDbUtil {

    public static MongoCollection<Document> getConnection() {

        // Connect to local mongodb server
        MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");
        MongoClient client = new MongoClient(uri);

        // Connect to a database named "codethen"
        MongoDatabase db = client.getDatabase("codethen");

        // Get collection called "products"
        MongoCollection<Document> products = db.getCollection("products");
        
        client.close();

        return products;
    }
}
