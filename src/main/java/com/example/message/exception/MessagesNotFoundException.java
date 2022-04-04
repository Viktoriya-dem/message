package com.example.message.exception;

public class MessagesNotFoundException extends RuntimeException {
    public MessagesNotFoundException() {
        super("Messages not found!");
    }
}

