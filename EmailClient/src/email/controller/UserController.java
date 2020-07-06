package email.controller;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import email.dto.MessageDTO;
import email.dto.UserDTO;
import email.entity.Account;
import email.entity.User;
import email.service.UserService;

@RestController
@RequestMapping(value="/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	@RequestMapping(value = "/users/{username}/{password}")
	public ResponseEntity<UserDTO> getAUser(@PathVariable("username") String username,@PathVariable("password") String password) throws ParseException{
		System.out.println("\nPocinjem da trazim user-a!<----------------------\n");
		User u1 = userService.findByUsernameAndPassword(username,password);
		if(u1 != null){
			return new ResponseEntity<UserDTO>(new UserDTO(u1), HttpStatus.OK);
		}
		
		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes = "application/json", value = "/users")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
		User u1 = userService.findByUsername(userDTO.getUsername());
		if(u1!=null && u1.getUsername().equals(userDTO.getUsername())) {
			return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
		}
		System.out.println("\nPoceo upisivanje user-a<----------------\n");
		User user=new User();
		user.setFirstname(userDTO.getUsername());
		user.setLastname(userDTO.getLastname());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setAccounts(new ArrayList<Account>());
		user=userService.save(user);
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.CREATED);
	}
	
	@GetMapping
	@RequestMapping(value = "/users/{username}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username){
		
		System.out.println("Trazim user-a sa korisnickim imenom: "+username);
		User user=userService.findByUsername(username);
		if(user!=null) {
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
		}
		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value = "/users/{username}", consumes="application/json")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,@PathVariable("username") String username){
		
		System.out.println("Cuvam izmene user-a sa korisnickim imenom: "+username);
		User user=userService.findByUsername(username);
		if(user!=null) {
			user.setFirstname(userDTO.getFirstname());
			user.setLastname(userDTO.getLastname());
			user.setPassword(userDTO.getPassword());
			
			user=userService.save(user);
			
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
		}
		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}

}
