package email.repository;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import email.entity.MyMessage;


@Component
public interface MessageRepository extends JpaRepository<MyMessage, Integer>{
 
	@Query("SELECT max(id) FROM MyMessage")
	int findMaxId();
	
	@Query("SELECT count(id) FROM MyMessage WHERE _to LIKE concat('%',:username,'%')")
	long count(@Param("username") String username);
	
	@Query("SELECT max(dateTime) FROM MyMessage")
	GregorianCalendar getMaxDate();
	
	MyMessage findById(long id);
	
	@Query("FROM MyMessage WHERE _to LIKE concat('%',:username,'%')")
	List<MyMessage> findAllMessage(@Param("username") String username);
	
}
