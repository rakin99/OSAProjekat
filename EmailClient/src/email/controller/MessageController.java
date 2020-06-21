package email.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import email.dto.MessageDTO;
import email.entity.Account;
import email.entity.MyMessage;
import email.read_send_mail.ReadMail;
import email.read_send_mail.SendMail;
import email.service.AccountService;
import email.service.MessageService;
import email.service.MessageServiceInterface;
import email.tools.DateUtil;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;


@RestController
@RequestMapping(value="/api")
public class MessageController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private AccountService accountService;
	
	@GetMapping
	@RequestMapping(value="/messages/{username}")
	public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable("username") String username) throws MessagingException, IOException, ParseException{
		Account account=accountService.findByUsername(username);
		GregorianCalendar dateTime=DateUtil.getLastOneHour();
		System.out.println("\nUsername: "+username+"<--------------------");
		long count=messageService.count(username);
		System.out.println("\nBroj poruka u bazi je: "+messageService.count(username)+"\n");
		if(count!=0) {
			dateTime=messageService.getMaxDate();
		}
		System.out.println("Vreme poslednje poruke je:"+DateUtil.formatTimeWithSecond(dateTime));
		ReadMail.receiveEmail(account.getSmtpAddress(), account.getInServerAddress(), account.getUsername(), account.getPassword(),dateTime,count);
		List<MyMessage> messages= messageService.findAll();
		List<MessageDTO> messagesDTO=new ArrayList<MessageDTO>();
		for (MyMessage myMessage : messages) {
			if(myMessage.isActive()) {
				messagesDTO.add(new MessageDTO(myMessage));
			}
		}
		return new ResponseEntity<List<MessageDTO>>(messagesDTO,HttpStatus.OK);
	}
	
	@GetMapping(value="/messages/{username}/{id}")
	public ResponseEntity<MessageDTO> getMessage(@PathVariable("username") String username,@PathVariable("id") Integer id) throws ParseException{
		MyMessage message = messageService.findById(id);
		if(message == null){
			return new ResponseEntity<MessageDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MessageDTO>(new MessageDTO(message), HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json", value = "/messages")
	public ResponseEntity<MessageDTO> saveMessage(@RequestBody MessageDTO messageDTO) throws ParseException{
		System.out.println("\nPoceo slanje poruke!<-------------------------\n");
		System.out.println("\nCC JE:!"+messageDTO.get_cc()+"<-------------------------\n");
		MyMessage message = new MyMessage();
		message.set_from(messageDTO.get_from());
		message.set_to(messageDTO.get_to());
		message.set_cc(messageDTO.get_cc());;
		message.set_bcc(messageDTO.get_bcc());;
		message.setDateTime(DateUtil.convertFromDMYHMS(messageDTO.getDateTime()));
		message.setSubject(messageDTO.getSubject());
		message.setContent(messageDTO.getContent());
		SendMail.send(message);
	
		message = messageService.save(message);
		return new ResponseEntity<MessageDTO>(new MessageDTO(message), HttpStatus.CREATED);	
	}
	
	@DeleteMapping(value="/messages/{id}")
	public ResponseEntity<Void> deleteMessage(@PathVariable("id") Long id){
		System.out.println("\nPocinjem sa trazenjem poruke za brisanje! <----------------------------------------\n");
		MyMessage myMessage = messageService.findById(id);
		if (myMessage != null){
			System.out.println("\nPronasao sam poruku i sada pocinjem sa brisanjem! <------------------------------\n");
			myMessage.setActive(false);
			messageService.save(myMessage);
			System.out.println("\nObrisao sam poruku! <------------------------------------------------------\n");
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {	
			System.out.println("Nisam uspeo da pronadjem poruku! <-----------------------------------------------");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
