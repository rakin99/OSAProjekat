package service;

import java.util.List;

import entity.MyMessage;

public interface MessageServiceInterface {
	
	MyMessage findOne(Integer messageId);
	
	List<MyMessage> findAll();

}
