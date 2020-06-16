package email.controller;

import java.io.IOException;
import java.text.ParseException;
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
import javax.mail.internet.MimeMultipart;


@RestController
@RequestMapping(value="/api")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@GetMapping
	@RequestMapping(value="/messages")
	public ResponseEntity<List<MyMessage>> getMessages() throws MessagingException, IOException, ParseException{
		int maxId=0;
		System.out.println("Broj poruka u bazi je: ");
		System.out.println(messageService.count());
		if(messageService.count()!=0) {
			maxId=messageService.maxId();
		}
		System.out.println("Id poslednje poruke je:"+maxId);
		Message[] mess=ReadMail.receiveEmail("smtp.gmail.com", "pop3", "rakindejan@gmail.com", "pexlqolkzswsczrj",maxId);
		List<MyMessage> messages= messageService.findAll();
		int br=0;
//		for (MyMessage message : messages) {
//			 Message m=mess[(int) (message.getId()-1)];
//			 String content="";
//			    if (m.isMimeType("text/plain")) {
//			    	content = message.getContent().toString();
//			    } else if (m.isMimeType("multipart/*")) {
//			        MimeMultipart mimeMultipart = (MimeMultipart) m.getContent();
//			        content = ReadMail.getTextFromMimeMultipart(mimeMultipart);
//			    }
//			 message.setContent(content);
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
