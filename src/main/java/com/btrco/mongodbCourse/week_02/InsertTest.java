package com.btrco.mongodbCourse.week_02;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class InsertTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = database.getCollection("insertTest");

        collection.drop();

        Document smith = new Document("name", "Smith")
                .append("age", 30)
                .append("profession", "programmer");

        Document jones = new Document("name", "Jones")
                .append("age", 25)
                .append("profession", "designer");

//        collection.insertOne(smith); //if one document
        collection.insertMany(Arrays.asList(smith, jones)); //if several documents
    }
}
