package email.service;

import org.springframework.stereotype.Component;

import email.entity.Account;

@Component
public interface AccountServiceInteface {
	
	Account findByUsername(String username);

}
