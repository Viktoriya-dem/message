package com.example.message.controller;

import com.example.message.dto.MessageDto;
import com.example.message.dto.MessageDtoMessage;
import com.example.message.exception.MessagesNotFoundException;
import com.example.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    public List<MessageDtoMessage> sendMessage(Principal principal, @RequestBody MessageDto messageDto){
        if (messageDto.getName().equals(principal.getName())){
                return messageService.sendMessage(messageDto);
        } else throw new UsernameNotFoundException("User not found!");
    }
}
