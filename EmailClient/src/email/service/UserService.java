package email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import email.entity.User;
import email.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		User user=userRepository.findByUsernameAndPassword(username,password);
		return user;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	
}
