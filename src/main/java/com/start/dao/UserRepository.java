package com.start.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.start.entities.User;
public interface UserRepository extends JpaRepository<User,Integer>{

	
}
