package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import entity.Message;
import repository.MessageRepository;

public class MessageService implements MessageServiceInterface{
	
	@Autowired
	MessageRepository messageRepository;
	
	@Override
	public Message findOne(Integer messageId) {
		return messageRepository.findOne(messageId);
	}
	
	@Override
	public List<Message> findAll(){
		return messageRepository.findAll();
	}
}
