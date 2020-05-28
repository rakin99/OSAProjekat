package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import javax.mail.MessagingException;

import dto.MessageDTO;
import entity.MyMessage;
import read_send_mail.ReadMail;
import service.MessageServiceInterface;

@RestController
@RequestMapping(value="api/messages")
public class MessageController {

	@Autowired
	private MessageServiceInterface messageService;
	
	@GetMapping
	public ResponseEntity<List<Message>> getMessages() throws MessagingException, IOException{
		Message[] m=ReadMail.receiveEmail("smtp.gmail.com", "pop3", "rakindejan@gmail.com", "dlelziaoqgpbcxls");
		List<Message> messages = new ArrayList<Message>();
		for (int i = 0; i < m.length; i++) {
			Message message = m[i];
			 System.out.println("---------------------------------");  
			 System.out.println("Email Number " + (i + 1));  
			 System.out.println("Subject: " + message.getSubject());  
			 System.out.println("From: " + message.getFrom()[0]);  
			 System.out.println("Text: " + message.getContent().toString());  
			messages.add(message);
		}
		return new ResponseEntity<List<Message>>(messages,HttpStatus.OK);
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
