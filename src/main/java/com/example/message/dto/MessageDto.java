package com.example.message.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MessageDto {

    private String name;

    private String message;

    private MessageDto(String message) {
        this.message = message;
    }
}
