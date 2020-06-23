package email.service;

import email.entity.Account;

public interface AccountServiceInterface {
	
	Account findByUsername(String username);
	
	Account findByPassword(String password);

	Account save(Account account);
	
	Account findByUsernameAndPassword(String username,String password);
}
