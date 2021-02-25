package com.app;

import com.app.service.MessagesService;
import com.app.web.Routing;

import static spark.Spark.initExceptionHandler;
import static spark.Spark.port;

public class App {

    public static void main(String[] args) {
        initExceptionHandler(e -> System.out.println(e.getMessage()));
        port(8080);
        var messagesService = new MessagesService();
        var routing = new Routing(messagesService);
        routing.initRoutes();
    }
}
