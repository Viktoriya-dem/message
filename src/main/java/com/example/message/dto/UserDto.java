package com.example.message.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
    private int id;
    private String username;
    private String password;

       public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
