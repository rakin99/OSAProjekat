package service;

import org.springframework.beans.factory.annotation.Autowired;

import entity.User;
import repository.UserRepository;

public class UserService implements UserServiceInteface{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findByUsernameAndPassword(String username, String password){
		User user = userRepository.findByUsername(username);
		if(user.getPassword().equals(password))
			return user;
		else
			return null;
	}
}
