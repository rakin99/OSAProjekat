package email.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import email.entity.Account;

@Component
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	Account findByUsername(String username);
	Account findByPassword(String password);
	
	@Query("FROM Account WHERE username LIKE :username AND password LIKE concat(:password,'%')")
	Account findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

}
