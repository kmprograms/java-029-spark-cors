package com.app.web;

import com.app.model.Message;
import com.app.service.MessagesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;


@RequiredArgsConstructor
@Slf4j
public class Routing {

    private final MessagesService messagesService;

    public void initRoutes() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        options("/*", (request, response) -> "PREFLIGHT REQUEST DONE");

        after((request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://localhost:3000");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization");
            response.header("Access-Control-Allow-Methods", "*");
        });

        path("/messages", () -> {

            get("", (request, response) -> {
                var messages = messagesService.findAll();
                response.status(200);
                response.header("Content-Type", "application/json;charset=utf-8");
                return messages;
            }, new JsonTransformer());

            post("", (request, response) -> {
                log.info("Authorization: {}", request.headers("Authorization"));
                var message = gson.fromJson(request.body(), Message.class);
                response.status(201);
                response.header("Content-Type", "application/json;charset=utf-8");
                return messagesService.add(message);
            }, new JsonTransformer());

        });
    }
}
