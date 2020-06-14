package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Account;
import repository.AccountRepository;

@Service
public class AccountService implements AccountServiceInteface{

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account findByUsername(String username){
		Account account = accountRepository.findByUsername(username);
		return account;
	}
	
}
