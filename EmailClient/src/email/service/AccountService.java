package email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import email.entity.Account;
import email.repository.AccountRepository;

@Service
public class AccountService implements AccountServiceInterface{

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account findByUsername(String username) {
		Account account=accountRepository.findByUsername(username);
		return account;
	}
	
	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account findByPassword(String password) {
		Account account=accountRepository.findByPassword(password);
		return account;
	}

	@Override
	public Account findByUsernameAndPassword(String username, String password) {
		Account account=accountRepository.findByUsernameAndPassword(username,password);
		return account;
	}

}
