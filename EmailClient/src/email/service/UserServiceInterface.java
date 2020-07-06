package email.service;

import email.entity.User;

public interface UserServiceInterface {
	
	User save(User user);
	
	User findByUsernameAndPassword(String username,String password);
	
	User findByUsername(String username);

}
