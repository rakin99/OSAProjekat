package email.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import email.dto.AccountDTO;
import email.entity.Account;
import email.service.AccountService;

@RestController
@RequestMapping(value="/api")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@GetMapping
	@RequestMapping(value = "/accounts/{username}/{password}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable("username") String username,@PathVariable("password") String password) throws ParseException{
		System.out.println("\nPocinjem da trazim account!<----------------------\n");
		Account a1 = accountService.findByUsername(username);
		Account a2 = accountService.findByPassword(password);
		if(a1 != null || a2!=null){
			if(a1.getUsername().equals(a2.getUsername()) && a1.getPassword().equals(a2.getPassword())) {
				return new ResponseEntity<AccountDTO>(new AccountDTO(a1), HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes = "application/json", value = "/accounts")
	public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO accountDTO){
		Account a1 = accountService.findByUsername(accountDTO.getUsername());
		if(a1==null) {
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
			
			account=accountService.save(account);
			return new ResponseEntity<AccountDTO>(new AccountDTO(account), HttpStatus.CREATED);
		}
		return new ResponseEntity<AccountDTO>(HttpStatus.NO_CONTENT);
	}
}