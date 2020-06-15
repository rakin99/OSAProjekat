package email.service;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import email.entity.MyMessage;


public interface MessageServiceInterface {
	
	MyMessage findOne(Integer messageId);
	
	List<MyMessage> findAll();

}
