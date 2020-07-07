package email.service;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import email.entity.Account;
import email.entity.MyMessage;
import email.repository.MessageRepository;

@Service
public class MessageService implements MessageServiceInterface{
	
	@Autowired
	MessageRepository messageRepository;
	
	@Override
	public MyMessage findById(long messageId) {
		return messageRepository.findById(messageId);
	}
	
	@Override
	public List<MyMessage> findByAccountOrderBySubjectAsc(Account account){
		return messageRepository.findByAccountOrderBySubjectAsc(account);
	}

	@Override
	public int maxId() {
		int maxId=messageRepository.findMaxId();
		return maxId;
	}

	@Override
	public long count(String username) {
		long count=messageRepository.count(username);
		return count;
	}
	
	@Override
	public MyMessage save(MyMessage message) {
		return messageRepository.save(message);
	}

	@Override
	public GregorianCalendar getMaxDate(String username) {
		return messageRepository.getMaxDate(username);
	}

	@Override
	public List<MyMessage> findAllSentMessage(String username) {
		return messageRepository.findAllSentMessage(username);
	}

	@Override
	public List<MyMessage> findByAccountOrderByFromAsc(Account account) {
		return messageRepository.findByAccountOrderByFromAsc(account);
	}

	@Override
	public List<MyMessage> findByAccountOrderByDateTimeAsc(Account account) {
		return messageRepository.findByAccountOrderByDateTimeAsc(account);
	}

	@Override
	public List<MyMessage> findByAccountOrderBySubjectDesc(Account account) {
		return messageRepository.findByAccountOrderBySubjectDesc(account);
	}

	@Override
	public List<MyMessage> findByAccountOrderByFromDesc(Account account) {
		return messageRepository.findByAccountOrderByFromDesc(account);
	}

	@Override
	public List<MyMessage> findByAccountOrderByDateTimeDesc(Account account) {
		return messageRepository.findByAccountOrderByDateTimeDesc(account);
	}
}
