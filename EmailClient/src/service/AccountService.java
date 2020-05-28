package service;

import org.springframework.beans.factory.annotation.Autowired;

import entity.Account;
import repository.AccountRepository;

public class AccountService implements AccountServiceInteface{

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account findByUsername(String username){
		Account account = accountRepository.findByUsername(username);
		return account;
	}
	
}
