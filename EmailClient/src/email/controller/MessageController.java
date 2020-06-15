package email.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import email.dto.MessageDTO;
import email.entity.MyMessage;
import email.read_send_mail.ReadMail;
import email.service.MessageService;
import email.service.MessageServiceInterface;

import javax.mail.Message;
import javax.mail.MessagingException;


@RestController
@RequestMapping(value="/api")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@GetMapping
	@RequestMapping(value="/messages")
	public ResponseEntity<List<MyMessage>> getMessages() throws MessagingException, IOException{
		List<MyMessage> messages=ReadMail.receiveEmail("smtp.gmail.com", "pop3", "rakindejan@gmail.com", "pexlqolkzswsczrj");
//		List<MyMessage> messages= messageService.findAll();
		int br=0;
//		for (MyMessage message : messages) {
//			 System.out.println("---------------------------------");  
//			 System.out.println("Email Number " + (br++));  
//			 System.out.println("Subject: " + message.getSubject());  
//			 System.out.println("From: " + message.get_from());  
//			 System.out.println("Text: " + message.getContent());  
//			messages.add(message);
//		}
		return new ResponseEntity<List<MyMessage>>(messages,HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<MessageDTO> getMessage(@PathVariable("id") Integer id){
		MyMessage message=messageService.findOne(id);
		if(message==null) {
			return new ResponseEntity<MessageDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MessageDTO>(new MessageDTO(message),HttpStatus.OK);
	}
	
}
