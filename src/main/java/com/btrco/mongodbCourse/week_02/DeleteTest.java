package com.btrco.mongodbCourse.week_02;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.gt;

public class DeleteTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = database.getCollection("insertTest");

        collection.drop();

        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document().append("_id", i));
        }

        collection.deleteMany(gt("_id", 4));

        List<Document> all = collection.find().into(new ArrayList<Document>());

        for (Document cur : all){
            Helpers.printJson(cur);
        }
    }
}
