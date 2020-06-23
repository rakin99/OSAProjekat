package email.service;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import email.entity.MyMessage;


public interface MessageServiceInterface {
	
	MyMessage findById(long messageId);
	
	GregorianCalendar getMaxDate();
	
	List<MyMessage> findAllMessage(String username);

	int maxId();
	
	long count(String username);
	
	MyMessage save(MyMessage message);
}
