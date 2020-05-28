package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.MyMessage;

public interface MessageRepository extends JpaRepository<MyMessage, Integer>{

}
