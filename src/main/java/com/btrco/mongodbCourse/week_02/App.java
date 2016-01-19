package com.btrco.mongodbCourse.week_02;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

public class App {
    public static void main(String[] args) {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(200).build();
        MongoClient client = new MongoClient(new ServerAddress(), options);

        MongoDatabase database = client.getDatabase("test").withReadPreference(ReadPreference.secondary());

        MongoCollection<Document> collection = database.getCollection("test")/*.withReadPreference(ReadPreference.secondary())*/;
        MongoCollection<BsonDocument> collectionBson = database.getCollection("test", BsonDocument.class)/*.withReadPreference(ReadPreference.secondary())*/;
    }
}
