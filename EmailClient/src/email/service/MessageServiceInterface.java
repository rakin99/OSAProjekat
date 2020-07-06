package email.service;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import email.entity.Account;
import email.entity.MyMessage;


public interface MessageServiceInterface {
	
	MyMessage findById(long messageId);
	
	GregorianCalendar getMaxDate(String username);
	
	List<MyMessage> findByAccountOrderBySubjectAsc(Account account);

	int maxId();
	
	long count(String username);
	
	MyMessage save(MyMessage message);
	
	List<MyMessage> findAllSentMessage(String username);
	
	List<MyMessage> findByAccountOrderByFromAsc(Account account);
	
	List<MyMessage> findByAccountOrderByDateTimeAsc(Account account);
}
