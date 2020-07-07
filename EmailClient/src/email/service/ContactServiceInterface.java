package email.service;

import java.util.List;

import email.entity.Account;
import email.entity.Contact;

public interface ContactServiceInterface {

	Contact findById(long contactId);
	
	List<Contact> findByAccountOrderByDisplayNameAsc(Account account);

	int maxId();
	
	long count(String username);
	
	Contact save(Contact contact);
	
	List<Contact> findAllContacts(String username);
	
	List<Contact> findByAccountOrderByFirstNameAsc(Account account);
	
	List<Contact> findByAccountOrderByLastNameAsc(Account account);
	
}
