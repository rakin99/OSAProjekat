package email.service;

import email.entity.User;

public interface UserServiceInteface {
	
	User findByUsernameAndPassword(String username, String password);

}
