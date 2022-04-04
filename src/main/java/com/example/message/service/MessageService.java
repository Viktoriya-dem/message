package com.example.message.service;

import com.example.message.dto.MessageDto;
import com.example.message.dto.MessageDtoMessage;
import com.example.message.entity.Message;
import com.example.message.entity.User;
import com.example.message.exception.MessagesNotFoundException;
import com.example.message.repo.MessageRepo;
import com.example.message.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageService {

    private final MessageRepo messageRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Transactional
    public List<MessageDtoMessage> sendMessage(MessageDto messageDto) {
        if (!messageDto.getMessage().equals("History 10")) {
            User user = userRepo.findByUsername(messageDto.getName());
            Message message = modelMapper.map(messageDto, Message.class);
            message.setMessage(message.getMessage());
            message.setUser(user);
            messageRepo.save(message);
            return null;
        } else {
            List<Message> messages = messageRepo.getTenLastMessages();
            if (messages.size()==0) throw new MessagesNotFoundException();
            List<MessageDtoMessage> messageDtos = new ArrayList<>();
            for (Message m : messages) {
                MessageDtoMessage mapMessage = modelMapper.map(m, MessageDtoMessage.class);
                messageDtos.add(mapMessage);
            }
            return messageDtos;
        }
    }


}
