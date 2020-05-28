package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.MessageDTO;
import entity.Message;
import service.MessageServiceInterface;

@RestController
@RequestMapping(value="api/messages")
public class MessageController {

	@Autowired
	private MessageServiceInterface messageService;
	
	@GetMapping
	public ResponseEntity<List<MessageDTO>> getMessages(){
		List<Message> messages=messageService.findAll();
		List<MessageDTO> messagesDTO = new ArrayList<MessageDTO>();
		for (Message m:messages) {
			messagesDTO.add(new MessageDTO(m));
		}
		return new ResponseEntity<List<MessageDTO>>(messagesDTO,HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<MessageDTO> getMessage(@PathVariable("id") Integer id){
		Message message=messageService.findOne(id);
		if(message==null) {
			return new ResponseEntity<MessageDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MessageDTO>(new MessageDTO(message),HttpStatus.OK);
	}
	
}
