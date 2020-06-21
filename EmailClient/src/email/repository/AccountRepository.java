package email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import email.entity.Account;

@Component
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	Account findByUsername(String username);
	Account findByPassword(String password);

}
