package service;

import entity.User;

public interface UserServiceInteface {
	
	User findByUsernameAndPassword(String username, String password);

}
