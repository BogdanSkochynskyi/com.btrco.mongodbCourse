package com.btrco.mongodbCourse.week_01;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get("/", new Route(){

            public Object handle(Request request, Response response) throws Exception {
                return "HELLO WORLD!";
            }
        });

        Spark.get("/echo/:thing", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return request.params(":thing");
            }
        });
    }
}
