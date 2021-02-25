package com.app.service;

import com.app.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesService {

    private final List<Message> messages = new ArrayList<>(
            List.of(
                    Message.builder().title("TITLE 1").text("TEXT 1").build(),
                    Message.builder().title("TITLE 2").text("TEXT 2").build(),
                    Message.builder().title("TITLE 3").text("TEXT 3").build()
            ));

    public Message add(Message message) {
        messages.add(message);
        return message;
    }

    public List<Message> findAll() {
        return messages;
    }
}
