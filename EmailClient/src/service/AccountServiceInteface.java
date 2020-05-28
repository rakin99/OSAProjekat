package service;

import entity.Account;

public interface AccountServiceInteface {
	
	Account findByUsername(String username);

}
