package email.service;

import java.util.List;

import email.entity.Account;
import email.entity.User;

public interface AccountServiceInterface {
	
	Account findByUsername(String username);
	
	Account findByPassword(String password);

	Account save(Account account);
	
	Account findByUsernameAndPassword(String username,String password);
	
	List<Account> findByUser(User user);
	
	Account findById(long id);
}
