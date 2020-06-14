package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import entity.MyMessage;
import repository.MessageRepository;

@Service
public class MessageService implements MessageServiceInterface{
	
	@Autowired
	MessageRepository messageRepository;
	
	@Override
	public MyMessage findOne(Integer messageId) {
		return messageRepository.findOne(messageId);
	}
	
	@Override
	public List<MyMessage> findAll(){
		return messageRepository.findAll();
	}
}
