package email.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import email.entity.Account;
import email.entity.Contact;
import email.repository.ContactRepository;

@Service
public class ContactService implements ContactServiceInterface{
	
	@Autowired
	ContactRepository contactRepository;
	
	@Override
	public Contact findById(long contactId) {
		return contactRepository.findById(contactId);
	}
	
	@Override
	public List<Contact> findByAccountOrderByDisplayNameAsc(Account account){
		return contactRepository.findByAccountOrderByDisplayNameAsc(account);
	}

	@Override
	public int maxId() {
		int maxId=contactRepository.findMaxId();
		return maxId;
	}

	@Override
	public long count(String username) {
		long count=contactRepository.count(username);
		return count;
	}
	
	@Override
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public List<Contact> findAllContacts(String username) {
		return contactRepository.findAllContacts(username);
	}

	@Override
	public List<Contact> findByAccountOrderByFirstNameAsc(Account account) {
		return contactRepository.findByAccountOrderByFirstNameAsc(account);
	}

	@Override
	public List<Contact> findByAccountOrderByLastNameAsc(Account account) {
		return contactRepository.findByAccountOrderByLastNameAsc(account);
	}

}
