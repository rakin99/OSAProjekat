package service;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import entity.MyMessage;


public interface MessageServiceInterface {
	
	MyMessage findOne(Integer messageId);
	
	List<MyMessage> findAll();

}
