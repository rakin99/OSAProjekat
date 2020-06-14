package service;

import org.springframework.stereotype.Component;

import entity.Account;

@Component
public interface AccountServiceInteface {
	
	Account findByUsername(String username);

}
