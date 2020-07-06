package email.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

import email.dto.AccountDTO;
import email.entity.Account;
import email.entity.User;
import email.service.AccountService;
import email.service.UserService;

@RestController
@RequestMapping(value="/api")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired 
	private UserService userService;

	@GetMapping
	@RequestMapping(value = "/accounts/{username}")
	public ResponseEntity<List<AccountDTO>> getAccounts(@PathVariable("username") String username) throws ParseException{
		System.out.println("\nPocinjem da trazim account!<----------------------\n");
		User user=userService.findByUsername(username);
		List<Account> accounts = accountService.findByUser(user);
		List<AccountDTO> accountsDTO=new ArrayList<AccountDTO>();
		for (Account a : accounts) {
			if(a.isActive()) {
				accountsDTO.add(new AccountDTO(a));
			}
		}
		if(accountsDTO.size() != 0){
			return new ResponseEntity<List<AccountDTO>>(accountsDTO, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<AccountDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes = "application/json", value = "/accounts/{username}")
	public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO accountDTO,@PathVariable("username") String username){
		Account a1 = accountService.findByUsername(accountDTO.getUsername());
		User user=userService.findByUsername(username);
		if(a1!=null && a1.getUsername().equals(accountDTO.getUsername()) && a1.getSmtpAddress().equals(accountDTO.getSmtpAddress())) {
			return new ResponseEntity<AccountDTO>(HttpStatus.NO_CONTENT);
		}
		System.out.println("\nPoceo upisivanje account-a<----------------\n");
		Account account=new Account();
		account.setActive(accountDTO.isActive());
		account.setDisplayname(accountDTO.getDisplayName());
		account.setInServerAddress(accountDTO.getInServerAddress());
		account.setInServerPort(accountDTO.getInServerPort());
		account.setInServerType(accountDTO.getInServerType());
		account.setPassword(accountDTO.getPassword());
		account.setSmtpAddress(accountDTO.getSmtpAddress());
		account.setSmtpPort(accountDTO.getSmtpPort());
		account.setUsername(accountDTO.getUsername());
		account.setUser(user);
		
		account=accountService.save(account);
		return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.CREATED);
	}
	
//	@GetMapping
//	@RequestMapping(value = "/accounts/{username}")
//	public ResponseEntity<AccountDTO> updateAccount(@PathVariable("username") String username){
//		Account account=accountService.findByUsername(username);
//		if(account!=null) {
//			return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.OK);
//		}
//		return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
//	}
}
