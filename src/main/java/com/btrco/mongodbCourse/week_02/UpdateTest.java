package com.btrco.mongodbCourse.week_02;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;

public class UpdateTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = database.getCollection("insertTest");

        collection.drop();

        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document()
                    .append("_id", i)
                    .append("x", i));
        }

//        collection.replaceOne(eq("x", 5), new Document("_id", 5).append("x", 20).append("update", true));

        collection.updateOne(eq("x", 5), new Document("$set", new Document("x", 20)));

        collection.updateOne(eq("_id", 9), new Document("$set", new Document("x", 20)), new UpdateOptions().upsert(true));

        collection.updateMany(gte("_id", 5), new Document("$inc", new Document("x", 1)));

        for (Document cur : collection.find().into(new ArrayList<Document>())){
            Helpers.printJson(cur);
        }
    }

}
