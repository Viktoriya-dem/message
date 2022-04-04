package com.example.message;

import com.example.message.controller.MessageController;
import com.example.message.dto.MessageDto;
import com.example.message.entity.Message;
import com.example.message.entity.User;
import com.example.message.repo.MessageRepo;
import com.example.message.repo.UserRepo;
import com.example.message.security.jwt.JwtFilter;
import com.example.message.security.jwt.JwtProvider;
import com.example.message.service.MessageService;
import com.example.message.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class MessageApplicationTests {

	@Autowired
	private MessageService messageService;

	@MockBean
	private UserRepo userRepo;

	@MockBean
	private MessageRepo messageRepo;

	@Autowired
	private ModelMapper mapper;

	private static final User USER = new User(-10, "test", "test", null);
	private static final Message MESSAGE=new Message(-10, "Привет", USER);
	private static final Message MESSAGE2=new Message(-10, "History 10", USER);
	public static final List<Message> messages = new ArrayList<>();

	@Test
	public void sendMessageTest(){
		when(userRepo.findByUsername(USER.getUsername())).thenReturn(USER);
		MessageDto messageDto=mapper.map(MESSAGE, MessageDto.class);
		messageService.sendMessage(messageDto);
		verify(messageRepo).save(any());
	}

	@Test
	public void sendMessageShowHistoryTest(){
		when(userRepo.findByUsername(USER.getUsername())).thenReturn(USER);
		List<Message> messages=new ArrayList<>();
		messages.add(MESSAGE);
		when(messageRepo.getTenLastMessages()).thenReturn(messages);
		MessageDto messageDto=mapper.map(MESSAGE2, MessageDto.class);
		messageService.sendMessage(messageDto);
		verify(messageRepo).getTenLastMessages();
	}

}
