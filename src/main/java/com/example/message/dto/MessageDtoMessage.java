package com.example.message.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MessageDtoMessage {

    private String message;

    private MessageDtoMessage(String message) {
        this.message = message;
    }
}
