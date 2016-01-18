package com.btrco.mongodbCourse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SparkPostTest {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setClassForTemplateLoading(SparkPostTest.class, "/");
        Spark.get("/", new Route() {
            StringWriter writer = new StringWriter();

            public Object handle(Request request, Response response) throws Exception {
                Template template = configuration.getTemplate("fruitPicker.ftl");
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("fruits", Arrays.asList("Orange", "Apple", "Banana", "Cherry"));
                template.process(map, writer);
                return writer;
            }
        });

        Spark.post("/favourite_fruit", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                final String fruit = request.queryParams("fruit");
                if (fruit == null) {
                    return "Why you don't pick anything?";
                } else {
                    return "Your favourite fruit is " + fruit;
                }
            }
        });
    }
}
