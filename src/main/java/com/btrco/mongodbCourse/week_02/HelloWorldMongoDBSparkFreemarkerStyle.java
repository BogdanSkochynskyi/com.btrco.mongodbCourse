package com.btrco.mongodbCourse.week_02;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.bson.Document;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;

public class HelloWorldMongoDBSparkFreemarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldMongoDBSparkFreemarkerStyle.class, "/");

        MongoClient client = new MongoClient();

        MongoDatabase database = client.getDatabase("course");
        final MongoCollection<Document> collection = database.getCollection("hello");

        collection.drop();

        collection.insertOne(new Document("name", "MongoDB"));

        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                StringWriter sw = new StringWriter();
                try {
                    Template template = configuration.getTemplate("hello.ftl");
                    Document document = collection.find().first();
                    template.process(document, sw);
                } catch (Exception e){
                    e.printStackTrace();
                }
                return sw;
            }
        });
    }
}
