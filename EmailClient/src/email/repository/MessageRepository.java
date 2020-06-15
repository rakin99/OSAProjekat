package email.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import email.entity.MyMessage;


@Component
public interface MessageRepository extends JpaRepository<MyMessage, Integer>{

}
