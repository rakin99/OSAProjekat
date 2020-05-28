package service;

import java.util.List;

import entity.Message;

public interface MessageServiceInterface {
	
	Message findOne(Integer messageId);
	
	List<Message> findAll();

}
